package view;

import java.util.Scanner;
import controller.Controller;

public class App {

	public App(boolean running) {
		Scanner scan = new Scanner(System.in);
		Controller controller = new Controller();
		
		while(running) {
			System.out.println("Welcome to JeTeLoka");
			System.out.println("1. Rent a Place");
			System.out.println("2. View Transactions");
			System.out.println("3. Delete Transactions");
			System.out.println("4. Exit");
			System.out.println();
			
			int choice;
			
			do {
				System.out.print(">> ");
				choice = scan.nextInt();
				scan.nextLine();
			} while(choice < 1 || choice > 4);
			
			switch(choice) {
			case 1:
				controller.rent();
				break;
			case 2:
				controller.view();
				break;
			case 3:
				controller.delete();
				break;
			case 4:
				System.out.println("Thank you for using our application!");
				running = false;
				break;
			}
		}
		
		scan.close();
	}
	
	public static void main(String[] args) {
		new App(true);
	}
}
