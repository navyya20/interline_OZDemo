package jp.co.interlineOZDemo.vo;

public class BillInformVO {
	private int billNum;
	private int reportNum;
	private int userNum;
	private	String billDate;
	private	String supplier;
	private	String address;
	private String post;
	private	String phoneNumber;
	private	String representative;
	private	String receiver;
	private	String supplyPoint;
	private String stamp;
	private	long sum;
	private	long sumWithTax;
	private long sumWithTax2;
	private String bankName;
	private String depositeClassification;
	private String accountNumber;
	private String accountOwner;
	private String hurigana;
	private String ozrName;
	private String estimateType;
	private String billDBName;
	

	public int getBillNum() {
		return billNum;
	}

	public void setBillNum(int billNum) {
		this.billNum = billNum;
	}

	public int getReportNum() {
		return reportNum;
	}

	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
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

	public String getSupplyPoint() {
		return supplyPoint;
	}

	public void setSupplyPoint(String supplyPoint) {
		this.supplyPoint = supplyPoint;
	}

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
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

	
	public String getOzrName() {
		return ozrName;
	}

	public void setOzrName(String ozrName) {
		this.ozrName = ozrName;
	}

	public String getEstimateType() {
		return estimateType;
	}

	public void setEstimateType(String estimateType) {
		this.estimateType = estimateType;
	}
	
	public String getBillDBName() {
		return billDBName;
	}

	public void setBillDBName(String billDBName) {
		this.billDBName = billDBName;
	}

	@Override
	public String toString() {
		return "BillInformVO [billNum=" + billNum + ", reportNum=" + reportNum + ", userNum=" + userNum + ", billDate="
				+ billDate + ", supplier=" + supplier + ", address=" + address + ", post=" + post + ", phoneNumber="
				+ phoneNumber + ", representative=" + representative + ", receiver=" + receiver + ", supplyPoint="
				+ supplyPoint + ", stamp=" + stamp + ", sum=" + sum + ", sumWithTax=" + sumWithTax + ", sumWithTax2="
				+ sumWithTax2 + ", bankName=" + bankName + ", depositeClassification=" + depositeClassification
				+ ", accountNumber=" + accountNumber + ", accountOwner=" + accountOwner + ", hurigana=" + hurigana
				+ ", ozrName=" + ozrName + ", estimateType=" + estimateType + ", billDBName=" + billDBName + "]";
	}
	
	
	

}
