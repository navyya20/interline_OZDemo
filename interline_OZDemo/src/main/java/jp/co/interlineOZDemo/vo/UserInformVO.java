package jp.co.interlineOZDemo.vo;

public class UserInformVO {
	private String userid;
	private String password;
	private String companyname;
	private String address;
	private String post;
	private String authority;
	
	public UserInformVO() {
		super();
	}

	public UserInformVO(String userid, String password, String companyname , String address, String post, String authority) {
		super();
		this.userid = userid;
		this.password = password;
		this.companyname  = companyname ;
		this.address = address;
		this.post = post;
		this.authority = authority;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getcompanyname () {
		return companyname ;
	}

	public void setcompanyname (String companyname ) {
		this.companyname  = companyname ;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", password=" + password + ", companyname =" + companyname  + ", address="
				+ address + ", post=" + post + ", authority=" + authority + "]";
	}
}