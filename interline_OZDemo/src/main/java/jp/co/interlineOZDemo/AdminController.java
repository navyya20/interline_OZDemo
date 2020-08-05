package jp.co.interlineOZDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	//관리자 메인
	@RequestMapping(value="/adminMain", method=RequestMethod.GET)
	public String mainMenuMember() {
		
		return "Admin/mainMenuMember";
	}
	
	//회원 리스트
	@RequestMapping(value="/memberList", method=RequestMethod.GET)
	public String getMemberList() {
		
		return "Admin/memberList";
	}
	
	//회원등록 폼
	@RequestMapping(value="/registerMember", method=RequestMethod.GET)
	public String registerMember() {
		
		return "Admin/registerMember";
	}
	
	//(관리자용)회원정보 수정
	@RequestMapping(value="/updateMember", method=RequestMethod.GET)
	public String updateMember() {
		
		return "Admin/updateMember";
	}
}
