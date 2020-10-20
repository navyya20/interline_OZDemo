package jp.co.interlineOZDemo.agreement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

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

import jp.co.interlineOZDemo.dao.agreement.AgreementMemberDAO;
import jp.co.interlineOZDemo.util.PageNavigator;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementAgreementVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementUserInformVO;





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
	
	//memberMain
	@RequestMapping(value = "/agreementMainMenu", method=RequestMethod.GET)
	public String mainMenuMember() {

		return "Agreement/Member/agreementMainMenu";
	}
	
	//견적서 리스트 페이지 
	@RequestMapping(value="/agreementList", method=RequestMethod.GET)
	public String getEstimateSheetList(HttpSession session , Model model ,@RequestParam(value="page", defaultValue="1") int page) {
		System.out.println("동의서 리스트 페이지 호출 실행");
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		int total = dao.getTotalAgreement(userInform.getUserNum());
		PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);
		ArrayList<AgreementAgreementVO> AgreementArray = new ArrayList<AgreementAgreementVO>();
		AgreementArray = dao.getAgreementList(navi.getStartRecord(), navi.getCountPerPage(), userInform.getUserNum());
		model.addAttribute("AgreementArray",AgreementArray);
		model.addAttribute("pn", navi);
		System.out.println(AgreementArray.toString());
		System.out.println("견적서 리스트 페이지 호출 완료");
		return "Agreement/Document/agreementList";
	}
	
	//동의서 작성
	@RequestMapping(value="/writeAgreement", method=RequestMethod.GET)
	public String writeAgreement(HttpSession session, Model model) {
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		AgreementUserInformVO agreementUserInform = dao.getUserInform(userInform.getUserNum());
		JSONObject userInformJsonObject = new JSONObject(agreementUserInform);
		String userInformJsonString = userInformJsonObject.toString();
		System.out.println("agreementUserInform:"+userInformJsonString);
		model.addAttribute("userInformJsonString", userInformJsonString);
		return "Agreement/Document/writeAgreement";
	}
	
	//동의서 저장
	@ResponseBody
	@RequestMapping(value="/saveAgreement", method=RequestMethod.POST)
	public String saveAgreement(AgreementAgreementVO agreementVO, HttpSession session) throws ParseException {
		System.out.println("saveEstimateSheet실행");
		System.out.println("견적서내용:"+agreementVO);
		//年月日로 되어있는 날짜 형식을  . . . 으로 바꿔줌. DB에 넣기위해
		Date agreementDate = oldDate_pattern.parse(agreementVO.getAgreementDate());
		agreementVO.setAgreementDate(newDate_pattern.format(agreementDate));
		//지금까지있는 reportNum의 MAX를 찾아 그것의 +1한값을 reportNum으로 해줌.
		int nextReportNum = dao.nextReportNum(agreementVO.getUserNum());
		agreementVO.setReportNum(nextReportNum + 1);
		System.out.println("nextReportNum:"+nextReportNum);
		int result = dao.saveAgreement(agreementVO);
		return "completed";
	}
}
