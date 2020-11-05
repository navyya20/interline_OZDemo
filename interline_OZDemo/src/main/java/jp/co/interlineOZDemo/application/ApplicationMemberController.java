package jp.co.interlineOZDemo.application;

import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.interlineOZDemo.agreement.AgreementMemberController;
import jp.co.interlineOZDemo.dao.application.ApplicationMemberDAO;
import jp.co.interlineOZDemo.util.ExportReport;
import jp.co.interlineOZDemo.util.GetProperties;
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
}
