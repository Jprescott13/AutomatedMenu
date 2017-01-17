package menuItemClasses;

public class Drink extends Item{
	
	private String size;
	private int ounces;
	private boolean diet;

	public Drink(String itemCode, String name, int price, String size) {
		super(itemCode, name, price);
		
		this.size = size;
		
		if (size.contains("Small")){
			ounces = 20;
		}else if (size.contains("Medium")){
			ounces = 32;
		}else {
			ounces = 40;
		}
		diet = false;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getOunces() {
		return ounces;
	}

	public void setOunces(int ounces) {
		this.ounces = ounces;
	}

	public boolean isDiet() {
		return diet;
	}

	public void setDiet(boolean diet) {
		this.diet = diet;
	}

}
