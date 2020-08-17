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
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.interlineOZDemo.dao.MemberDAO;
import jp.co.interlineOZDemo.vo.EstimateItemsVO;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;



@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberDAO dao;
	
	//memberMain
	@RequestMapping(value = "/memberMain", method=RequestMethod.GET)
	public String mainMenuMember() {
		
		return "Member/mainMenuMember";
	}
	
	//견적서 리스트
	@RequestMapping(value="/estimateSheetList", method=RequestMethod.GET)
	public String getEstimateSheetList() {
		
		return "Document/estimateSheetList";
	}
	
	//견적서 작성
	@RequestMapping(value="/writeEstimate", method=RequestMethod.GET)
	public String writeEstimateSheet() {
		
		return "Document/writeEstimateSheet";
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
	
	@RequestMapping(value="/readBill", method=RequestMethod.GET)
	public String readBillSheet(Model model,int reportNum){
		
		model.addAttribute("reportNum", reportNum);	 
		return "Document/BillSheet";
	}
	
	//(회원용)회원정보 수정
	@RequestMapping(value="/updateMyProfile",method=RequestMethod.GET)
	public String updateMyProfileForm() {
		
		return "Member/updateMyProfile";
	}
}
