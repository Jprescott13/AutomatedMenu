package ResturantSupporters;



public class KensBurgerJointSupportClass {

	public KensBurgerJointSupportClass() {
		// TODO Auto-generated constructor stub
	}
	
	public void startMenu(){
		System.out.println(" ");
		System.out.println(
				"************************************************************************************************");
		System.out.printf("%-48s", "                                       Welcome to Ken's Burger Joint ");
		System.out.println(" ");
		System.out.println(
				"************************************************************************************************");
		System.out.printf("%-60s", "                                                     MENU ");
		System.out.println(" ");
		System.out.println(
				"************************************************************************************************");
		System.out.println(" ");
		System.out.printf("%-40s", "**Sandwiches**");
		System.out.printf("%-40s", "**Drinks**");
		System.out.printf("%-40s", "**Sides**");
		System.out.println(" ");
		System.out.println(" ");	
		
	}
	
	public void OrderMenu(){
		System.out.println("Please Select from the Following Options>>");
		System.out.println("[1] Place Order");
		System.out.println("[2] Check Inventory");
		System.out.println("[3] Complete Order");
		System.out.println("*****************************");
		System.out.println("Enter Your Selection>>");
	}
	
	public void BeverageOptionMenu1() {
		System.out.println("What type of Soda Would you like>?");
		System.out.println("[1] Cola");
		System.out.println("[2] Lemon-Lime");
		System.out.println("[3] Rootbeer");
		System.out.print("Enter Your Selection>>");
	}
	
	public void BeverageOptionMenu2() {
		System.out.println("Regular Or Diet?");
		System.out.println("[1] Regular");
		System.out.println("[2] Diet");
		System.out.print("Enter Your Selection>>");
	}
	
	public void BeverageOptionMenu3() {
		System.out.println("What type of Soda Would you like>?");
		System.out.println("[1] Chocolate");
		System.out.println("[2] Vanilla");
		System.out.println("[3] Strawberry");
		System.out.print("Enter Your Selection>>");
	}
	
	

}
