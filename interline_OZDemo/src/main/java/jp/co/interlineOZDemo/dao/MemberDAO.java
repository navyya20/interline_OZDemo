package jp.co.interlineOZDemo.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.interlineOZDemo.vo.BillInformVO;
import jp.co.interlineOZDemo.vo.EstimateItemsVO;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;

@Repository
public class MemberDAO {

	@Autowired
	SqlSession session;
	
	public UserInformVO getMember(UserInformVO userIdPassword) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		UserInformVO result_user = mapper.getMember(userIdPassword);
		
		return result_user;
	}

	public int insertEstimateSheet(EstimateSheetVO estimateSheetVO) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		int result = (int) mapper.insertEstimateSheet(estimateSheetVO);
		System.out.println("result:"+result);
		int reportNum = estimateSheetVO.getReportNum();
		System.out.println("생성된 reportNum:"+reportNum);
		return reportNum;
	}

	public void insertEstimateItems(ArrayList<EstimateItemsVO> estimateItemsArray) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		mapper.insertEstimateItems(estimateItemsArray);
	}

	public void updateMyProfile(UserInformVO userInform) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		mapper.updateMyProfile(userInform);
	}

	public EstimateSheetVO getEstimateSheet(EstimateSheetVO userNumReportNum) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		EstimateSheetVO estimateSheet = mapper.getEstimateSheet(userNumReportNum);
		return estimateSheet;
	}

	public ArrayList<EstimateItemsVO> getEstimateItems(int reportNum) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		ArrayList<EstimateItemsVO> estimateItems = mapper.getEstimateItems(reportNum);
		return estimateItems;
	}

	public ArrayList<EstimateSheetVO> getEstimateSheetList(int st, int ctt, int userNum) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		RowBounds rbs = new RowBounds(st,ctt);
		ArrayList<EstimateSheetVO> estimateSheetArray = mapper.getEstimateSheetList(rbs, userNum);
		return estimateSheetArray;
		
	}

	public int getTotalEstimateSheet(int userNum) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		int result = mapper.getTotalEstimateSheet(userNum);
		return result;
	}

	public int deleteSheet(HashMap<String, Object> hashMap) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		int result = mapper.deleteSheet(hashMap);
		return result;
	}

	public int insertBillSheet(BillInformVO billInform) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		int result = mapper.insertBillSheet(billInform);
		return result;
	}

	public void setState(int reportNum) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		mapper.setState(reportNum);
	}

	public BillInformVO getBillInform(BillInformVO userNumReportNum) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		BillInformVO BillInform = mapper.getBillInform(userNumReportNum);
		return BillInform;
	}

}
