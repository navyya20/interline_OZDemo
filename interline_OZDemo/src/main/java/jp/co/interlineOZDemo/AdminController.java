package jp.co.interlineOZDemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.interlineOZDemo.dao.AdminDAO;
import jp.co.interlineOZDemo.vo.UserInformVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	SimpleDateFormat old_pattern = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	SimpleDateFormat new_pattern = new SimpleDateFormat("yyyy.MM.dd");
	
	@Autowired
	AdminDAO dao;
	
	//관리자 메인
	@RequestMapping(value="/adminMain", method=RequestMethod.GET)
	public String mainMenuMember() {
		
		return "Admin/mainMenuAdmin";
	}
	
	//회원 리스트
	@RequestMapping(value="/memberList", method=RequestMethod.GET)
	public String getMemberList(Model model){
		ArrayList<UserInformVO> list = dao.getMemberList();		
		
		try {
			for(int n=0; n<list.size();n++) {		
				Date Start_Date = old_pattern.parse(list.get(n).getStartDate());
				list.get(n).setStartDate(new_pattern.format(Start_Date));	
			}
		} catch (ParseException e) {
			logger.error(""+e);
			return "redirect:/500Error";
		}
		
		model.addAttribute("member", list);
		return "Admin/memberList";
	}
	
	//회원등록 폼
	@RequestMapping(value="/registerMember", method=RequestMethod.GET)
	public String registerMemberForm() {
		
		return "Admin/registerMember";
	}
	
	//회원번호와 회원아이디 중복검사
	@ResponseBody
	@RequestMapping(value="/check_multiple", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String check_Multiple(int userNum, String userId) {
		logger.debug("userNum:{},userid:{}",userNum,userId);
		UserInformVO check_result = null;
		
		check_result = dao.check_Multiple("check_Num",userNum); //회원번호 중복 확인
		if(check_result != null) {
			return "存在する会員番号です。";
		}
		
		check_result = dao.check_Multiple("check_Id",userId); //회원아이디 중복 확인
		if(check_result != null) {
			return "存在する会員IDです。";
		}

		return "成功";
	}
	
	//회원 등록
	@RequestMapping(value="/registerMember", method=RequestMethod.POST)
	public String registerMember(UserInformVO member) {
		logger.debug("insertMember:{}",member);
		int resultMember = dao.registerMember(member);
		
		if(resultMember == 1) {
			logger.debug("ユーザー登録成功");
			int resultAgreementMember = dao.registerAgreementMember(member.getUserNum());
			
			if(resultAgreementMember == 1) {
				logger.debug("同意書のユーザー登録成功");
			}
		}
		return "redirect:/admin/memberList";
	}
	
	//(관리자용)회원정보 수정폼
	@RequestMapping(value="/updateMember", method=RequestMethod.GET)
	public String updateMemberForm(Model model,int userNum) {
		logger.debug("updateMember:{}",userNum);
		
		UserInformVO sel_member = dao.getMember(userNum);
		
		model.addAttribute("member",sel_member);
		return "Admin/updateMember";
	}
	
	//회원정보 수정
	@RequestMapping(value="/updateMember", method=RequestMethod.POST)
	public String updateMember(UserInformVO member) {
		logger.debug("updateMember:{}",member);
		
		int result = dao.updateMember(member); //DB에서 해당 회원정보 수정
		
		if(result == 1) {
			logger.debug("修正成功");
		}
		
		return "redirect:/admin/memberList";
	}
	
	//회원정보 삭제
	@RequestMapping(value="/deleteMember", method=RequestMethod.POST)
	public String deleteMember(int userNum) {
		logger.debug("userNum:{}",userNum);
		
		
		int result = dao.deleteMember(userNum); //DB에서 해당 회원정보 삭제
		  
		if(result == 1) { 
			logger.debug("削除成功"); 
		}
		 
		
		return "redirect:/admin/memberList";
	}
}
