package generateMenus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import menuItemClasses.Inventory;

public class StoreRoom {

	private Map<String, Inventory> inventoryItemsMap = new HashMap<>();

	public StoreRoom(File f) {

		// Create Inventory Table

		try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr)) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] inventoryItemProperties;
				inventoryItemProperties = line.split("\\,");
				String itemCode = inventoryItemProperties[0];
				String itemName = inventoryItemProperties[1];
				int price = (int) (Double.parseDouble(inventoryItemProperties[2]) * 100);
				int ItemQuantity = (int) (Double.parseDouble(inventoryItemProperties[3]));
				inventoryItemsMap.put(itemCode, new Inventory(itemCode, itemName, price, ItemQuantity));
			}
		} catch (Exception e) {
			System.out.println("There was a problem importing the Inventory File.");
			System.out.println("The Error Reads: " + e.getMessage());
		}
	}

	public List<String> displayInventory() {

		List<String> inventoryListStrings = new ArrayList<>();
		Set<String> keys = inventoryItemsMap.keySet();
		for (String s : keys) {
			Inventory inv = inventoryItemsMap.get(s);
			StringBuffer itemString = new StringBuffer();

			itemString.append(inv.getItemCode() + " |   ");
			itemString.append(inv.getName() + " | ");
			itemString.append("$" + (double) inv.getPrice() / 100 + " | ");
			itemString.append(inv.getQuantity() + " | ");
			inventoryListStrings.add(itemString.toString());
		}
		return inventoryListStrings;
	}

	//Inventory Updater 
	
	 public void subtractOrderedInventory(String soldItem, int itemQuantity) {
		 int oldInventoryQuantity = inventoryItemsMap.get(soldItem).getQuantity();
		 int newInventoryQuantity = inventoryItemsMap.get(soldItem).getQuantity() - itemQuantity;
		 inventoryItemsMap.get(soldItem).setQuantity(newInventoryQuantity);
		 System.out.println("The inventory of " + inventoryItemsMap.get(soldItem).getName() + " has been updated from " + oldInventoryQuantity + " to " + newInventoryQuantity);
		 
	 }

	public Map<String, Inventory> getInventoryItemsMap() {
		return inventoryItemsMap;
	}

	public void setInventoryItemsMap(Map<String, Inventory> inventoryLevels) {
		this.inventoryItemsMap = inventoryLevels;
	}

}
