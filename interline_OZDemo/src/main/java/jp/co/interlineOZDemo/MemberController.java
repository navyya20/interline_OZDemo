package jp.co.interlineOZDemo;

import java.util.ArrayList;

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
import jp.co.interlineOZDemo.util.PageNavigator;
import jp.co.interlineOZDemo.vo.BillInformVO;
import jp.co.interlineOZDemo.vo.EstimateItemsVO;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;



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
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		
		int total = dao.getTotalEstimateSheet(userInform.getUserNum());
		PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);
		ArrayList<EstimateSheetVO> estimateSheetArray = new ArrayList<EstimateSheetVO>();
		estimateSheetArray = dao.getEstimateSheetList(navi.getStartRecord(), navi.getCountPerPage(), userInform.getUserNum());
		model.addAttribute("estimateSheetArray",estimateSheetArray);
		model.addAttribute("pn", navi);
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
			ArrayList<EstimateItemsVO> estimateItems = dao.getEstimateItems(reportNum);
			JSONObject estimateSheetJsonObject = new JSONObject(estimateSheet);
			String estimateSheetJsonString = estimateSheetJsonObject.toString();
			model.addAttribute("estimateSheetJsonString", estimateSheetJsonString);
			
			if(estimateItems.size() !=0 && estimateItems != null) {
				JSONObject estimateItemsJsonObject = new JSONObject(estimateItems);
				String estimateItemsJsonString = estimateItemsJsonObject.toString();
				model.addAttribute("estimateItemsJsonString", estimateItemsJsonString);
			}
			
			return "Document/modEstimateSheet";
		}
	
	
	//견적서 저장
	@ResponseBody
	@RequestMapping(value="/saveEstimate", method=RequestMethod.POST)
	public String saveEstimateSheet(EstimateSheetVO estimateSheetVO, String estimateItemsString , HttpSession session) {
		System.out.println("saveEstimateSheet실행");
		System.out.println("견적서내용:"+estimateSheetVO);
		System.out.println("견적아이템들:"+estimateItemsString);

		//견적내용 insert후 reportNum받아옴.
		UserInformVO userInform = (UserInformVO)session.getAttribute("member");
		estimateSheetVO.setUserNum(userInform.getUserNum());
		int reportNum = dao.insertEstimateSheet(estimateSheetVO);
		
		//estimateItemsArray에 작성한 아이템들이 배열로 들어옴.
		ArrayList<EstimateItemsVO> estimateItemsArray = new ArrayList<EstimateItemsVO>();
		JSONObject jsonObject = new JSONObject(estimateItemsString);
		JSONArray jsonArray = (JSONArray)jsonObject.get("itemArray");
		for ( int i = 0 ; i < jsonArray.length() ; i++ ) {
			JSONObject item = (JSONObject)jsonArray.get(i);
			EstimateItemsVO estimateItemsVO = new EstimateItemsVO();
			estimateItemsVO.setReportNum(reportNum);
			estimateItemsVO.setImtemNum(Integer.parseInt((String)item.get("itemNum")));
			estimateItemsVO.setItemName((String)item.get("itemName"));
			estimateItemsVO.setAmount(Integer.parseInt((String)item.get("amount")));
			estimateItemsVO.setUnit((String)item.get("unit"));
			estimateItemsVO.setUnitPrice(Integer.parseInt((String)item.get("unitPrice")));
			estimateItemsVO.setPrice(Integer.parseInt((String)item.get("price")));
			estimateItemsVO.setNote((String)item.get("note"));
			estimateItemsArray.add(estimateItemsVO);
		}
		
		dao.insertEstimateItems(estimateItemsArray);
		
		
		
		
		return "success";
	}
	
	//견적서 열람
	@RequestMapping(value="/readEstimate", method=RequestMethod.GET)
	public String readEstimate() {
		
		return "Document/estimateSheet";
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
