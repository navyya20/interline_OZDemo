package jp.co.interlineOZDemo.dao;

import java.util.ArrayList;

import jp.co.interlineOZDemo.vo.EstimateItemsVO;
import jp.co.interlineOZDemo.vo.EstimateSheetVO;
import jp.co.interlineOZDemo.vo.UserInformVO;

public interface MemberMapper {

	public UserInformVO getMember(String id);

	public int insertEstimateSheet(EstimateSheetVO estimateSheetVO);

	public void insertEstimateItems(ArrayList<EstimateItemsVO> estimateItemsArray);

}
