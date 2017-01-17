package menuItemClasses;

public class Item {
	
	private String name; //Product or Component Name
	private int price; //Product Sale Price or Component COG
	private String itemCode; // 2-digit Product Item Code, or 3-digit Inventory Item Code  

	public Item(String itemCode, String name, int price) {
		
		this.itemCode = itemCode;
		this.name = name;
		this.price = price;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

}
