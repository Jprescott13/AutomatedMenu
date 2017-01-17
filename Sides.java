package menuItemClasses;

public class Sides extends Item {

	private String size;
	private int sizeNumericalValue; //used for calculations of inventory
	
	public Sides(String itemCode, String name, int price, String size) {
		super(itemCode,name,price);
		

		this.size = size;
		
		if (size.contains("Small")){
			sizeNumericalValue = 1;
		}else if (size.contains("Medium")){
			sizeNumericalValue = 2;
		}else if (size.contains("Large")){
			sizeNumericalValue = 3;
		}else {
			sizeNumericalValue = 1;
		}
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getSizeNumericalValue() {
		return sizeNumericalValue;
	}

	public void setSizeNumericalValue(int sizeNumericalValue) {
		this.sizeNumericalValue = sizeNumericalValue;
	}
}
