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
	
	public BillInformVO() {
		super();
	}

	public BillInformVO(int billNum, int reportNum, int userNum, String billDate, String supplier, String address,
			String post, String phoneNumber, String representative, String receiver, String supplyPoint, String stamp,
			long sum, long sumWithTax, long sumWithTax2, String bankName, String depositeClassification,
			String accountNumber, String accountOwner, String hurigana) {
		super();
		this.billNum = billNum;
		this.reportNum = reportNum;
		this.userNum = userNum;
		this.billDate = billDate;
		this.supplier = supplier;
		this.address = address;
		this.post = post;
		this.phoneNumber = phoneNumber;
		this.representative = representative;
		this.receiver = receiver;
		this.supplyPoint = supplyPoint;
		this.stamp = stamp;
		this.sum = sum;
		this.sumWithTax = sumWithTax;
		this.sumWithTax2 = sumWithTax2;
		this.bankName = bankName;
		this.depositeClassification = depositeClassification;
		this.accountNumber = accountNumber;
		this.accountOwner = accountOwner;
		this.hurigana = hurigana;
	}

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

	@Override
	public String toString() {
		return "BillInformVO [billNum=" + billNum + ", reportNum=" + reportNum + ", userNum=" + userNum + ", billDate="
				+ billDate + ", supplier=" + supplier + ", address=" + address + ", post=" + post + ", phoneNumber="
				+ phoneNumber + ", representative=" + representative + ", receiver=" + receiver + ", supplyPoint="
				+ supplyPoint + ", stamp=" + stamp + ", sum=" + sum + ", sumWithTax=" + sumWithTax + ", sumWithTax2="
				+ sumWithTax2 + ", bankName=" + bankName + ", depositeClassification=" + depositeClassification
				+ ", accountNumber=" + accountNumber + ", accountOwner=" + accountOwner + ", hurigana=" + hurigana
				+ ", getBillNum()=" + getBillNum() + ", getReportNum()=" + getReportNum() + ", getBillDate()="
				+ getBillDate() + ", getSupplier()=" + getSupplier() + ", getAddress()=" + getAddress() + ", getPost()="
				+ getPost() + ", getPhoneNumber()=" + getPhoneNumber() + ", getRepresentative()=" + getRepresentative()
				+ ", getReceiver()=" + getReceiver() + ", getSupplyPoint()=" + getSupplyPoint() + ", getStamp()="
				+ getStamp() + ", getSum()=" + getSum() + ", getSumWithTax()=" + getSumWithTax() + ", getSumWithTax2()="
				+ getSumWithTax2() + ", getBankName()=" + getBankName() + ", getDepositeClassification()="
				+ getDepositeClassification() + ", getAccountNumber()=" + getAccountNumber() + ", getAccountOwner()="
				+ getAccountOwner() + ", getHurigana()=" + getHurigana() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
