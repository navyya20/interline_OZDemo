package jp.co.interlineOZDemo.dao;

import java.util.ArrayList;
import java.util.HashMap;

import jp.co.interlineOZDemo.vo.UserInformVO;

public interface AdminMapper {

	ArrayList<UserInformVO> getMemberList();
	
	int registerMember(UserInformVO member);

	int updateMember(UserInformVO vo);

	int deleteMember(int userNum);

	UserInformVO getMember(int userNum);

	UserInformVO check_Multiple(HashMap<String, Object> map);

	int registerAgreementMember(int userNum);

}
