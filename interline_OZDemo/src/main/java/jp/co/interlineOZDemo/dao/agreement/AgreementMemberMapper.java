package jp.co.interlineOZDemo.dao.agreement;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import jp.co.interlineOZDemo.vo.BillInformVO;
import jp.co.interlineOZDemo.vo.EstimateItemsVO;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementAgreementVO;
import jp.co.interlineOZDemo.vo.agreement.AgreementUserInformVO;

public interface AgreementMemberMapper {

	int getTotalAgreement(int userNum);

	ArrayList<AgreementAgreementVO> getAgreementList(RowBounds rbs, int userNum);

	AgreementUserInformVO getAgreementUserInform(int userNum);

}
