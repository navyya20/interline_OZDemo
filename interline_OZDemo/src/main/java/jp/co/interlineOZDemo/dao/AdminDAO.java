package jp.co.interlineOZDemo.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.interlineOZDemo.vo.UserInformVO;

@Repository
public class AdminDAO {

	@Autowired
	SqlSession session;
	
	public UserInformVO check_Multiple(String check_point, Object check_content) {
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("check_point", check_point);
		map.put("check_content", check_content);
		
		UserInformVO vo = mapper.check_Multiple(map);

		return vo;
	}

	public int registerMember(UserInformVO member) {
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		
		int result = mapper.registerMember(member);
		return result;
	}
	
	public ArrayList<UserInformVO> getMemberList() {
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		
		ArrayList<UserInformVO> list = mapper.getMemberList();
		return list;
	}

	public int updateMember(UserInformVO vo) {
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		
		int n = mapper.updateMember(vo);
		return n;
	}

	public int deleteMember(int userNum) {
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		
		int n = mapper.deleteMember(userNum);
		return n;
	}

	public UserInformVO getMember(int userNum) {
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		
		UserInformVO member = mapper.getMember(userNum);
		
		return member;
	}

	public int registerAgreementMember(int userNum) {
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		
		int n = mapper.registerAgreementMember(userNum);
		return n;
	}

}
