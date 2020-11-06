package jp.co.interlineOZDemo.vo.application;

public class ApplicationVO {
	int userNum;
	int systemReportNum;
	int reportNum;
	String applicationDate;
	String systemDate;
	String name;
	String nameHurigana;
	String telephone;
	String mobile;
	String birth; // 만약 승훈씨가 년 월 일 각각 셀렉트박스 식으로 한다면 int 3개로 쪼개셔도됩니다.
	String gender;
	int age;
	String post;
	String address;
	String addressHurigana;
	String PDFPath;
	String sign;
	
	public ApplicationVO() {
		super();
	}

	public ApplicationVO(int userNum, int systemReportNum, int reportNum, String applicationDate, String systemDate,
			String name, String nameHurigana, String telephone, String mobile, String birth, String gender, int age,
			String post, String address, String addressHurigana, String pDFPath, String sign) {
		super();
		this.userNum = userNum;
		this.systemReportNum = systemReportNum;
		this.reportNum = reportNum;
		this.applicationDate = applicationDate;
		this.systemDate = systemDate;
		this.name = name;
		this.nameHurigana = nameHurigana;
		this.telephone = telephone;
		this.mobile = mobile;
		this.birth = birth;
		this.gender = gender;
		this.age = age;
		this.post = post;
		this.address = address;
		this.addressHurigana = addressHurigana;
		PDFPath = pDFPath;
		this.sign = sign;
	}

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

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getSystemDate() {
		return systemDate;
	}

	public void setSystemDate(String systemDate) {
		this.systemDate = systemDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameHurigana() {
		return nameHurigana;
	}

	public void setNameHurigana(String nameHurigana) {
		this.nameHurigana = nameHurigana;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getAddressHurigana() {
		return addressHurigana;
	}

	public void setAddressHurigana(String addressHurigana) {
		this.addressHurigana = addressHurigana;
	}

	public String getPDFPath() {
		return PDFPath;
	}

	public void setPDFPath(String pDFPath) {
		PDFPath = pDFPath;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "ApplicationVO [userNum=" + userNum + ", systemReportNum=" + systemReportNum + ", reportNum=" + reportNum
				+ ", applicationDate=" + applicationDate + ", systemDate=" + systemDate + ", name=" + name
				+ ", nameHurigana=" + nameHurigana + ", telephone=" + telephone + ", mobile=" + mobile + ", birth="
				+ birth + ", gender=" + gender + ", age=" + age + ", post=" + post + ", address=" + address
				+ ", addressHurigana=" + addressHurigana + ", PDFPath=" + PDFPath + ", sign=" + sign + "]";
	}
	
}
