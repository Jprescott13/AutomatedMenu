package restaurant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import generateMenus.Register;
import generateMenus.StoreRoom;
import menuItemClasses.Item;
import menuItemClasses.Sandwich;
import transaction.Order;

public class KensBurgers {

	public static void main(String[] args) {

		File inputSandwichFile = new File("SandwichMenu.csv");
		File inputDrinkFile = new File("Menu2.csv");
		File inputSidesFile = new File("Menu3.csv");
		File inputInventoryFile = new File("Inventory.csv");

		Register kensBurgerJointRegister = new Register(inputSandwichFile, inputDrinkFile, inputSidesFile);
		int sandwichMenuLength = kensBurgerJointRegister.displaySandwichMenu().size();
		int drinkMenuLength = kensBurgerJointRegister.displayDrinkMenu().size();
		int sidesMenuLength = kensBurgerJointRegister.displaySidesMenu().size();

		StoreRoom kensBurgerJointStoreRoom = new StoreRoom(inputInventoryFile);

		Order o = new Order();

		List<Item> itemsAddedToOrder = new ArrayList<Item>(); // Used to Make
																// Receipt
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		
		//CREATE VISUAL DISPLAY OF MENU
		System.out.println(" ");
		System.out.println(
				"************************************************************************************************");
		System.out.printf("%-48s", "                                       Welcome to Ken's Burger Joint ");
		System.out.println(" ");
		System.out.println(
				"************************************************************************************************");
		System.out.println(" ");
		System.out.printf("%-40s", "**Sandwiches**");
		System.out.printf("%-40s", "**Drinks**");
		System.out.printf("%-40s", "**Sides**");
		System.out.println(" ");
		System.out.println(" ");

		for (int i = 0; i < Math.max(Math.max(sandwichMenuLength, drinkMenuLength), sidesMenuLength); i++) {
			if (i < sandwichMenuLength) {
				System.out.printf("%-40s", kensBurgerJointRegister.displaySandwichMenu().get(i));
			} else {
				System.out.printf("%-40s", "  ");
			}
			if (i < drinkMenuLength) {
				System.out.printf("%-40s", kensBurgerJointRegister.displayDrinkMenu().get(i));
			} else {
				System.out.printf("%-40s", "  ");
			}
			if (i < sidesMenuLength) {
				System.out.printf("%-40s", kensBurgerJointRegister.displaySidesMenu().get(i));
				System.out.println(" ");
			} else {
				System.out.printf("%-40s", "  ");
				System.out.println(" ");
			}
		}

		System.out.println("Please Select from the Following Options>>");
		System.out.println("[1] Place Order");
		System.out.println("[2] Check Inventory");
		System.out.println("*****************************");
		System.out.print("Enter Your Selection>>");
		int userInput = scan.nextInt();

		// USER SELECT "PLACE ORDER"
		if (userInput == 1) {

			System.out.println(" ");
			System.out.println("Use Product Code to Make your Selection");
			System.out.print("Enter Your Selection>>");

			while (!o.orderStatus()) {
				String[] orderedItemComponetCodesArray = null;

				scan.reset();
				String userInput2 = scan.next();

				// USER ORDERS A SANDWICH
				if (userInput2.substring(0, 1).equals("S")) {

					Sandwich sandwich = kensBurgerJointRegister.getSandwichMenu().get(userInput2);
					int[] sandwichComponetValues = new int[] {
							sandwich.getBun(),
							sandwich.getPatties(),
							sandwich.getCheese(),
							sandwich.getLettuce(),
							sandwich.getTomato(),
							sandwich.getOnion() };

					System.out.print("Please Enter Quantity>>");
					scan.reset();
					String userInput3 = scan.next();
					if (sandwich.getName().contains("Chicken")) {
						orderedItemComponetCodesArray = new String[] { "101", "103", "104", "105", "106", "107" };
					} else {
						orderedItemComponetCodesArray = new String[] { "101", "102", "104", "105", "106", "107" };
					}

					o.checkAndDecrementStock(sandwichComponetValues, userInput3, orderedItemComponetCodesArray,
							kensBurgerJointStoreRoom);

					if (o.isCanFillOrder()) {
						System.out.println(kensBurgerJointRegister.addToOrder(o, userInput2, userInput3));
					}

					// USER ORDERS A DRINK
				} else if (userInput2.substring(0, 1).equals("D")) {
					int[] drinkComponetValues = new int[] {
							kensBurgerJointRegister.getDrinkMenu().get(userInput2).getOunces() };

					System.out.println("Please Enter Quantity>>");
					scan.reset();
					String userInput3 = scan.next();
					if (kensBurgerJointRegister.getDrinkMenu().get(userInput2).getName().contains("Soda")) {
						System.out.println("What type of Soda Would you like>?");
						System.out.println("[1] Cola");
						System.out.println("[2] Lemon-Lime");
						System.out.println("[3] Rootbeer");
						System.out.print("Enter Your Selection>>");
						scan.reset();
						String userInput3b = scan.next();
						if (userInput3b.equals("1") || userInput3b.equals("2")) {
							System.out.println("Regular Or Diet?");
							System.out.println("[1] Regular");
							System.out.println("[2] Diet");
							System.out.print("Enter Your Selection>>");
							scan.reset();
							String userInput3c = scan.next();

							if (userInput3b.equals("1") && userInput3c.equals("1")) {
								orderedItemComponetCodesArray = new String[] { "201" };
							} else if (userInput3b.equals("1") && userInput3c.equals("2")) {
								orderedItemComponetCodesArray = new String[] { "202" };
							} else if (userInput3b.equals("2") && userInput3c.equals("1")) {
								orderedItemComponetCodesArray = new String[] { "203" };
							} else if (userInput3b.equals("2") && userInput3c.equals("2")) {
								orderedItemComponetCodesArray = new String[] { "204" };
							}
						} else if (userInput3b.equals("3")) {
							orderedItemComponetCodesArray = new String[] { "205" };
						}

					} else if (kensBurgerJointRegister.getDrinkMenu().get(userInput2).getName().contains("Coffee")) {
						orderedItemComponetCodesArray = new String[] { "206" };
						;
					} else if (kensBurgerJointRegister.getDrinkMenu().get(userInput2).getName().contains("Shake")) {
						System.out.println("What type of Soda Would you like>?");
						System.out.println("[1] Chocolate");
						System.out.println("[2] Vanilla");
						System.out.println("[3] Strawberry");
						System.out.print("Enter Your Selection>>");
						scan.reset();
						String userInput3b = scan.next();
						if (userInput3b.equals("1")) {
							orderedItemComponetCodesArray = new String[] { "207" };
						} else if (userInput3b.equals("2")) {
							orderedItemComponetCodesArray = new String[] { "208" };
						} else if (userInput3b.equals("3")) {
							orderedItemComponetCodesArray = new String[] { "209" };
						}
					}
					o.checkAndDecrementStock(drinkComponetValues, userInput3, orderedItemComponetCodesArray,
							kensBurgerJointStoreRoom);

					if (o.isCanFillOrder()) {
						System.out.println(kensBurgerJointRegister.addToOrder(o, userInput2, userInput3));
					}

					// USER ORDERS A SIDE ITEM
				} else if (userInput2.substring(0, 1).equals("T")) {
					int[] sidesComponetValues = new int[] {
							kensBurgerJointRegister.getSidesMenu().get(userInput2).getSizeNumericalValue() };
					System.out.println("Please Enter Quantity>>");
					scan.reset();
					String userInput3 = scan.next();
					if (kensBurgerJointRegister.getSidesMenu().get(userInput2).getName().contains("Rings")) {
						orderedItemComponetCodesArray = new String[] { "301" };
					} else if (kensBurgerJointRegister.getSidesMenu().get(userInput2).getName().contains("Fry")) {
						orderedItemComponetCodesArray = new String[] { "302" };
					} else if (kensBurgerJointRegister.getSidesMenu().get(userInput2).getName().contains("Cream")) {
						orderedItemComponetCodesArray = new String[] { "303" };
					}
					o.checkAndDecrementStock(sidesComponetValues, userInput3, orderedItemComponetCodesArray,
							kensBurgerJointStoreRoom);

					if (o.isCanFillOrder()) {
						System.out.println(kensBurgerJointRegister.addToOrder(o, userInput2, userInput3));
					}
					// USER WANT TO COMPLETE ORDER
				} else if (userInput2.equals("C")) {

					System.out.println("************Receipt************");
					System.out.println("******Ken's Burger Joint*******");
					System.out.println("");
					System.out.printf("%-30s", "Item");
					System.out.printf("%-30s", "Price");
					System.out.println("");
					itemsAddedToOrder = kensBurgerJointRegister.getItemsToBePrintedToReceipt();
					kensBurgerJointRegister.displayOrderToConsole(itemsAddedToOrder, o);
					System.out.println("");
					System.out.printf("%-30s", "TOTAL");
					System.out.printf("%-30s", o.getBalanceInDollars(kensBurgerJointRegister.getTotalCostInt()));
					System.out.println("");
					o.setComplete(true);

				} else {
					System.out.println("We didn't UnderStand that Product Code");
					System.out.println("Please Try again using a Capital Letter and Number Combination");
				}

				if (!o.orderStatus()) {
					System.out.println("************************");
					System.out.println(
							"Enter another Product Code to add to your Order or Type [C] to Complete your Transaction");
					System.out.println("Enter Your Selection>>");
				}
			}
		}
		// USER WANTS TO VIEW INVENTORY
		if (userInput == 2) {
			System.out.println("******Ken's Inventory*******");
			System.out.println("");
			System.out.println("Code | Product Name | Cost ($) | Units  ");
			for (String s : kensBurgerJointStoreRoom.displayInventory()) {
				System.out.println(s);
			}
			System.out.println(" ");
			System.out.println("*********************************");
			System.out.println("What would you like to do next?");
			System.out.println("[1] Return to Main Menu");
			System.out.println("[2] End Program");
			System.out.print("Enter Your Selection>>");
			scan.reset();
			String userInput2 = scan.next();
			if (userInput2.equalsIgnoreCase("1")) {
				System.out.print("this doesn't work yet"); // Could add another
															// While Loop that
															// will take you
															// back to the
															// beginning
			} else {
				System.out.print("Thank You, Please Come Again");
			}
		}
	}
}
