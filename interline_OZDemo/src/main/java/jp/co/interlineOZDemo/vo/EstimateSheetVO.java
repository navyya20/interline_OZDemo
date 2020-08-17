package jp.co.interlineOZDemo.vo;

public class EstimateSheetVO {
	private int reportNum;
	private String reportDate;
	private int userNum;
	private String supplier;
	private String address;
	private String post;
	private String phoneNumber;
	private String representative;
	private String receiver;
	private String reportName;
	private String deadline;
	private String supplyPoint;
	private String payCondition;
	private String stamp;
	private String cautions;
	private int sum;
	private int sumWithTax;
	private int sumWithTax2;
	private String email;
	private String state;
	
	
	
	public int getReportNum() {
		return reportNum;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
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
	public String getRepresentative() {
		return representative;
	}
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getSupplyPoint() {
		return supplyPoint;
	}
	public void setSupplyPoint(String supplyPoint) {
		this.supplyPoint = supplyPoint;
	}
	public String getPayCondition() {
		return payCondition;
	}
	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public String getCautions() {
		return cautions;
	}
	public void setCautions(String cautions) {
		this.cautions = cautions;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getSumWithTax() {
		return sumWithTax;
	}
	public void setSumWithTax(int sumWithTax) {
		this.sumWithTax = sumWithTax;
	}
	public int getSumWithTax2() {
		return sumWithTax2;
	}
	public void setSumWithTax2(int sumWithTax2) {
		this.sumWithTax2 = sumWithTax2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "EstimateSheetVO [reportNum=" + reportNum + ", reportDate=" + reportDate + ", userNum=" + userNum
				+ ", supplier=" + supplier + ", address=" + address + ", post=" + post + ", phoneNumber=" + phoneNumber
				+ ", representative=" + representative + ", receiver=" + receiver + ", reportName=" + reportName
				+ ", deadline=" + deadline + ", supplyPoint=" + supplyPoint + ", payCondition=" + payCondition
				+ ", stamp=" + stamp + ", cautions=" + cautions + ", sum=" + sum + ", sumWithTax=" + sumWithTax
				+ ", sumWithTax2=" + sumWithTax2 + ", email=" + email + ", state=" + state + "]";
	}
	
	
	
}
