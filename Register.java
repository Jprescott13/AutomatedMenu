package generateMenus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import menuItemClasses.Drink;
import menuItemClasses.Item;
import menuItemClasses.Sandwich;
import menuItemClasses.Sides;
import transaction.Order;

public class Register {

	private Map<String, Sandwich> sandwichMenu = new HashMap<>();
	private Map<String, Drink> drinkMenu = new HashMap<>();
	private Map<String, Sides> sidesMenu = new HashMap<>();
	private List<Item> itemsToBePrintedToReceipt = new ArrayList<Item>();
	private Sandwich[] sandwichArray = new Sandwich[4];
	private Item orderItem;
	private int totalCostInt = 0;

	public Register(File f, File g, File h) {

		// Create Sandwich Menu

		try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr)) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] itemProperties;
				itemProperties = line.split("\\,");
				String itemCode = itemProperties[0];
				String itemName = itemProperties[1];
				int price = (int) (Double.parseDouble(itemProperties[2]) * 100);
				String meatType = itemProperties[3];
				int patties = (int) (Double.parseDouble(itemProperties[4]));
				int lettuce = (int) (Double.parseDouble(itemProperties[5]));
				int tomato = (int) (Double.parseDouble(itemProperties[6]));
				int onion = (int) (Double.parseDouble(itemProperties[7]));
				int cheese = (int) (Double.parseDouble(itemProperties[8]));
				sandwichMenu.put(itemCode, new Sandwich(itemCode, itemName, price, meatType, patties, lettuce, tomato, onion, cheese));
				}
		} catch (Exception e) {
			System.out.println("There is a problem with the sandwich file you are trying to import.");
			System.out.println("The Error Reads:" + e.getMessage());
		}

		// Create Drink Menu

		try (FileReader fr = new FileReader(g); BufferedReader br = new BufferedReader(fr)) {

			String line;

			while ((line = br.readLine()) != null) {
				String[] itemProperties;
				itemProperties = line.split("\\,");
				String itemCode = itemProperties[0];
				String itemName = itemProperties[1];
				int price = (int) (Double.parseDouble(itemProperties[2]) * 100);
				String size = itemProperties[3];
				drinkMenu.put(itemCode, new Drink(itemCode, itemName, price, size));
			}
		} catch (Exception e) {
			System.out.println("There is a problem with the drink file you are trying to import.");
			System.out.println("The Error Reads:" + e.getMessage());
		}

		// Create Side Menu

		try (FileReader fr = new FileReader(h); BufferedReader br = new BufferedReader(fr)) {

			String line;

			while ((line = br.readLine()) != null) {
				String[] itemProperties;
				itemProperties = line.split("\\,");
				String itemCode = itemProperties[0];
				String itemName = itemProperties[1];
				int price = (int) (Double.parseDouble(itemProperties[2]) * 100);
				String size = itemProperties[3];
				sidesMenu.put(itemCode, new Sides(itemCode, itemName, price, size));
			}
		} catch (Exception e) {
			System.out.println("There is a problem with the Sides file you are trying to import.");
			System.out.println("The Error Reads:" + e.getMessage());
		}
	}

	public List<String> displaySandwichMenu() {
		List<String> sandwichListStrings = new ArrayList<>();
		Set<String> keys = sandwichMenu.keySet();
		for (String s : keys) {
			Sandwich i = sandwichMenu.get(s);
			StringBuffer itemString = new StringBuffer();

			itemString.append(i.getItemCode() + "|");
			itemString.append(i.getName() + " | ");
			itemString.append((double) i.getPrice() / 100 + " | ");
			sandwichListStrings.add(itemString.toString());

		}
		return sandwichListStrings;
	}
	
	//Test to figure out how to display Sandwich with numerical ingredients
	
	public List<String> displaySandwichMenuTestVersion() {
		List<String> sandwichIngredientList = new ArrayList<>();
		Set<String> keys = sandwichMenu.keySet();
		for (String s : keys) {
			Sandwich i = sandwichMenu.get(s);
			StringBuffer itemString = new StringBuffer();
			
			itemString.append(i.getName() + " | ");
			itemString.append("Bun: " + i.getBun() + " | ");
			
			itemString.append("Meat: " + i.getPatties() + " " + i.getMeatType() + " Patty | " );
			itemString.append("Cheese: " + i.getCheese() + " | " );
			
			itemString.append("Lettuce: " + i.getLettuce() + " | ");
			itemString.append("Tomato: " + i.getTomato() + " | ");
			itemString.append("Onion: " + i.getOnion() + " | ");
			
			sandwichIngredientList.add(itemString.toString());

		}
		return sandwichIngredientList;
	}
	
	// ***Tests to figure out how to display Sandwiches with Y/N for whether it contains ingredients

	public List<String> displaySandwichMenuTestVersion2() {
		List<String> sandwichIngredientList = new ArrayList<>();
		Set<String> keys = sandwichMenu.keySet();
		for (String s : keys) {
			Sandwich i = sandwichMenu.get(s);
			StringBuffer itemString = new StringBuffer();
			
			itemString.append(i.getName() + " | ");
			if(i.getBun()>=1){
				itemString.append("Bun: Y |");
			}
			else {
				itemString.append("Bun: N |");
			}
			itemString.append("Meat: " + i.getPatties() + " " + i.getMeatType() + " Patty | " );
			
			if(i.getCheese()>=1){
				itemString.append("Cheese: Y |");
			}
			else {
				itemString.append("Cheese: N |");
			}
				
			if(i.getLettuce()>=1){
				itemString.append("Lettuce: Y |");
			}
			else {
				itemString.append("Lettuce: N |");
			}
			
			if(i.getTomato()>=1){
				itemString.append("Tomato: Y |");
			}
			else {
				itemString.append("Tomato: N |");
			}
			
			if(i.getOnion()>=1){
				itemString.append("Onion: Y |");
			}
			else {
				itemString.append("Onion: N |");
			}
			sandwichIngredientList.add(itemString.toString());

		}
		return sandwichIngredientList;
	}
	
	// ***Tests to display each sandwich's ingredients vertically

	public Sandwich[] diplaySandwichesAsArray() {
		Set<String> keys = sandwichMenu.keySet();
		int count = 0; 
		for (String s : keys) {
			Sandwich b = sandwichMenu.get(s);
			sandwichArray[count] = b;
			count +=1;
		}
		return sandwichArray;
	}
	
	
	public List<String> displayDrinkMenu() {
		List<String> drinkListStrings = new ArrayList<>();
		Set<String> keys = drinkMenu.keySet();
		for (String s : keys) {
			Drink i = drinkMenu.get(s);
			StringBuffer itemString = new StringBuffer();

			itemString.append(i.getItemCode() + "|");
			itemString.append(i.getName() + " | $");
			itemString.append((double) i.getPrice() / 100 + " | ");
			drinkListStrings.add(itemString.toString());

		}
		return drinkListStrings;
	}

	public List<String> displaySidesMenu() {
		List<String> drinkListStrings = new ArrayList<>();
		Set<String> keys = sidesMenu.keySet();
		for (String s : keys) {
			Sides i = sidesMenu.get(s);
			StringBuffer itemString = new StringBuffer();

			itemString.append(i.getItemCode() + "|");
			itemString.append(i.getName() + " | $");
			itemString.append((double) i.getPrice() / 100 + " | ");
			drinkListStrings.add(itemString.toString());

		}
		return drinkListStrings;

	}

	public String addToOrder(Order o, String itemCode, String itemQty) {
		int itemQuantityInt = Integer.parseInt(itemQty);

		if (itemCode.substring(0, 1).equalsIgnoreCase("S")) {
			Sandwich orderedItem = sandwichMenu.get(itemCode);
			Item receiptItem = new Item(orderedItem.getItemCode(), orderedItem.getName(), orderedItem.getPrice());
			receiptItem.setItemCode(orderedItem.getItemCode());
			receiptItem.setName(orderedItem.getName());
			receiptItem.setPrice((orderedItem.getPrice()) * itemQuantityInt);
			itemsToBePrintedToReceipt.add(receiptItem);
			orderItem = receiptItem;
			totalCostInt += receiptItem.getPrice();
			return itemQty + orderedItem.getName() + "(s) have been added to your order";

		}
		if (itemCode.substring(0, 1).equalsIgnoreCase("D")) {
			Drink orderedItem = drinkMenu.get(itemCode);
			Item receiptItem = new Item(orderedItem.getItemCode(), orderedItem.getName(), orderedItem.getPrice());
			receiptItem.setItemCode(orderedItem.getItemCode());
			receiptItem.setName(orderedItem.getName());
			receiptItem.setPrice((orderedItem.getPrice()) * itemQuantityInt);
			itemsToBePrintedToReceipt.add(receiptItem);
			totalCostInt += receiptItem.getPrice();
			return itemQty + orderedItem.getName() + "(s) have been added to your order";
		}
		if (itemCode.substring(0, 1).equalsIgnoreCase("T")) {
			Sides orderedItem = sidesMenu.get(itemCode);
			Item receiptItem = new Item(orderedItem.getItemCode(), orderedItem.getName(), orderedItem.getPrice());
			receiptItem.setItemCode(orderedItem.getItemCode());
			receiptItem.setName(orderedItem.getName());
			receiptItem.setPrice((orderedItem.getPrice()) * itemQuantityInt);
			itemsToBePrintedToReceipt.add(receiptItem);
			totalCostInt += receiptItem.getPrice();
			return itemQty + orderedItem.getName() + "(s) have been added to your order";
		} else {
			return "Sorry That Item does not exist, please try again";
		}

	}

	public void displayOrderToConsole(List<Item> ReceiptList, Order order1) {

		for (int i = 0; i < ReceiptList.size(); i++) {
			System.out.printf("%-30s", ReceiptList.get(i).getName());
			System.out.printf("%-30s", order1.getBalanceInDollars(ReceiptList.get(i).getPrice()));
			System.out.println(" ");
		}
	}

	public Item getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Item orderItem) {
		this.orderItem = orderItem;
	}

	public List<Item> getItemsToBePrintedToReceipt() {
		return itemsToBePrintedToReceipt;
	}

	public int getTotalCostInt() {
		return totalCostInt;
	}

	public Sandwich[] getSandwichArray() {
		return sandwichArray;
	}

	public void setSandwichArray(Sandwich[] sandwichArray) {
		this.sandwichArray = sandwichArray;
	}

	public Map<String, Sandwich> getSandwichMenu() {
		return sandwichMenu;
	}

	public void setSandwichMenu(Map<String, Sandwich> sandwichMenu) {
		this.sandwichMenu = sandwichMenu;
	}

	public Map<String, Drink> getDrinkMenu() {
		return drinkMenu;
	}

	public void setDrinkMenu(Map<String, Drink> drinkMenu) {
		this.drinkMenu = drinkMenu;
	}

	public Map<String, Sides> getSidesMenu() {
		return sidesMenu;
	}

	public void setSidesMenu(Map<String, Sides> sidesMenu) {
		this.sidesMenu = sidesMenu;
	}
}
