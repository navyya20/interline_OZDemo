package jp.co.interlineOZDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import jp.co.interlineOZDemo.dao.MemberDAO;
import jp.co.interlineOZDemo.util.FileService;
import jp.co.interlineOZDemo.util.GetProperties;
import jp.co.interlineOZDemo.util.PageNavigator;
import jp.co.interlineOZDemo.vo.BillInformVO;
import jp.co.interlineOZDemo.vo.EstimateItemsVO;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;





@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private static final int countPerPage=20;	
	private static final int pagePerGroup=10;
	String rootPath="aaa";
	
	@Autowired
	MemberDAO dao;
	
	//private final String resourcesDir=c.getContextPath();
	public MemberController() {
		GetProperties properties= new GetProperties();
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) { 
			System.out.println("Window");
			rootPath = properties.getServerPath2(); 
		} else if (os.contains("mac")) { 
			System.out.println("Mac"); 
		} else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) { 
			System.out.println("Unix"); 
			rootPath = properties.getServerPath();
		} else if (os.contains("linux")) { 
			System.out.println("Linux"); 
			rootPath = properties.getServerPath(); 
		} else if (os.contains("sunos")) { 
			System.out.println("Solaris"); 
		}
		System.out.println("설정된 루트경로:"+rootPath);
	}
	
	//memberMain
	@RequestMapping(value = "/memberMain", method=RequestMethod.GET)
	public String mainMenuMember() {

		return "Member/mainMenuMember";
	}
	
	//견적서 리스트 페이지 
	@RequestMapping(value="/estimateSheetList", method=RequestMethod.GET)
	public String getEstimateSheetList(HttpSession session , Model model ,@RequestParam(value="page", defaultValue="1") int page) {
		System.out.println("견적서 리스트 페이지 호출 실행");
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		int total = dao.getTotalEstimateSheet(userInform.getUserNum());
		PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);
		ArrayList<EstimateSheetVO> estimateSheetArray = new ArrayList<EstimateSheetVO>();
		estimateSheetArray = dao.getEstimateSheetList(navi.getStartRecord(), navi.getCountPerPage(), userInform.getUserNum());
		model.addAttribute("estimateSheetArray",estimateSheetArray);
		model.addAttribute("pn", navi);
		System.out.println("견적서 리스트 페이지 호출 완료");
		return "Document/estimateSheetList";
	}
	
	//견적서 작성
	@RequestMapping(value="/writeEstimate", method=RequestMethod.GET)
	public String writeEstimateSheet(HttpSession session, Model model) {
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		JSONObject userInformJsonObject = new JSONObject(userInform);
		String userInformJsonString = userInformJsonObject.toString();
		model.addAttribute("userInformJsonString", userInformJsonString);
		return "Document/writeEstimateSheet";
	}
	
	//견적서 수정작성
		@RequestMapping(value="/modEstimate", method=RequestMethod.GET)
		public String modEstimateSheet(HttpSession session, Model model, int reportNum) {
			UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
			EstimateSheetVO userNumReportNum = new EstimateSheetVO();
			userNumReportNum.setUserNum(userInform.getUserNum());
			userNumReportNum.setReportNum(reportNum);
			EstimateSheetVO estimateSheet = dao.getEstimateSheet(userNumReportNum);
			if(estimateSheet==null) {
				return "Member/mainMenuMember";
			}
			JSONObject estimateSheetJson = new JSONObject(estimateSheet);
			String estimateSheetJsonString = estimateSheetJson.toString();
			model.addAttribute("estimateSheetJsonString", estimateSheetJsonString);
			
			ArrayList<EstimateItemsVO> estimateItems = dao.getEstimateItems(reportNum);
			if(estimateItems.size() !=0 && estimateItems != null) {
				JSONArray estimateItemsJsonObject = new JSONArray(estimateItems);
				String estimateItemsJsonString = estimateItemsJsonObject.toString();
				System.out.println(estimateItemsJsonString);
				model.addAttribute("estimateItemsJsonString", estimateItemsJsonString);
			}
			model.addAttribute("stampFileName", userInform.getStampFileName());
			return "Document/modEstimateSheet";
		}
	
	
	//견적서 저장
	@ResponseBody
	@RequestMapping(value="/saveEstimate", method=RequestMethod.POST)
	public String saveEstimateSheet(EstimateSheetVO estimateSheetVO, String estimateItemsString , HttpSession session, String email) {
		System.out.println("saveEstimateSheet실행");
		System.out.println("견적서내용:"+estimateSheetVO);
		System.out.println("견적아이템들:"+estimateItemsString);
		SimpleDateFormat estimateDate_pattern = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat newDate_pattern = new SimpleDateFormat("yyyy.MM.dd");
		try {
			Date estimate_Date = estimateDate_pattern.parse(estimateSheetVO.getReportDate());
			estimateSheetVO.setReportDate(newDate_pattern.format(estimate_Date)); //청구서에 입력된 날짜 데이터 포멧
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//견적내용 insert후 reportNum받아옴.
		int reportNum = dao.insertEstimateSheet(estimateSheetVO);
		
		//estimateItemsArray에 작성한 아이템들이 배열로 들어옴.
		ArrayList<EstimateItemsVO> estimateItemsArray = new ArrayList<EstimateItemsVO>();
		JSONObject jsonObject = new JSONObject(estimateItemsString);
		JSONArray jsonArray = (JSONArray)jsonObject.get("itemArray");
		System.out.println("견적된item갯수:"+jsonArray.length());
		if(jsonArray.length() != 0) {
			for ( int i = 0 ; i < jsonArray.length() ; i++ ) {
				JSONObject item = (JSONObject)jsonArray.get(i);
				EstimateItemsVO estimateItemsVO = new EstimateItemsVO();
				estimateItemsVO.setReportNum(reportNum);
				estimateItemsVO.setItemNum(Integer.parseInt((String)item.get("itemNum")));
				estimateItemsVO.setItemName((String)item.get("itemName"));
				estimateItemsVO.setAmount(Integer.parseInt((String)item.get("amount")));
				estimateItemsVO.setUnit((String)item.get("unit"));
				estimateItemsVO.setUnitPrice(Integer.parseInt((String)item.get("unitPrice")));
				estimateItemsVO.setPrice(Long.parseLong((String)item.get("price")));
				estimateItemsArray.add(estimateItemsVO);
			}
			dao.insertEstimateItems(estimateItemsArray);
		}
		
		/*파일 데몬서버에 떨구기
		GetProperties properties= new GetProperties();
		String ozId="admin";
		String ozPW="admin1";
		String OZserverURL="http://"+properties.getOzIP()+"/oz80/server";
		String ipScheduler=properties.getOzIP().split(":")[0];
		int portScheduler=9521;
		ExportReport export=new ExportReport(ozId, ozPW, OZserverURL, ipScheduler, portScheduler);
		String nameOzr="OZDemo/readEstimateSheet.ozr";
		String[] ozrParamValue = null;
		String nameOdi ="readEstimateSheet";
		String[] odiParamValue = {"userNum="+estimateSheetVO.getUserNum(),"reportNum="+reportNum};
		String formatExport = "pdf";
		String fileNameExport="reportExport"+estimateSheetVO.getReportName()+reportNum;
		String result="";
		JSONObject exportJsonObject = new JSONObject();
		exportJsonObject.put("stamp", "data:image/png;base64,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");
		String jsonData = exportJsonObject.toString();
		try {
			result=export.exportMethod(jsonData, nameOzr, ozrParamValue, nameOdi, odiParamValue, formatExport, fileNameExport);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="saveError";
		}
		*/
		/*이메일 보내기
		if (!email.equals("") && email!=null) {
			System.out.println("이메일을 보냅니다.");
			System.out.println("emailAddress:" + email);
			SendMail mail = new SendMail();
			String title = "OZ_Demoの件";
			String text = "作成した見積書をお送りいたします。";
			GetProperties getProperties = new GetProperties();
			String filePath = getProperties.getServerPath()+"/webapps/ozsch80/Repository/pdf/"+"reportExport"+estimateSheetVO.getReportName()+reportNum+".pdf";
			filePath.replace('/', File.separatorChar);
			System.out.println("filePath:"+filePath);
			File targetFile = new File(filePath);
			targetFile.setReadable(true, false);
            targetFile.setWritable(true, false);
            targetFile.setExecutable(true, false);
            EstimateSheetVO estimateSheet = dao.getEstimateSheet(estimateSheetVO);
			String fileName = estimateSheet.getReportNum()+"OZDemo"+estimateSheet.getReportDate()+".pdf";
			System.out.println("fileName:"+fileName);
			String html="<div>"+estimateSheet.getReceiver()+"</div>"+"<div>ご担当様<div><br></br><div>下記の御見積書を作成しました。</div><br></br><div>文書番号: "+estimateSheet.getReportNum()+"</div><div>作成日: "+estimateSheet.getReportDate()+"</div><div>件名: "+estimateSheet.getReportName()+"</div><div>ファイル名: "+fileName+"</div>";
			mail.MailSend(email, title, text, html, filePath, fileName);
		}
		*/
		return "success";
	}
	
	
	//견적서 삭제
	@ResponseBody
	@RequestMapping(value="/deleteSheet", method=RequestMethod.GET)
	public String deleteSheet(int[] reportNum, Model model, HttpSession session) {
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		int userNum=userInform.getUserNum();
		ArrayList<EstimateSheetVO> estimateSheetArray = new ArrayList<EstimateSheetVO>();
		
		for(int i=0 ; i<reportNum.length ; i++) {
			EstimateSheetVO estimateSheet = new EstimateSheetVO();
			estimateSheet.setReportNum(reportNum[i]);
			estimateSheetArray.add(estimateSheet);
		}
		
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("userNum", userNum);
		hashMap.put("reportNum", reportNum);
		dao.deleteSheet(hashMap);
		
		return "Document/estimateSheetList";
	}
	
	
	//견적서 열람
	@RequestMapping(value="/readEstimate", method=RequestMethod.GET)
	public String readEstimate(int reportNum, Model model, HttpSession session) {
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		EstimateSheetVO userNumReportNum = new EstimateSheetVO();
		userNumReportNum.setUserNum(userInform.getUserNum());
		userNumReportNum.setReportNum(reportNum);
		EstimateSheetVO estimateSheet = dao.getEstimateSheet(userNumReportNum);
		if(estimateSheet==null) {
			return "Member/mainMenuMember";
		}
		model.addAttribute("userNum", userInform.getUserNum());
		model.addAttribute("reportNum", reportNum);
		model.addAttribute("stamp", estimateSheet.getStamp());
		return "Document/readEstimateSheet";
	}
	
	//청구서 작성
	@RequestMapping(value="/writeBill", method=RequestMethod.GET)
	public String writeBillSheet(Model model,int reportNum, HttpSession session){
		String id = (String) session.getAttribute("loginId");
		
		model.addAttribute("reportNum", reportNum);
		model.addAttribute("id", id);
		 
		return "Document/writeBillSheet";
	}
	
	//청구서 저장
	@ResponseBody
	@RequestMapping(value="/saveBill", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String saveEstimateSheet(String jsonStr, HttpSession session) {
		System.out.println("견적서내용:"+jsonStr);
		
		Gson gson = new Gson();
		BillInformVO BillInform = gson.fromJson(jsonStr, BillInformVO.class); //json객체의 vo객체화
		
		SimpleDateFormat billDate_pattern = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat newDate_pattern = new SimpleDateFormat("yyyy.MM.dd");
		
		
		try {
			Date bill_Date = billDate_pattern.parse(BillInform.getBillDate());
			BillInform.setBillDate(newDate_pattern.format(bill_Date)); //청구서에 입력된 날짜 데이터 포멧
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		int result = dao.insertBillSheet(BillInform); //DB에 청구서 정보 저장
		
		if(result == 1) {
			dao.setState(BillInform.getReportNum()); //견적서DB의 청구서 작성여부 변경
			return "success";
		}
		return "error";		
	}
	
	//청구서 열람
	@RequestMapping(value="/readBill", method=RequestMethod.GET)
	public String readBillSheet(Model model,int reportNum){
		
		model.addAttribute("reportNum", reportNum); //청구서와 연결된 견적서 번호
		return "Document/readBillSheet";
	}
	
	//(회원용)회원정보 수정페이지로드
	@RequestMapping(value="/updateMyProfile",method=RequestMethod.GET)
	public String updateMyProfileForm(HttpSession session , Model model) {
		System.out.println("회원정보 업데이트 화면로드");
		UserInformVO userIdPassword = (UserInformVO)session.getAttribute("userInform");
		UserInformVO member = dao.getMember(userIdPassword);
		session.setAttribute("userInform", member);
		
		return "Member/updateMyProfile";
	}
	
	//(회원용)회원정보 수정정보 저장
	@ResponseBody
	@RequestMapping(value="/updateMyProfile",method=RequestMethod.POST)
	public String updateMyProfileForm2( UserInformVO userInform , MultipartFile file, HttpSession session) {
		System.out.println(file.getContentType());
		String path =  session.getServletContext().getRealPath("/resources/stamp");
		String RootName="OZDemo";
		if(path.contains("interline_OZDemo")) {RootName="interline_OZDemo";}
		path = path.replace(File.separatorChar+RootName+File.separatorChar+"resources"+File.separatorChar+"stamp", "");
		path = path + "/files/OZDemoEstimateSheet/stamp";
		path = path.replace('/', File.separatorChar);
		System.out.println("저장경로:"+path);
		System.out.println("회원정보 업데이트 실행"); 
		System.out.println("받아온 회원수정정보:" +userInform); 
		if (!file.isEmpty()) {		//파일 관련 일들  이름을 다시지어준다던가 결로를 정해준다던가 복사해서 저장한다던가 자부자리한것들이많아 하나의 객체로 조종하는게 편하다.
			String savedfile = FileService.saveFile(file, path, userInform.getUserId());
			System.out.println(savedfile+"가 저장되었습니다.");
			userInform.setStampFileName(savedfile);
		}
		
		try {
			dao.updateMyProfile(userInform);
			UserInformVO newInform = dao.getMember(userInform);
			session.setAttribute("userInform", newInform);
		} catch (Exception e) {
			
		}
		
		return "自社情報を修正しました。";
	}
	
	//다운로드
	@RequestMapping(value = "/updateMyProfile/download", method = RequestMethod.GET)
	public String fileDownload(HttpSession session, String fileName, Model model, HttpServletResponse response) {	//response쓰임을 잘보자. 요청과 응답에 관련된 클레스
		String path = session.getServletContext().getRealPath("/resources/stamp/sample");
		if(fileName.contains("/")) {return null;}
		try {
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));	
			//항상 전송되는데이터에는 헤더가있음 "Content-Disposition"종류의 파일이고 
			//" attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8") 이라는 파일 name을 갖고 있다 는뜻.
			//생각해보면 받을때는 MultipartFile객체로 받으니 파일에 대한 정보를 다 받지만 
			//아웃 스트림 으로 줄때는 정보가 없다. 브라우져가 유일하게 알아 먹을 방법이 헤더에 명시해주는것. 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//저장된 파일 경로
		String fullPath = path + "/" + fileName;
		
		//서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림
		FileInputStream filein = null;
		ServletOutputStream fileout = null;
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();	//response가 outputstream을 만들면 스트림을 유져-서버 관계를 만든다.  예전에 만들던 outputstream은 내컴퓨터-내컴퓨터
			
			//Spring의 파일 관련 유틸. filein을 fileout으로 복사한다.
			FileCopyUtils.copy(filein, fileout);
			
			filein.close();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
