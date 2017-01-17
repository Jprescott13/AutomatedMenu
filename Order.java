package transaction;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import generateMenus.StoreRoom;
import menuItemClasses.Item;

public class Order {

	private int balance;
	private boolean isComplete = false;
	private boolean canFillOrder = true;
	private int presentInventory = 0;
	private int requestedInventory = 0;
	private String StoreRoomMessage = " ";

	public void orderFinished() {
		isComplete = true;
	}

	public boolean orderStatus() {
		return isComplete;
	}

	public int getBalance() {
		return balance;
	}

	public String getBalanceInDollars(int givenBalance) {
		DecimalFormat df = new DecimalFormat("###.##");
		return "$" + df.format((double) givenBalance / 100);
	}

	
	
	// Print Receipt to File
	
	
	public void printReceipt(List<Item> ReceiptList) {
		File receipt = new File("customerReceipt.txt");

		if (!receipt.exists()) {
			try {
				receipt.createNewFile();
			} catch (Exception e) {
				System.out.println("There was a problem Creating the Reciept File");
				System.out.println(e.getMessage());
			} finally {
				System.out.println("Receipt File Creation Process Complete");
			}

			try (PrintWriter pw = new PrintWriter(new FileWriter(receipt, true))) {
				pw.println("DateTime            | Item | Qty | Price");

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		try (PrintWriter pw = new PrintWriter(new FileWriter(receipt, true))) {
			LocalDateTime dateTime = LocalDateTime.now();
			for (int i = 0; i < ReceiptList.size(); i++) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu hh:mm a");
				pw.print(formatter.format(dateTime) + " | ");
				pw.print(ReceiptList.get(i).getName() + " | ");
				// pw.print(qty + " | ");
				pw.print(getBalanceInDollars(ReceiptList.get(i).getPrice()) + "  |  ");
			}
		} catch (Exception e) {
			System.out.println("There was a problem writing to the log file.");
			System.out.println(e.getMessage());
		}
	}

	// ***UNIVERSAL Inventory Level Checker ***

	public String checkAndDecrementStock(int[] itemIngredientValues, String itemQuantityRequested,
			String[] inventoryCode, StoreRoom restaurantStoreRoom) {
		int itemQuantityRequestedInt = (int) Integer.parseInt(itemQuantityRequested);

		for (int i = 0; i < inventoryCode.length; i++) {

			int presentInventory = (restaurantStoreRoom.getInventoryItemsMap().get(inventoryCode[i]).getQuantity());
			int requestedInventory = (itemIngredientValues[i] * itemQuantityRequestedInt);

			if (presentInventory - requestedInventory < 0) {
				canFillOrder = false;
				StoreRoomMessage = "This Cannot be added to your order, there are Not Enough "
						+ restaurantStoreRoom.getInventoryItemsMap().get(inventoryCode[i]).getName()
						+ " Available to Fill this order";
				System.out.println(StoreRoomMessage);
				break; /*
						 * IMPORTANT! This Break Ends FOR Loop immediately after
						 * setting the "canFillOrder" boolean value to "False",
						 * This not only let you know where the shortage of
						 * inventory is, but also makes sure that no inventory
						 * is reduced and no incomplete items are added to the
						 * order
						 */
			} else {
				canFillOrder = true;
				StoreRoomMessage = "Enough "
						+ restaurantStoreRoom.getInventoryItemsMap().get(inventoryCode[i]).getName() + " to fill order";
				// The line above will need to be removed in Final Version - I
				// am presently using it make sure all inventory is being
				// counted properly
				System.out.println(StoreRoomMessage);
			}
		}
		if (canFillOrder) {
			decrementStockTool(itemIngredientValues, itemQuantityRequested, inventoryCode, restaurantStoreRoom);
		}
		return StoreRoomMessage;
	}

	// ***UNIVERSAL Inventory Level Reducer ***

	public void decrementStockTool(int[] sandwichIngredientValues, String itemQuantityRequested, String[] inventoryCode,
			StoreRoom restaurantStoreRoom) {
		int itemQuantityRequestedInt = (int) Integer.parseInt(itemQuantityRequested);
		if (canFillOrder) {
			for (int i = 0; i < inventoryCode.length; i++) {
				int requestedInventory = (sandwichIngredientValues[i] * itemQuantityRequestedInt);
				restaurantStoreRoom.subtractOrderedInventory(inventoryCode[i], requestedInventory);
			}
		}
		return;
	}

	public void createNewOrder() {
		balance = 0;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public boolean isCanFillOrder() {
		return canFillOrder;
	}

	public void setCanFillOrder(boolean canFillOrder) {
		this.canFillOrder = canFillOrder;
	}

	public int getPresentInventory() {
		return presentInventory;
	}

	public void setPresentInventory(int presentInventory) {
		this.presentInventory = presentInventory;
	}

	public int getRequestedInventory() {
		return requestedInventory;
	}

	public void setRequestedInventory(int requestedInventory) {
		this.requestedInventory = requestedInventory;
	}

}

// Left over Code that is not used and needs to be removed when project is
// completed

// //Display Order on Console
// public void displayReceipt(List<Item> orderedItems) {
// int loopTotal = orderedItems.size();
// for (int i = 0; i > loopTotal; i++) {
// System.out.printf("%-40s",orderedItems.get(i).getName());
// System.out.printf("%-40s",orderedItems.get(i).getPrice());
// System.out.println(" ");
// }

// }

// Inventory Level Checkers / Reducers (ILCRs)

// ***SANDWICH ILCRs ****
// public String decrementSandwichStock(String sandwichProductCode, Map<String,
// Sandwich> sandwichMap,
// int[] sandwichIngredientValues, String itemQuantityRequested, String[]
// inventoryCode,
// Map<String, Inventory> inventoryMap, StoreRoom tempStoreRoom) {
// int itemQuantityRequestedInt = (int) Integer.parseInt(itemQuantityRequested);
// String StoreRoomMessage = " ";
//
// for (int i = 0; i < inventoryCode.length; i++) {
//
// int presentInventory = (inventoryMap.get(inventoryCode[i]).getQuantity());
// int requestedInventory = (sandwichIngredientValues[i] *
// itemQuantityRequestedInt);
//
// if (presentInventory - requestedInventory < 0) {
// canFillOrder = false;
// StoreRoomMessage = "This Cannot be added to your order, there are Not Enough
// " + inventoryMap.get(inventoryCode[i]).getName()
// + " Available to fill order";
// System.out.println(StoreRoomMessage);
// break; /*
// * IMPORTANT! This Break Ends FOR Loop immediately after
// * setting the "canFillOrder" boolean value to "False",
// * This not only let you know where the shortage of
// * inventory is, but also makes sure this item does not
// * get added to the order
// */
// } else {
// canFillOrder = true;
// StoreRoomMessage = "Enough " + inventoryMap.get(inventoryCode[i]).getName() +
// " to fill order";
// // The line above will need to be removed in Final Version - I am presently
// using it make sure all inventory is being counted properly
// System.out.println(StoreRoomMessage);
// tempStoreRoom.subtractOrderedInventory(inventoryCode[i], requestedInventory);
//
// }
// }
// return StoreRoomMessage;
//
// }
//
//// ***DRINK ILCRs***
//
// public String decrementDrinkStock(String drinkProductCode, Map<String, Drink>
// drinkMap,
// String itemQuantityRequested, String[] inventoryCode, Map<String, Inventory>
// inventoryMap,
// StoreRoom tempStoreRoom) {
// int itemQuantityRequestedInt = (int) Integer.parseInt(itemQuantityRequested);
//
// String StoreRoomMessage = " ";
//
// for (int i = 0; i < inventoryCode.length; i++) {
// int presentInventory = (inventoryMap.get(inventoryCode[i]).getQuantity());
// int requestedInventory = (drinkMap.get(drinkProductCode).getOunces() *
// itemQuantityRequestedInt);
//
// if (presentInventory - requestedInventory < 0) {
// canFillOrder = false;
// StoreRoomMessage = "This Cannot be added to your order, there are Not Enough
// " + inventoryMap.get(inventoryCode).getName() + " available to fill order";
// System.out.println(StoreRoomMessage);
// } else {
// canFillOrder = true;
// StoreRoomMessage = "Everthing Checked Out";
// System.out.println(StoreRoomMessage);
// tempStoreRoom.subtractOrderedInventory(inventoryCode[i], requestedInventory);
// }
// }
// return StoreRoomMessage;
// }
//
//// ***SIDES ILCRs***
//
// public String decrementSidesStock(String sidesProductCode, Map<String, Sides>
// sidesMap,
// String itemQuantityRequested, String inventoryCode, Map<String, Inventory>
// inventoryMap,
// StoreRoom tempStoreRoom) {
// int itemQuantityRequestedInt = (int) Integer.parseInt(itemQuantityRequested);
//
// String StoreRoomMessage = " ";
// int presentInventory = (inventoryMap.get(inventoryCode).getQuantity());
// int requestedInventory =
// (sidesMap.get(sidesProductCode).getSizeNumericalValue() *
// itemQuantityRequestedInt);
//
// if (presentInventory - requestedInventory < 0) {
// canFillOrder = false;
//
// StoreRoomMessage = "This Cannot be added to your order, there are Not Enough
// " + inventoryMap.get(inventoryCode).getName() + " available to fill order";
// System.out.println(StoreRoomMessage);
// } else {
// canFillOrder = true;
// StoreRoomMessage = "Everthing Checked Out";
// System.out.println(StoreRoomMessage);
// tempStoreRoom.subtractOrderedInventory(inventoryCode, requestedInventory);
// }
// return StoreRoomMessage;
// }
