package jp.co.interlineOZDemo.vo.agreement;

public class AgreementAgreementVO {
	
	private int userNum;
	private int systemReportNum;
	private int reportNum;
	private String systemDate;
	
	private String company;
	private String address;
	private String post;
	private String phoneNumber;
	private String email;
	private String incharge;
	
	private String agreementDate;
	private String customer;
	private String sign;
	private String sort;
	
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public int getSystemReportNum() {
		return systemReportNum;
	}
	public void setSystemReportNum(int systemReportNum) {
		this.systemReportNum = systemReportNum;
	}
	public int getReportNum() {
		return reportNum;
	}
	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}
	public String getSystemDate() {
		return systemDate;
	}
	public void setSystemDate(String systemDate) {
		this.systemDate = systemDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIncharge() {
		return incharge;
	}
	public void setIncharge(String incharge) {
		this.incharge = incharge;
	}
	public String getAgreementDate() {
		return agreementDate;
	}
	public void setAgreementDate(String agreementDate) {
		this.agreementDate = agreementDate;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	@Override
	public String toString() {
		return "AgreementAgreementVO [userNum=" + userNum + ", systemReportNum=" + systemReportNum + ", reportNum="
				+ reportNum + ", systemDate=" + systemDate + ", company=" + company + ", address=" + address + ", post="
				+ post + ", phoneNumber=" + phoneNumber + ", email=" + email + ", incharge=" + incharge
				+ ", agreementDate=" + agreementDate + ", customer=" + customer + ", sign=" + sign + ", sort=" + sort
				+ "]";
	}
	
	
	
	
}
