package jp.co.interlineOZDemo.vo;

public class EstimateTypeVO {
	private String estimateType;
	private String estimateName;
	private String EstimateOzrName;
	private String EstimateDBName;
	private String BillOzrName;
	private String BillDBName;
	private String ItemDBName;
	private String jsName;
	
	public String getEstimateType() {
		return estimateType;
	}
	public void setEstimateType(String estimateType) {
		this.estimateType = estimateType;
	}
	public String getEstimateName() {
		return estimateName;
	}
	public void setEstimateName(String estimateName) {
		this.estimateName = estimateName;
	}
	public String getEstimateOzrName() {
		return EstimateOzrName;
	}
	public void setEstimateOzrName(String estimateOzrName) {
		EstimateOzrName = estimateOzrName;
	}
	public String getEstimateDBName() {
		return EstimateDBName;
	}
	public void setEstimateDBName(String estimateDBName) {
		EstimateDBName = estimateDBName;
	}
	public String getBillOzrName() {
		return BillOzrName;
	}
	public void setBillOzrName(String billOzrName) {
		BillOzrName = billOzrName;
	}
	public String getBillDBName() {
		return BillDBName;
	}
	public void setBillDBName(String billDBName) {
		BillDBName = billDBName;
	}
	public String getItemDBName() {
		return ItemDBName;
	}
	public void setItemDBName(String itemDBName) {
		ItemDBName = itemDBName;
	}
	public String getJsName() {
		return jsName;
	}
	public void setJsName(String jsName) {
		this.jsName = jsName;
	}
	@Override
	public String toString() {
		return "EstimateTypeVO [estimateType=" + estimateType + ", estimateName=" + estimateName + ", EstimateOzrName="
				+ EstimateOzrName + ", EstimateDBName=" + EstimateDBName + ", BillOzrName=" + BillOzrName
				+ ", BillDBName=" + BillDBName + ", ItemDBName=" + ItemDBName + ", jsName=" + jsName + "]";
	}
	
	

	
	
}
