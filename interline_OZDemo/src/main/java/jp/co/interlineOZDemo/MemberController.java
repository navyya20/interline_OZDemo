package jp.co.interlineOZDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.interlineOZDemo.vo.EstimateSheetVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
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
	public String saveEstimateSheet(EstimateSheetVO estimateSheetVO, String estimateItemsString) {
		System.out.println("saveEstimateSheet");
		System.out.println(estimateSheetVO);
		System.out.println(estimateItemsString);
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
