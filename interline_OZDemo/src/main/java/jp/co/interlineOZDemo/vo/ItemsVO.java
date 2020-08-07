package jp.co.interlineOZDemo.vo;

public class ItemsVO {
	private String itemName;
    private int itemNum;
    private int basicPrice;
    
	public ItemsVO() {
		super();
	}

	public ItemsVO(String itemName, int itemNum, int basicPrice) {
		super();
		this.itemName = itemName;
		this.itemNum = itemNum;
		this.basicPrice = basicPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(int basicPrice) {
		this.basicPrice = basicPrice;
	}

	@Override
	public String toString() {
		return "ItemsVO [itemName=" + itemName + ", itemNum=" + itemNum + ", basicPrice=" + basicPrice + "]";
	}
}
