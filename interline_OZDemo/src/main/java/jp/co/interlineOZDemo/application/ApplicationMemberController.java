package jp.co.interlineOZDemo.application;

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

import jp.co.interlineOZDemo.agreement.AgreementMemberController;
import jp.co.interlineOZDemo.dao.application.ApplicationMemberDAO;
import jp.co.interlineOZDemo.util.ExportReport;
import jp.co.interlineOZDemo.util.GetProperties;
import jp.co.interlineOZDemo.util.PageNavigator;
import jp.co.interlineOZDemo.vo.UserInformVO;
import jp.co.interlineOZDemo.vo.application.ApplicationVO;
import oz.scheduler.SchedulerException;

@Controller
@RequestMapping("/member")
public class ApplicationMemberController {
private static final Logger logger = LoggerFactory.getLogger(AgreementMemberController.class);
	
	private static final int countPerPage=20;	
	private static final int pagePerGroup=10;
	private static final SimpleDateFormat oldDate_pattern = new SimpleDateFormat("yyyy年MM月dd日");
	private static final SimpleDateFormat newDate_pattern = new SimpleDateFormat("yyyy.MM.dd");
	
	@Autowired
	ApplicationMemberDAO dao;
	
	//memberMain
	@RequestMapping(value = "/applicationMainMenu", method=RequestMethod.GET)
	public String applicationMainMenu() {
		return "Application/Member/applicationMainMenu";
	}
	
	//신청서 리스트 페이지 
	@RequestMapping(value="/applicationList", method=RequestMethod.GET)
	public String getApplicationList(HttpSession session , Model model ,@RequestParam(value="page", defaultValue="1") int page) {
		System.out.println("동의서 리스트 페이지 호출 실행");
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		String device = (String)session.getAttribute("device");
		System.out.println(device);
		//int total = dao.getTotalAgreement(userInform.getUserNum());
		//PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);
		//ArrayList<AgreementAgreementVO> AgreementArray = new ArrayList<AgreementAgreementVO>();
		//AgreementArray = dao.getAgreementList(navi.getStartRecord(), navi.getCountPerPage(), userInform.getUserNum());
		//model.addAttribute("AgreementArray",AgreementArray);
		//model.addAttribute("pn", navi);
		//System.out.println(AgreementArray.toString());
		System.out.println("견적서 리스트 페이지 호출 완료");
		return "Application/Document/applicationList";
	}
	
	@RequestMapping(value = "/pdfTest", method=RequestMethod.GET)
	public String applicationPDFTest() {
		
		GetProperties properties= new GetProperties();
		String ozId="admin";
		String ozPW="admin1";
		String OZserverURL="http://"+properties.getOzIP()+"/oz80/server";
		String ipScheduler=properties.getOzIP().split(":")[0];
		int portScheduler=9521;
		ExportReport export=new ExportReport(ozId, ozPW, OZserverURL, ipScheduler, portScheduler);
		String nameOzr="OZDemo_Application/pdfTest.ozr";
		String[] ozrParamValue = null;
		//String nameOdi ="readEstimateSheet";
		//String[] odiParamValue = {"userNum="+estimateSheetVO.getUserNum(),"reportNum="+reportNum};
		String formatExport = "pdf";
		//String fileNameExport="reportExport"+estimateSheetVO.getReportName()+reportNum;
		String result="";
		JSONObject exportJsonObject = new JSONObject();
		exportJsonObject.put("form2", "aaaa");
		String jsonData = exportJsonObject.toString();
		try {
			result=export.exportMethod(jsonData, nameOzr, ozrParamValue, null, null, formatExport, "testPDF");
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="saveError";
		}
		
		return "Application/pdfTest";
	}
	
	//신청서 작성
	@RequestMapping(value="/writeApplication", method=RequestMethod.GET)
	public String writeMemorandumSheet(Model model,HttpSession session) {
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");

		model.addAttribute("userNum", userInform.getUserNum());
		
		return "Application/Document/writeApplication";
	}
	
	//신청서 저장
	@ResponseBody
	@RequestMapping(value="/saveApplication", method=RequestMethod.POST)
	public String saveApplicationSheet(ApplicationVO applicationVO,Model model,HttpSession session) throws ParseException {
		logger.debug("신청서 저장:{}",applicationVO);
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		
		int nextApplicationNum = dao.nextApplicationNum(userInform.getUserNum());
		
		Date application_Date = oldDate_pattern.parse(applicationVO.getApplicationDate());
		applicationVO.setApplicationDate(newDate_pattern.format(application_Date)); //각서에 입력된 날짜 데이터 포멧
		
		Date birth_Date = oldDate_pattern.parse(applicationVO.getBirth());
		applicationVO.setBirth(newDate_pattern.format(birth_Date)); //각서에 입력된 날짜 데이터 포멧
	
		applicationVO.setUserNum(userInform.getUserNum());
		applicationVO.setReportNum(nextApplicationNum + 1);
		logger.debug("신청서 저장용 정보:{}",applicationVO);
		
		int result = dao.insertApplicationSheet(applicationVO);
		
		if(result == 1) {
			return "success";
		}
		
		return "error";
	}
}
