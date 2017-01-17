package menuItemClasses;

public class Inventory extends Item{
	
	private int quantity;

	public Inventory(String itemCode, String name, int price, int inventoryLevel) {
		super(itemCode, name, price);
		
		this.quantity = inventoryLevel;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}