package jp.co.interlineOZDemo.vo.agreement;

public class AgreementMemorandumVO {
	
	private int userNum;
	private int systemReportNum;
	private int reportNum;
	private String systemDate;

	private String company;
	private String representative;
	private String post;
	private String address;

	private String memorandumDate;
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
	public String getMemorandumDate() {
		return memorandumDate;
	}
	public void setMemorandumDate(String memorandumDate) {
		this.memorandumDate = memorandumDate;
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
		return "AgreementMemorandum [userNum=" + userNum + ", systemReportNum=" + systemReportNum + ", reportNum="
				+ reportNum + ", systemDate=" + systemDate + ", company=" + company + ", representative="
				+ representative + ", post=" + post + ", address=" + address + ", memorandumDate=" + memorandumDate
				+ ", customer=" + customer + ", sign=" + sign + ", sort=" + sort + "]";
	}
	
	
	
	
}
