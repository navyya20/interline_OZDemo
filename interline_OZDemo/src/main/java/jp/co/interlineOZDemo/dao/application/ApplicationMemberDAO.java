package jp.co.interlineOZDemo.dao.application;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.interlineOZDemo.dao.agreement.AgreementMemberMapper;
import jp.co.interlineOZDemo.vo.agreement.AgreementAgreementVO;
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

	public int getTotalApplication(int userNum) {
		ApplicationMemberMapper mapper = session.getMapper(ApplicationMemberMapper.class);
		int result = mapper.getTotalApplication(userNum);
		return result;
	}

	public ArrayList<ApplicationVO> getApplicationList(int st, int ctt, int userNum) {
		ApplicationMemberMapper mapper = session.getMapper(ApplicationMemberMapper.class);
		RowBounds rbs = new RowBounds(st,ctt);
		ArrayList<ApplicationVO> applicationArray = mapper.getApplicationList(rbs, userNum);
		return applicationArray;
	}

	public int deleteApplication(HashMap<String, Object> hashMap) {
		ApplicationMemberMapper mapper = session.getMapper(ApplicationMemberMapper.class);
		int result = mapper.deleteApplication(hashMap);
		return result;
	}

}
