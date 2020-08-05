package jp.co.interlineOZDemo.dao;

import jp.co.interlineOZDemo.vo.UserInformVO;

public interface MemberMapper {

	public UserInformVO getUser(String id);

}
