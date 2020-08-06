package jp.co.interlineOZDemo;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.interlineOZDemo.dao.MemberDAO;
import jp.co.interlineOZDemo.vo.UserInformVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	MemberDAO dao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "login";
	}
	
	//Login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Login(HttpSession session,String id, String pw) {
		
		UserInformVO result_user = dao.getUser(id);
		
		if(result_user.getPassword().equals(pw)) {
			session.setAttribute("login_id", result_user.getUserid());
			
			if(result_user.getAuthority() == "a") {
				return "redirect:/admin/adminMain";
			}else if(result_user.getAuthority() == "u") {
				return "redirect:/member/memberMain";
			}
		}
		
		return "redirect:/";
	}
	
	//logout
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String Logout(HttpSession session) {
		
		session.removeAttribute("");
		session.invalidate();
		
		return "redirect:/";
	}
}
