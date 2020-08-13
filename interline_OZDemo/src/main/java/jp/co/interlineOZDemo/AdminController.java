package jp.co.interlineOZDemo;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.interlineOZDemo.dao.AdminDAO;
import jp.co.interlineOZDemo.vo.UserInformVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminDAO dao;
	
	//관리자 메인
	@RequestMapping(value="/adminMain", method=RequestMethod.GET)
	public String mainMenuMember() {
		
		return "Admin/mainMenuAdmin";
	}
	
	//회원 리스트
	@RequestMapping(value="/memberList", method=RequestMethod.GET)
	public String getMemberList(Model model) {
		ArrayList<UserInformVO> list = dao.getMemberList();
		
		model.addAttribute("member", list);
		return "Admin/memberList";
	}
	
	//회원등록 폼
	@RequestMapping(value="/registerMember", method=RequestMethod.GET)
	public String registerMemberForm() {
		
		return "Admin/registerMember";
	}
	
	@RequestMapping(value="/registerMember", method=RequestMethod.POST)
	public String registerMember(UserInformVO member) {
		logger.debug("insertMember:{}",member);
		int result = dao.registerMember(member);
		
		if(result == 1) {
			logger.debug("登録成功");
		}
		return "redirect:/admin/memberList";
	}
	
	//(관리자용)회원정보 수정
	@RequestMapping(value="/updateMember", method=RequestMethod.GET)
	public String updateMemberForm() {
		
		return "Admin/updateMember";
	}
	
	@RequestMapping(value="/updateMember", method=RequestMethod.POST)
	public String updateMember(UserInformVO member) {
		logger.debug("updateMember:{}",member);
		
		int result = dao.updateMember(member);
		
		if(result == 1) {
			logger.debug("登録成功");
		}
		
		return "redirect:/admin/memberList";
	}
	
	@RequestMapping(value="/deleteMember", method=RequestMethod.POST)
	public String deleteMember(UserInformVO member) {
		logger.debug("updateMember:{}",member);
		
		int result = dao.deleteMember(member);
		
		if(result == 1) {
			logger.debug("登録成功");
		}
		
		return "redirect:/admin/memberList";
	}
}
