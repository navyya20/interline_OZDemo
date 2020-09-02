package jp.co.interlineOZDemo;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.interlineOZDemo.dao.MemberDAO;
import jp.co.interlineOZDemo.util.ExportReport;
import jp.co.interlineOZDemo.util.GetProperties;
import jp.co.interlineOZDemo.util.PageNavigator;
import jp.co.interlineOZDemo.util.SendMail;
import jp.co.interlineOZDemo.vo.BillInformVO;
import jp.co.interlineOZDemo.vo.EstimateItemsVO;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;
import oz.scheduler.SchedulerException;





@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private static final int countPerPage=20;	
	private static final int pagePerGroup=10;
	
	@Autowired
	MemberDAO dao;
	
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
			
			return "Document/modEstimateSheet";
		}
	
	
	//견적서 저장
	@ResponseBody
	@RequestMapping(value="/saveEstimate", method=RequestMethod.POST)
	public String saveEstimateSheet(EstimateSheetVO estimateSheetVO, String estimateItemsString , HttpSession session, String email) {
		System.out.println("saveEstimateSheet실행");
		System.out.println("견적서내용:"+estimateSheetVO);
		System.out.println("견적아이템들:"+estimateItemsString);
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
				estimateItemsVO.setPrice(Integer.parseInt((String)item.get("price")));
				estimateItemsVO.setNote((String)item.get("note"));
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
	
	@ResponseBody
	@RequestMapping(value="/saveBill", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String saveEstimateSheet(String jsonStr, HttpSession session) {
		System.out.println("견적서내용:"+jsonStr);
		
		Gson gson = new Gson();
		BillInformVO BillInform = gson.fromJson(jsonStr, BillInformVO.class);
		
		SimpleDateFormat billDate_pattern = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat newDate_pattern = new SimpleDateFormat("yyyy.MM.dd");
		
		
		try {
			Date bill_Date = billDate_pattern.parse(BillInform.getBillDate());
			BillInform.setBillDate(newDate_pattern.format(bill_Date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		int result = dao.insertBillSheet(BillInform);
		
		if(result == 1) {
			dao.setState(BillInform.getReportNum());
			return "success";
		}
		return "error";		
	}
	
	@RequestMapping(value="/readBill", method=RequestMethod.GET)
	public String readBillSheet(Model model,int reportNum){
		
		model.addAttribute("reportNum", reportNum);	 
		return "Document/readBillSheet";
	}
	
	//(회원용)회원정보 수정페이지로드
	@RequestMapping(value="/updateMyProfile",method=RequestMethod.GET)
	public String updateMyProfileForm(HttpSession session) {
		System.out.println("회원정보 업데이트 화면로드");
		UserInformVO userIdPassword = (UserInformVO)session.getAttribute("userInform");
		UserInformVO member = dao.getMember(userIdPassword);
		session.setAttribute("userInform", member);
		return "Member/updateMyProfile";
	}
	
	//(회원용)회원정보 수정정보 저장
	@ResponseBody
	@RequestMapping(value="/updateMyProfile",method=RequestMethod.POST)
	public String updateMyProfileForm2(UserInformVO userInform ) {
		System.out.println("회원정보 업데이트 실행");
		System.out.println("받아온 회원수정정보:" + userInform);
		dao.updateMyProfile(userInform);
		
		return "自社情報を修正しました。";
	}
}
