package jp.co.interlineOZDemo.vo;

public class EstimateItemsVO {
	private int reportNum;
    private String itemName;
    private int imtemNum;
    private int amount;
    private String unit;
    private int unitPrice;
    private int price;    
    private String note;
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
	public int getImtemNum() {
		return imtemNum;
	}
	public void setImtemNum(int imtemNum) {
		this.imtemNum = imtemNum;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "EstimateItemsVO [reportNum=" + reportNum + ", itemName=" + itemName + ", imtemNum=" + imtemNum
				+ ", amount=" + amount + ", unit=" + unit + ", unitPrice=" + unitPrice + ", price=" + price + ", note="
				+ note + "]";
	}
    
	
    
    
}
