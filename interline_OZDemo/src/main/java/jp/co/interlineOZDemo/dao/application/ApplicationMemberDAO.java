package jp.co.interlineOZDemo.dao.application;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationMemberDAO {

	@Autowired
	SqlSession session;

	public void functionName() {
		ApplicationMemberMapper mapper = session.getMapper(ApplicationMemberMapper.class);	
	}

}
