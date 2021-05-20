package jp.co.interlineOZDemo.vo;

public class EstimateCommonVO {
	private int userNum;
	private int reportNum;
	private String stamp;
	private String DBName;
	private String state;
	
	
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public int getReportNum() {
		return reportNum;
	}
	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public String getDBName() {
		return DBName;
	}
	public void setDBName(String dBName) {
		DBName = dBName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
	@Override
	public String toString() {
		return "EstimateCommonVO [userNum=" + userNum + ", reportNum=" + reportNum + ", stamp=" + stamp + ", DBName="
				+ DBName + "]";
	}
	
	
	
}
