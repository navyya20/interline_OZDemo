package jp.co.interlineOZDemo.vo;

public class EstimateItemsVO {
	private int reportNum;
    private String itemName;
    private int itemNum;
    private int amount;
    private String unit;
    private int unitPrice;
    private long price;    
	public int getReportNum() {
		return reportNum;
	}
	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	@Override
	public String toString() {
		return "EstimateItemsVO [reportNum=" + reportNum + ", itemName=" + itemName + ", itemNum=" + itemNum
				+ ", amount=" + amount + ", unit=" + unit + ", unitPrice=" + unitPrice + ", price=" + price  + "]";
	}
	
    
	
    
    
}
