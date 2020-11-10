package jp.co.interlineOZDemo.agreement;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import jp.co.interlineOZDemo.dao.MemberDAO;
import jp.co.interlineOZDemo.dao.agreement.AgreementMemberDAO;
import jp.co.interlineOZDemo.util.FileService;
import jp.co.interlineOZDemo.util.PageNavigator;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementAgreementVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementMemorandumVO;
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
	
	//동의서 리스트 페이지 
	@RequestMapping(value="/agreementList", method=RequestMethod.GET)
	public String getEstimateSheetList(HttpSession session , Model model ,@RequestParam(value="page", defaultValue="1") int page) {
		System.out.println("동의서 리스트 페이지 호출 실행");
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		String device = (String)session.getAttribute("device");
		System.out.println(device);
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
	
	//동의서 작성 페이지
	@RequestMapping(value="/writeAgreement", method=RequestMethod.GET)
	public String writeAgreement(HttpSession session, Model model) {
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		AgreementUserInformVO agreementUserInform = dao.getUserInform(userInform.getUserNum());
		JSONObject userInformJsonObject = new JSONObject(agreementUserInform);
		String userInformJsonString = userInformJsonObject.toString();
		System.out.println("agreementUserInform:"+userInformJsonString);
		model.addAttribute("userInformJsonString", userInformJsonString);
		String device = (String)session.getAttribute("device");
		if(device.equals("MB")) {
			return "Agreement/Document/writeAgreementMobile";
		}
		return "Agreement/Document/writeAgreement";
	}
	
	//동의서 저장기능
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
	
	// 동의서 열람 페이지 
	@RequestMapping(value="/readAgreement", method={RequestMethod.GET,RequestMethod.POST})
	public String readAgreement(@RequestParam(defaultValue="0")int reportNum, Model model, HttpSession session) {
		System.out.println("동의서 열람 실행");
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		AgreementAgreementVO userNumReportNum = new AgreementAgreementVO();
		userNumReportNum.setUserNum(userInform.getUserNum());
		userNumReportNum.setReportNum(reportNum);
		AgreementAgreementVO agreement = dao.getAgreement(userNumReportNum);
		if(agreement==null) {
			System.out.println("reportNum:"+reportNum+"  userNum:"+userInform.getUserNum()+"  열람하려는 문서가 없습니다.");
			return "redirect:/member/agreementMainMenu";
		} 
		
		model.addAttribute("userNum", userInform.getUserNum());
		model.addAttribute("reportNum", reportNum);
		String device = (String)session.getAttribute("device");
		if(device.equals("MB")) {
			return "Agreement/Document/readAgreementMobile";
		}
		return "Agreement/Document/readAgreement";
	}
	
	//동의서 삭제
	@ResponseBody
	@RequestMapping(value="/deleteAgreement", method=RequestMethod.GET)
	public String deleteAgreement(int[] reportNum, Model model, HttpSession session) {
		System.out.println("삭제 실행.");
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		int userNum=userInform.getUserNum();	
		System.out.println("받아온 reportNum:"+reportNum.toString()+",userNum:"+userNum);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("userNum", userNum);
		hashMap.put("reportNum", reportNum);
		dao.deleteAgreement(hashMap);
		dao.deleteMemorandum(hashMap);
		System.out.println("삭제 완료.");
		return "Agreement/Document/agreementMainMenu";
	}
	
	//정보수정 페이지
	@RequestMapping(value="/agreementUpdateMyProfile",method=RequestMethod.GET)
	public String agreementUpdateMyProfileForm(HttpSession session , Model model) {
		System.out.println("회원정보 업데이트 화면로드");
		UserInformVO userIdPassword = (UserInformVO)session.getAttribute("userInform");
		
		UserInformVO member = dao.getMemberByAgreementSys(userIdPassword);
		model.addAttribute("userInform", member);
		AgreementUserInformVO agreementUserInform = dao.getAgreementUserInform(member.getUserNum());
		model.addAttribute("agreementUserInform", agreementUserInform);
		return "Agreement/Member/agreementUpdateMyProfile";
	}
	
	//(회원용)회원정보 수정정보 저장
	@ResponseBody
	@RequestMapping(value="/agreementUpdateMyProfile",method=RequestMethod.POST)
	public String agreementUpdateMyProfileForm2(HttpSession session, AgreementUserInformVO agreementUserInform, String password ) {
		System.out.println("수정정보 저장");
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		String oldPassword = userInform.getPassword();
		agreementUserInform.setUserNum(userInform.getUserNum());
		dao.updateAgreementUserInform(agreementUserInform);
		System.out.println("동의서시스템 유저정보를 수정하였습니다.");
		if(!oldPassword.equals(password)) {
			userInform.setPassword(password);
			dao.updatePasswordByAgreementSys(userInform);
			System.out.println("페스워드를 수정하였습니다.");
			session.setAttribute("userInform", userInform);
		}
		//userInform = dao.getUserInformByAgreementSys(userInform.getUserNum());
		//session.setAttribute("userInform", userInform);
		
		return "自社情報を修正しました。";
	}


	//각서 작성
	@RequestMapping(value="/writeMemorandum", method=RequestMethod.GET)
	public String writeMemorandumSheet(Model model,HttpSession session) {
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");

		model.addAttribute("userNum", userInform.getUserNum());
		
		String device = (String)session.getAttribute("device");
		if(device.equals("MB")) {
			return "Agreement/Document/writeMemorandumMobile";
		}
		return "Agreement/Document/writeMemorandum";
	}
	
	//각서 저장
	@ResponseBody
	@RequestMapping(value="/saveMemorandum", method=RequestMethod.POST)
	public String saveMemorandumSheet(AgreementMemorandumVO memorandumVO,Model model,HttpSession session) throws ParseException {
		logger.debug("각서 저장:{}",memorandumVO);
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");

		Date memorandum_Date = oldDate_pattern.parse(memorandumVO.getMemorandumDate());
		memorandumVO.setMemorandumDate(newDate_pattern.format(memorandum_Date)); //각서에 입력된 날짜 데이터 포멧
		
		int nextReportNum = dao.nextReportNum(userInform.getUserNum());
		
		memorandumVO.setReportNum(nextReportNum + 1);
		memorandumVO.setUserNum(userInform.getUserNum());
		memorandumVO.setSort("覚書");
		
		logger.debug("각서 정보:{}",memorandumVO);
		int result = dao.insertMemorandumSheet(memorandumVO);
		
		if(result == 1) {
			return "success";
		}
		
		return "error";
	}
	
	//각서 읽기
	@RequestMapping(value="/readMemorandum", method= {RequestMethod.GET,RequestMethod.POST})
	public String readMemorandumSheet(@RequestParam(defaultValue="0")int reportNum,Model model,HttpSession session) {
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		
		AgreementMemorandumVO userNumReportNum = new AgreementMemorandumVO();
		userNumReportNum.setUserNum(userInform.getUserNum());
		userNumReportNum.setReportNum(reportNum);
		AgreementMemorandumVO memorandum = dao.getMemorandum(userNumReportNum);
		
		if(memorandum==null) {
			System.out.println("reportNum:"+reportNum+"  userNum:"+userInform.getUserNum()+"  열람하려는 문서가 없습니다.");
			return "redirect:/member/agreementMainMenu";
		} 
		
		model.addAttribute("userNum", userInform.getUserNum());
		model.addAttribute("reportNum", reportNum);
		
		String device = (String)session.getAttribute("device");
		if(device.equals("MB")) {
			return "Agreement/Document/readMemorandumMobile";
		}
		return "Agreement/Document/readMemorandum";
	}
}
