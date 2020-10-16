package jp.co.interlineOZDemo.vo.agreement;

public class AgreementUserInformVO {
	
	private int userNum;
	private String company;
	private String representative;
	private String post;
	private String address;
	private String email;
	private String phoneNumber;
	private String incharge;
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRepresentative() {
		return representative;
	}
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getIncharge() {
		return incharge;
	}
	public void setIncharge(String incharge) {
		this.incharge = incharge;
	}
	
	@Override
	public String toString() {
		return "AgreementUserInformVO [userNum=" + userNum + ", company=" + company + ", representative="
				+ representative + ", post=" + post + ", address=" + address + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", incharge=" + incharge + "]";
	}
	
	
	
}
