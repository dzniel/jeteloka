package controller;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	Scanner scan = new Scanner(System.in);
	Validator validate = new Validator();

	public void rent() {
		ArrayList<GuestHouse> guestHouses = GuestHouse.getAll();
		ArrayList<Apartment> apartments = Apartment.getAll();
		
		System.out.println("============================================================================================================================================");
		System.out.printf("|%62s %-75s|\n", "", "Building List");
		System.out.println("============================================================================================================================================");
		System.out.printf("| %-5s | %-5s | %-30s | %-11s | %-15s | %-20s | %-5s | %-8s | %-13s |\n", 
				  "No",
				  "ID",
				  "Building Name",
				  "Type",
				  "Location",
				  "Owner Name",
				  "Area",
				  "Garage",
				  "Floor");
		System.out.println("============================================================================================================================================");
		
		for(GuestHouse guestHouse : guestHouses) {
			guestHouse.printBuilding(guestHouses.indexOf(guestHouse) + 1);
		}
		
		for(Apartment apartment : apartments) {
			apartment.printBuilding(apartments.indexOf(apartment) + 6);
		}
		
		System.out.println("============================================================================================================================================");
		
		int choice = 0;
		
		do {
			System.out.print("Choose Building [1-10]: ");
			choice = scan.nextInt();
			scan.nextLine();
		} while(choice < 1 || choice > 10);
		
		int rentTime;
		
		do {
			System.out.print("Input rent time [1-60] (inclusive): ");
			rentTime = scan.nextInt();
			scan.nextLine();
		} while(!validate.rentTime(rentTime));
		
		String userName;
		
		do {
			System.out.print("Input username [1-15 characters] (inclusive): ");
			userName = scan.nextLine();
		} while(!validate.userName(userName));
		
		String email;
		
		do {
			System.out.print("Input email [ends with '@gmail.com']: ");
			email = scan.nextLine();
		} while(!validate.email(email));
		
		String phone;
		
		do {
			System.out.print("Input valid phone number [10-13 characters] (inclusive): ");
			phone = scan.nextLine();
		} while(!validate.phone(phone));
		
		String id;
		String type;
		String buildingName;
		int totalPrice = 0;

		if(choice <= 5) {
			type = "Guest House";
			id = guestHouses.get(choice-1).getId();
			GuestHouse gh = GuestHouse.get(id);
			buildingName = gh.name;
			
			if(gh.getGarage() == "Yes") {
				totalPrice = rentTime * gh.area * 1000;
			} else if(gh.getGarage() == "No") {
				totalPrice = rentTime * gh.area * 500;
			}
			
		} else {
			type = "Apartment";
			id = apartments.get(choice-6).getId();
			Apartment ap = Apartment.get(id);
			buildingName = ap.name;
			totalPrice = rentTime * ap.area * ap.getFloorNumber() * 100;
		}

		System.out.println("===================================================");
		System.out.printf("| %13s%-34s |\n", "", "Detail Transaction");
		System.out.println("===================================================");
		System.out.printf("| %-13s : %-31s |\n", "User Name", userName);
		System.out.printf("| %-13s : %-31s |\n", "Type", type);
		System.out.printf("| %-13s : %-31s |\n", "Building Name", buildingName);
		System.out.printf("| %-13s : Rp.%-28s |\n", "Total Price", totalPrice);
		System.out.println("===================================================");
		
		Transaction t = new Transaction();
		t.setRentTime(rentTime);
		t.setName(userName);
		t.setEmail(email);
		t.setPhone(phone);
		t.setBuildingID(id);
		t.insert();

		scan.nextLine();
	}

	public void view() {
		ArrayList<Transaction> transactions = Transaction.getAll();
		
		if(transactions.isEmpty()) {
			emptyTransactionList();
		} else {
			System.out.println("==================================================================================================================");
			System.out.printf("| %47s%-63s |\n", "", "Transaction List");
			System.out.println("==================================================================================================================");
			System.out.printf("| %-2s | %-5s | %-30s | %-15s | %-9s | %-16s | %-15s |\n",
							   "No",
							   "ID",
							   "Building Name",
							   "Building Type",
							   "Rent Time",
							   "User Name",
							   "User Phone");
			System.out.println("==================================================================================================================");
			
			for(Transaction transaction : transactions) {
				transaction.printTransaction(transactions.indexOf(transaction) + 1);
			}
			
			System.out.println("==================================================================================================================");
		}
		
		System.out.println("Wanna go back? [press enter]");
		scan.nextLine();
	}

	public void delete() {
		ArrayList<Transaction> transactions = Transaction.getAll();
		
		if(transactions.isEmpty()) {
			emptyTransactionList();
			
			System.out.println("Wanna go back? [press enter]");
			scan.nextLine();
		} else {
			System.out.println("==================================================================================================================");
			System.out.printf("| %47s%-63s |\n", "", "Transaction List");
			System.out.println("==================================================================================================================");
			System.out.printf("| %-2s | %-5s | %-30s | %-15s | %-9s | %-16s | %-15s |\n",
							   "No",
							   "ID",
							   "Building Name",
							   "Building Type",
							   "Rent Time",
							   "User Name",
							   "User Phone");
			System.out.println("==================================================================================================================");
			
			for(Transaction transaction : transactions) {
				transaction.printTransaction(transactions.indexOf(transaction) + 1);
			}
			
			System.out.println("==================================================================================================================");
			
			int choice;
			
			do {
				System.out.print("Choose to be deleted [1-" + transactions.size() + "]: ");
				choice = scan.nextInt();
				scan.nextLine();
			} while(choice < 1 ||  choice > transactions.size());
			
			transactions.get(choice - 1).delete();
			
			scan.nextLine();
		}
	}
	
	public void emptyTransactionList() {
		System.out.println("==================================================================================================================");
		System.out.printf("| %47s%-63s |\n", "", "Transaction List");
		System.out.println("==================================================================================================================");
		System.out.printf("| %110s |\n", "");
		System.out.printf("| %110s |\n", "");
		System.out.printf("| %44s%-66s |\n", "", "There's No Transaction");
		System.out.printf("| %110s |\n", "");
		System.out.printf("| %110s |\n", "");
		System.out.println("==================================================================================================================");
	}
}
