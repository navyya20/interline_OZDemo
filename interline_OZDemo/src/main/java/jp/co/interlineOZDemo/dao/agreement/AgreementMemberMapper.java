package jp.co.interlineOZDemo.dao.agreement;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import jp.co.interlineOZDemo.vo.BillInformVO;
import jp.co.interlineOZDemo.vo.EstimateItemsVO;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementAgreementVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementMemorandumVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementUserInformVO;

public interface AgreementMemberMapper {

	int getTotalAgreement(int userNum);

	ArrayList<AgreementAgreementVO> getAgreementList(RowBounds rbs, int userNum);

	AgreementUserInformVO getAgreementUserInform(int userNum);

	int saveAgreement(AgreementAgreementVO agreementVO);
	int nextReportNum(int userNum);

	UserInformVO getMemberByAgreementSys(UserInformVO userIdPassword);

	int updateAgreementUserInform(AgreementUserInformVO agreementUserInform);

	int updatePasswordByAgreementSys(UserInformVO userInform);

	AgreementAgreementVO getAgreement(AgreementAgreementVO userNumReportNum);

	int deleteAgreement(HashMap<String, Object> hashMap);

	int deleteMemorandum(HashMap<String, Object> hashMap);

	//UserInformVO getUserInformByAgreementSys(int userNum);
	
	int insertMemorandumSheet(AgreementMemorandumVO memorandumInform);

	AgreementMemorandumVO getMemorandum(AgreementMemorandumVO userNumReportNum);
}
