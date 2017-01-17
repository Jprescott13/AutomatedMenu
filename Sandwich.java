package menuItemClasses;


public class Sandwich extends Item {
	
	private int bun;
	private String meatType;
	private int patties;
	private int lettuce;
	private int tomato;
	private int onion;
	private int cheese;
	

	public Sandwich(String itemCode, String name, int price, String meatType, int patties, int lettuce, int tomato, int onion, int cheese) {
		super(itemCode, name, price);
		
		this.meatType = meatType;
		this.patties = patties; 
		this.cheese = cheese;	
		this.lettuce = lettuce;
		this.tomato = tomato;
		this.onion = onion;
		bun = 1;
	}

	public int getBun() {
		return bun;
	}

	public void setBun(int bun) {
		this.bun = bun;
	}

	public String getMeatType() {
		return meatType;
	}

	public void setMeatType(String meatType) {
		this.meatType = meatType;
	}

	public int getPatties() {
		return patties;
	}

	public void setPatties(int patties) {
		this.patties = patties;
	}

	public int getLettuce() {
		return lettuce;
	}

	public void setLettuce(int lettuce) {
		this.lettuce = lettuce;
	}

	public int getTomato() {
		return tomato;
	}

	public void setTomato(int tomato) {
		this.tomato = tomato;
	}

	public int getOnion() {
		return onion;
	}

	public void setOnion(int onion) {
		this.onion = onion;
	}

	public int getCheese() {
		return cheese;
	}

	public void setCheese(int cheese) {
		this.cheese = cheese;
	}

}
	