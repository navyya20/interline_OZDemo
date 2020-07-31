package jp.co.interlineOZDemo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.interlineOZDemo.vo.UserVO;

@Repository
public class UserDAO {

	@Autowired
	SqlSession session;
	
	public UserVO getUser(String id) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		UserVO result_user = mapper.getUser(id);
		
		return result_user;
	}
}
