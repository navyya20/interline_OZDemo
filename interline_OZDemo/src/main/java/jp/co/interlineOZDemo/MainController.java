package jp.co.interlineOZDemo;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String home(HttpSession session) {
		session.removeAttribute("login_id");
		session.invalidate();
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(HttpSession session) {
		session.removeAttribute("login_id");
		session.invalidate();
		
		return "login";
	}
	
	//Login
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public HashMap<String, String> Login(HttpSession session,String userid, String password) {
		logger.debug("login id:{}, pw:{}", userid,password);
		
		UserInformVO member = dao.getMember(userid);
		HashMap<String,String> result = new HashMap<>();
		
		if(member != null && member.getPassword().equals(password)){
			session.setAttribute("login_id", member.getUserId());
			session.setAttribute("user_inform", member);
			
			if(member.getAuthority().equals("a")) {
				result.put("url", "admin/adminMain");		
			}else if(member.getAuthority().equals("u")) {
				result.put("url", "member/memberMain");
			}
		}
		
		if(member == null) {
			result.put("error","存在しないIDです。");
		}else if(!member.getPassword().equals(password)){
			result.put("error","PASSWORDが一致しません。");
		}
		
		return result;
	}
	
	//logout
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String Logout(HttpSession session) {
		
		session.removeAttribute("member");
		session.removeAttribute("login_id");
		session.invalidate();
		
		return "redirect:/";
	}
}
