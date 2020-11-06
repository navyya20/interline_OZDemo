package jp.co.interlineOZDemo.dao.application;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.interlineOZDemo.vo.application.ApplicationVO;

@Repository
public class ApplicationMemberDAO {

	@Autowired
	SqlSession session;

	public void functionName() {
		ApplicationMemberMapper mapper = session.getMapper(ApplicationMemberMapper.class);	
	}

	public int insertApplicationSheet(ApplicationVO applicationVO) {
		ApplicationMemberMapper mapper = session.getMapper(ApplicationMemberMapper.class);
		
		int result = mapper.insertApplicationSheet(applicationVO);
		
		return result;
	}

	public int nextApplicationNum(int userNum) {
		ApplicationMemberMapper mapper = session.getMapper(ApplicationMemberMapper.class);
		
		int result = mapper.nextApplicationNum(userNum);
		
		return result;
	}

}
