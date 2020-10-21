package jp.co.interlineOZDemo.dao.agreement;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.interlineOZDemo.vo.UserInformVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementAgreementVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementMemorandumVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementUserInformVO;

@Repository
public class AgreementMemberDAO {

	@Autowired
	SqlSession session;

	public int getTotalAgreement(int userNum) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		int result = mapper.getTotalAgreement(userNum);
		return result;
	}

	public ArrayList<AgreementAgreementVO> getAgreementList(int st, int ctt, int userNum) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		RowBounds rbs = new RowBounds(st,ctt);
		ArrayList<AgreementAgreementVO> agreementArray=mapper.getAgreementList(rbs, userNum);
		return agreementArray;
	}

	public AgreementUserInformVO getUserInform(int userNum) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		AgreementUserInformVO agreementUserInform = mapper.getAgreementUserInform(userNum);
		return agreementUserInform;
	}

	public int saveAgreement(AgreementAgreementVO agreementVO) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		int result = mapper.saveAgreement(agreementVO);
		return result;
	}
	
	public int nextReportNum(int userNum) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		int result = mapper.nextReportNum(userNum);
		return result;
	}

	public UserInformVO getMemberByAgreementSys(UserInformVO userIdPassword) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		UserInformVO userInform = mapper.getMemberByAgreementSys(userIdPassword);
		return userInform;
	}

	public AgreementUserInformVO getAgreementUserInform(int userNum) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		AgreementUserInformVO agreementUserInform = mapper.getAgreementUserInform(userNum);
		return agreementUserInform;
	}

	public int updateAgreementUserInform(AgreementUserInformVO agreementUserInform) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		int result = mapper.updateAgreementUserInform(agreementUserInform);
		return result;
	}

	public int updatePasswordByAgreementSys(UserInformVO userInform) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		int result = mapper.updatePasswordByAgreementSys(userInform);
		return result;
	}

	public AgreementAgreementVO getAgreement(AgreementAgreementVO userNumReportNum) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		AgreementAgreementVO agreement = mapper.getAgreement(userNumReportNum);
		return agreement;
	}

	public int deleteAgreement(HashMap<String, Object> hashMap) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		int result = mapper.deleteAgreement(hashMap);
		return result;
	}

	public int deleteMemorandum(HashMap<String, Object> hashMap) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		int result = mapper.deleteMemorandum(hashMap);
		return result;
	}
	
	/*
	public UserInformVO getUserInformByAgreementSys(int userNum) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		UserInformVO userInform = mapper.getUserInformByAgreementSys(userNum);
		return userInform;
	}*/
	
	public int insertMemorandumSheet(AgreementMemorandumVO memorandumInform) {
		AgreementMemberMapper mapper = session.getMapper(AgreementMemberMapper.class);
		int result = mapper.insertMemorandumSheet(memorandumInform);
		return result;
	}
}
