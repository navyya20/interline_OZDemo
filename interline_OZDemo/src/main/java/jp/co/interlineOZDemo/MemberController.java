package jp.co.interlineOZDemo;

import java.util.ArrayList;
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
	
	private static final int countPerPage=10;		
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
		
		//파일 데몬서버에 떨구기
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
		
		//이메일 보내기
		if (!email.equals("") && email!=null) {
			System.out.println("이메일을 보냅니다.");
			System.out.println("emailAddress:" + email);
			SendMail mail = new SendMail();
			String title = "OZ_Demoの件";
			String text = "作成した見積書をお送りいたします。";
			String html = "";
			GetProperties getProperties = new GetProperties();
			String filePath = getProperties.getServerPath()+"\\webapps\\ozsch80\\Repository\\pdf\\"+"reportExport"+estimateSheetVO.getReportName()+reportNum+".pdf";
			String fileName = "reportExport"+estimateSheetVO.getReportName()+reportNum+".pdf";
			mail.MailSend(email, title, text, html, filePath, fileName);
		}
		//SendMail mail = new SendMail();
		//mail.MailSend(emailAddress, title, text, html, filePath, fileName);
		//MailSend(String emailAddress ,String title,String text, String html ,String filePath, String fileName)
		
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
	public String writeBillSheet(Model model,int reportNum){
		
		model.addAttribute("reportNum", reportNum);
		 
		return "Document/writeBillSheet";
	}
	
	@ResponseBody
	@RequestMapping(value="/saveBill", method=RequestMethod.POST)
	public String saveEstimateSheet(BillInformVO BillInform , HttpSession session) {
		System.out.println("saveEstimateSheet실행");
		System.out.println("견적서내용:"+BillInform);
	
		return "success";
	}
	
	@RequestMapping(value="/readBill", method=RequestMethod.GET)
	public String readBillSheet(Model model,int reportNum){
		
		model.addAttribute("reportNum", reportNum);	 
		return "Document/BillSheet";
	}
	
	//(회원용)회원정보 수정페이지로드
	@RequestMapping(value="/updateMyProfile",method=RequestMethod.GET)
	public String updateMyProfileForm() {
		System.out.println("회원정보 업데이트 화면로드");
		return "Member/updateMyProfile";
	}
	
	//(회원용)회원정보 수정정보 저장
	@RequestMapping(value="/updateMyProfile",method=RequestMethod.POST)
	public String updateMyProfileForm2(UserInformVO userInform ) {
		System.out.println("회원정보 업데이트 실행");
		System.out.println("받아온 회원수정정보:" + userInform);
		dao.updateMyProfile(userInform);
		return "Document/estimateSheetList";
	}
}
