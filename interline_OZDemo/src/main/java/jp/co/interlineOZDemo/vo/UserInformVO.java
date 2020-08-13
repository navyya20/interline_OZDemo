package jp.co.interlineOZDemo.vo;

public class UserInformVO {
	private int userNum;
	private String userId;
	private String password;
	private String companyName;
	private String address;
	private String post;
	private String authority;
	private String stampFileName;
	private String startDate;
	private String bankName;
	private String depositeClassification;
	private String accountNumber;
	private String accountOwner;
	private String hurigana;
	
	public UserInformVO() {
		super();
	}

	public UserInformVO(int userNum, String userId, String password, String companyName, String address, String post,
			String authority, String stampFileName, String startDate, String bankName, String depositeClassification,
			String accountNumber, String accountOwner, String hurigana) {
		super();
		this.userNum = userNum;
		this.userId = userId;
		this.password = password;
		this.companyName = companyName;
		this.address = address;
		this.post = post;
		this.authority = authority;
		this.stampFileName = stampFileName;
		this.startDate = startDate;
		this.bankName = bankName;
		this.depositeClassification = depositeClassification;
		this.accountNumber = accountNumber;
		this.accountOwner = accountOwner;
		this.hurigana = hurigana;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getStampFileName() {
		return stampFileName;
	}

	public void setStampFileName(String stampFileName) {
		this.stampFileName = stampFileName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getDepositeClassification() {
		return depositeClassification;
	}

	public void setDepositeClassification(String depositeClassification) {
		this.depositeClassification = depositeClassification;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getHurigana() {
		return hurigana;
	}

	public void setHurigana(String hurigana) {
		this.hurigana = hurigana;
	}

	@Override
	public String toString() {
		return "UserInformVO [userNum=" + userNum + ", userId=" + userId + ", password=" + password + ", companyName="
				+ companyName + ", address=" + address + ", post=" + post + ", authority=" + authority
				+ ", stampFileName=" + stampFileName + ", startDate=" + startDate + ", bankName=" + bankName
				+ ", depositeClassification=" + depositeClassification + ", accountNumber=" + accountNumber
				+ ", accountOwner=" + accountOwner + ", hurigana=" + hurigana + "]";
	}
}
