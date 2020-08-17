package jp.co.interlineOZDemo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
