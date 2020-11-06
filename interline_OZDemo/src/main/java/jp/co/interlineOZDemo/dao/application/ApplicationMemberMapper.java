package jp.co.interlineOZDemo.dao.application;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import jp.co.interlineOZDemo.vo.application.ApplicationVO;

public interface ApplicationMemberMapper {

	int insertApplicationSheet(ApplicationVO applicationVO);

	int nextApplicationNum(int userNum);

	
}
