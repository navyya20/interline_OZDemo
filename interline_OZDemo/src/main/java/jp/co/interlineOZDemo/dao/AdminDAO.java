package jp.co.interlineOZDemo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.interlineOZDemo.vo.UserInformVO;

@Repository
public class AdminDAO {

	@Autowired
	SqlSession session;

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

	public int deleteMember(UserInformVO vo) {
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		
		int n = mapper.deleteMember(vo);
		return n;
	}
}
