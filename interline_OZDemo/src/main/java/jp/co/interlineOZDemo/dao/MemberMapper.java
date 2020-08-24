package jp.co.interlineOZDemo.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import jp.co.interlineOZDemo.vo.BillInformVO;
import jp.co.interlineOZDemo.vo.EstimateItemsVO;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;

public interface MemberMapper {

	public UserInformVO getMember(UserInformVO userIdPassword);

	public int insertEstimateSheet(EstimateSheetVO estimateSheetVO);

	public void insertEstimateItems(ArrayList<EstimateItemsVO> estimateItemsArray);

	public void updateMyProfile(UserInformVO userInform);

	public EstimateSheetVO getEstimateSheet(EstimateSheetVO userNumReportNum);

	public ArrayList<EstimateItemsVO> getEstimateItems(int reportNum);

	public ArrayList<EstimateSheetVO> getEstimateSheetList(RowBounds rbs, int userNum);

	public int getTotalEstimateSheet(int userNum);

	public int deleteSheet(HashMap<String, Object> hashMap);

	public int insertBillSheet(BillInformVO billInform);

	public void setState(int reportNum);

}
