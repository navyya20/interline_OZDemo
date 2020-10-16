package jp.co.interlineOZDemo.agreement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.interlineOZDemo.dao.agreement.AgreementMemberDAO;
import jp.co.interlineOZDemo.util.PageNavigator;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;





@Controller
@RequestMapping("/member")
public class AgreementMemberController {
	private static final Logger logger = LoggerFactory.getLogger(AgreementMemberController.class);
	
	private static final int countPerPage=20;	
	private static final int pagePerGroup=10;
	private static final SimpleDateFormat oldDate_pattern = new SimpleDateFormat("yyyy年MM月dd日");
	private static final SimpleDateFormat newDate_pattern = new SimpleDateFormat("yyyy.MM.dd");
	
	@Autowired
	AgreementMemberDAO dao;
	
	//견적서 리스트 페이지 
	@RequestMapping(value="/agreementList", method=RequestMethod.GET)
	public String getEstimateSheetList(HttpSession session , Model model ,@RequestParam(value="page", defaultValue="1") int page) {
		System.out.println("동의서 리스트 페이지 호출 실행");
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		//int total = dao.getTotalAgreement(userInform.getUserNum());
		//PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);
		//ArrayList<EstimateSheetVO> estimateSheetArray = new ArrayList<EstimateSheetVO>();
		//estimateSheetArray = dao.getEstimateSheetList(navi.getStartRecord(), navi.getCountPerPage(), userInform.getUserNum());
		//model.addAttribute("estimateSheetArray",estimateSheetArray);
		//model.addAttribute("pn", navi);
		System.out.println("견적서 리스트 페이지 호출 완료");
		return "Agreement/Document/estimateSheetList";
	}
}
