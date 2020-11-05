package jp.co.interlineOZDemo.application;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.interlineOZDemo.agreement.AgreementMemberController;
import jp.co.interlineOZDemo.dao.application.ApplicationMemberDAO;

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
	
}
