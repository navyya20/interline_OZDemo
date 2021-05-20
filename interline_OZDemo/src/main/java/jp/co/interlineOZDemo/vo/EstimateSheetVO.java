package jp.co.interlineOZDemo.vo;

public class EstimateSheetVO {
	private int reportNum;
	private String reportDate;
	private String dateForDisplaying;
	private String expirationDate;
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
	private long sum;
	private long sumWithTax;
	private long sumWithTax2;
	private String email;
	private String state;
	private String estimateType;
	private String estimateDBName;
	private String itemDBName;
	
	
	
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
	public long getSum() {
		return sum;
	}
	public void setSum(long sum) {
		this.sum = sum;
	}
	public long getSumWithTax() {
		return sumWithTax;
	}
	public void setSumWithTax(long sumWithTax) {
		this.sumWithTax = sumWithTax;
	}
	public long getSumWithTax2() {
		return sumWithTax2;
	}
	public void setSumWithTax2(long sumWithTax2) {
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
	public String getDateForDisplaying() {
		return dateForDisplaying;
	}
	public void setDateForDisplaying(String dateForDisplaying) {
		this.dateForDisplaying = dateForDisplaying;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	public String getEstimateType() {
		return estimateType;
	}
	public void setEstimateType(String estimateType) {
		this.estimateType = estimateType;
	}
	
	
	public String getEstimateDBName() {
		return estimateDBName;
	}
	public void setEstimateDBName(String estimateDBName) {
		this.estimateDBName = estimateDBName;
	}
	public String getItemDBName() {
		return itemDBName;
	}
	public void setItemDBName(String itemDBName) {
		this.itemDBName = itemDBName;
	}
	
	
	@Override
	public String toString() {
		return "EstimateSheetVO [reportNum=" + reportNum + ", reportDate=" + reportDate + ", dateForDisplaying="
				+ dateForDisplaying + ", expirationDate=" + expirationDate + ", userNum=" + userNum + ", supplier="
				+ supplier + ", address=" + address + ", post=" + post + ", phoneNumber=" + phoneNumber
				+ ", representative=" + representative + ", receiver=" + receiver + ", reportName=" + reportName
				+ ", deadline=" + deadline + ", supplyPoint=" + supplyPoint + ", payCondition=" + payCondition
				+ ", stamp=" + stamp + ", cautions=" + cautions + ", sum=" + sum + ", sumWithTax=" + sumWithTax
				+ ", sumWithTax2=" + sumWithTax2 + ", email=" + email + ", state=" + state + ", estimateType="
				+ estimateType + ", estimateDBName=" + estimateDBName + ", itemDBName=" + itemDBName + "]";
	}
	
	
	
	
	
	
}
