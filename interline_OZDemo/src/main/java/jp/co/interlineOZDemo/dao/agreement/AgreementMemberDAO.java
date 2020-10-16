package jp.co.interlineOZDemo.dao.agreement;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.interlineOZDemo.dao.MemberMapper;
import jp.co.interlineOZDemo.vo.BillInformVO;
import jp.co.interlineOZDemo.vo.EstimateItemsVO;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementAgreementVO;

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
	
}
