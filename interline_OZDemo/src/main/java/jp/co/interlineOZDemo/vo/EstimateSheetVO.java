package jp.co.interlineOZDemo.vo;

public class EstimateSheetVO {
	private int reportNum;
	private String reportDate;
	private String supplier;
	private String address;
	private String phoneNumber;
	private String incharge;
	private String receiver;
	private String reportName;
	private String deadline;
	private String supplyPoint;
	private String payCondition;
	private String cautions;
	private int sum;
	private int sumWithTax;
	private String state;
	
	public EstimateSheetVO() {
		super();
	}

	public EstimateSheetVO(int reportNum, String reportDate, String supplier, String address, String phoneNumber,
			String incharge, String receiver, String reportName, String deadline, String supplyPoint,
			String payCondition, String cautions, int sum, int sumWithTax, String state) {
		super();
		this.reportNum = reportNum;
		this.reportDate = reportDate;
		this.supplier = supplier;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.incharge = incharge;
		this.receiver = receiver;
		this.reportName = reportName;
		this.deadline = deadline;
		this.supplyPoint = supplyPoint;
		this.payCondition = payCondition;
		this.cautions = cautions;
		this.sum = sum;
		this.sumWithTax = sumWithTax;
		this.state = state;
	}

	public int getReportNum() {
		return reportNum;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "EstimateSheetVO [reportNum=" + reportNum + ", reportDate=" + reportDate + ", supplier=" + supplier
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", incharge=" + incharge + ", receiver="
				+ receiver + ", reportName=" + reportName + ", deadline=" + deadline + ", supplyPoint=" + supplyPoint
				+ ", payCondition=" + payCondition + ", cautions=" + cautions + ", sum=" + sum + ", sumWithTax="
				+ sumWithTax + ", state=" + state + "]";
	}
}
