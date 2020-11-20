package jp.co.interlineOZDemo.application;

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

import jp.co.interlineOZDemo.dao.application.ApplicationMemberDAO;
import jp.co.interlineOZDemo.util.ExportReport;
import jp.co.interlineOZDemo.util.GetProperties;
import jp.co.interlineOZDemo.util.PDFController;
import jp.co.interlineOZDemo.util.PageNavigator;
import jp.co.interlineOZDemo.vo.UserInformVO;
import jp.co.interlineOZDemo.vo.application.ApplicationVO;
import oz.scheduler.SchedulerException;

@Controller
@RequestMapping("/member")
public class ApplicationMemberController {
private static final Logger logger = LoggerFactory.getLogger(ApplicationMemberController.class);
	
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
		int total = dao.getTotalApplication(userInform.getUserNum());
		PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);
		ArrayList<ApplicationVO> applicationArray = new ArrayList<ApplicationVO>();
		applicationArray = dao.getApplicationList(navi.getStartRecord(), navi.getCountPerPage(), userInform.getUserNum());
		model.addAttribute("ApplicationArray",applicationArray);
		model.addAttribute("pn", navi);
		//System.out.println(AgreementArray.toString());
		System.out.println("견적서 리스트 페이지 호출 완료");
		return "Application/Document/applicationList";
	}
	
	@RequestMapping(value = "/pdfTest", method=RequestMethod.GET)
	public String applicationPDFTest() {
		String rootPath = "C:/Apache Software Foundation/Tomcat 8.5";
		String pdfPath = rootPath+"/webapps/files/application/pdf/1_Application_uuu.pdf";
		rootPath = rootPath.replace('/', File.separatorChar);
		PDFController pdfController = new PDFController();
		ArrayList<String> jpgPaths = pdfController.PDFtoJPG(pdfPath, 150);
		String outPDFPath = rootPath+"/webapps/files/application/pdf/converted.pdf";
		outPDFPath = outPDFPath.replace('/', File.separatorChar);
		pdfController.JPGtoPDF(jpgPaths, outPDFPath);
		return "Application/pdfTest";
	}
	
	//신청서 작성
	@RequestMapping(value="/writeApplication", method=RequestMethod.GET)
	public String writeMemorandumSheet(Model model,HttpSession session) {
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");

		model.addAttribute("userNum", userInform.getUserNum());
		String device = (String)session.getAttribute("device");
		
		if(device.equals("MB")) {
			return "Application/Document/writeApplicationMobile";
		}
		return "Application/Document/writeApplication";

	}
	
	//신청서 저장
	@ResponseBody
	@RequestMapping(value="/saveApplication", method=RequestMethod.POST)
	public String saveApplicationSheet(ApplicationVO applicationVO,String jsonString,Model model,HttpSession session) throws ParseException {
		logger.debug("신청서 저장:{}",applicationVO);
		logger.debug("신청서 저장2:{}",jsonString);
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		HashMap<String, Object> hashMap = new HashMap<>();
		
		int nextApplicationNum = dao.nextApplicationNum(userInform.getUserNum());
		
		Date application_Date = oldDate_pattern.parse(applicationVO.getApplicationDate());
		applicationVO.setApplicationDate(newDate_pattern.format(application_Date)); //각서에 입력된 날짜 데이터 포멧
		
		Date birth_Date = oldDate_pattern.parse(applicationVO.getBirth());
		applicationVO.setBirth(newDate_pattern.format(birth_Date)); //각서에 입력된 날짜 데이터 포멧
	
		applicationVO.setUserNum(userInform.getUserNum());
		applicationVO.setReportNum(nextApplicationNum + 1);
		
		int result = dao.insertApplicationSheet(applicationVO);
		
		if(result == 1) {
			
			GetProperties properties= new GetProperties();
			String ozId="admin";
			String ozPW="admin1";
			String OZserverURL="http://"+properties.getOzIP()+"/oz80/server";
			String ipScheduler=properties.getOzIP().split(":")[0];
			int portScheduler=9521;
			ExportReport export=new ExportReport(ozId, ozPW, OZserverURL, ipScheduler, portScheduler);
			String nameOzr="OZDemo_Application/readApplication.ozr";
			String[] ozrParamValue = null;
			//String nameOdi ="readEstimateSheet";
			//String[] odiParamValue = {"userNum="+estimateSheetVO.getUserNum(),"reportNum="+reportNum};
			String formatExport = "pdf";
			//String fileNameExport="reportExport"+estimateSheetVO.getReportName()+reportNum;
			String ozExportResult="";
			
			try {
				ozExportResult=export.exportMethod(jsonString, nameOzr, ozrParamValue, null, null, formatExport, (nextApplicationNum+1)+"_Application_"+userInform.getUserId());
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ozExportResult="saveError";
			}
			logger.debug("ozExportResult:{}",ozExportResult);
			
			hashMap.put("reportNum", (nextApplicationNum+1));
			hashMap.put("userNum", userInform.getUserNum());
			hashMap.put("PDFPath", "http://"+properties.getOzIP()+"/files/application/pdf/"+(nextApplicationNum+1)+"_Application_"+userInform.getUserId()+".pdf");
			
			dao.setPDFPath(hashMap);
			return "success";
		}

		
		return "error";
	}
	

	//신청서 삭제
	@ResponseBody
	@RequestMapping(value="/deleteApplication", method=RequestMethod.GET)
	public String deleteApplication(int[] reportNum, Model model, HttpSession session) {
		System.out.println("삭제 실행.");
		UserInformVO userInform = (UserInformVO)session.getAttribute("userInform");
		int userNum=userInform.getUserNum();	
		System.out.println("받아온 reportNum:"+reportNum.toString()+",userNum:"+userNum);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("userNum", userNum);
		hashMap.put("reportNum", reportNum);
		dao.deleteApplication(hashMap);
		System.out.println("삭제 완료.");
		return "Application/Document/applicationMainMenu";
	}
	
	
	
}
