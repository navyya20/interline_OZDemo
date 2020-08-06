package jp.co.interlineOZDemo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.interlineOZDemo.vo.UserInformVO;

@Repository
public class MemberDAO {

	@Autowired
	SqlSession session;
	
	public UserInformVO getMember(String id) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		UserInformVO result_user = mapper.getMember(id);
		
		return result_user;
	}
}
