package model;

import database.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Transaction {
	
	private String id;
	private String buildingID;
	private String name;
	private String email;
	private String phone;
	private int rentTime;
	
	private static Connect connect = Connect.getConnection();
	
	public static ArrayList<Transaction> getAll() {
		String query = "select * from transaction";
		ArrayList<Transaction> transactions = new ArrayList<>();
		Transaction transaction = null;
		
		try {
			ResultSet rs = connect.executeQuery(query);
			
			while(rs.next()) {
				transaction = new Transaction();
				transaction.setId(rs.getString("transactionID"));
				transaction.setRentTime(rs.getInt("rentTime"));
				transaction.setName(rs.getString("userName"));
				transaction.setEmail(rs.getString("userEmail"));
				transaction.setPhone(rs.getString("userPhone"));
				transaction.setBuildingID(rs.getString("buildingID"));
				transactions.add(transaction);
			}
			
			return transactions;
		} catch (Exception e) {
			return transactions;
		}
	}
	
	public static Transaction get(String id) {
		String query = "select * from where transactionID = '" + id + "'";
		Transaction transaction = null;
		
		try {
			ResultSet rs = connect.executeQuery(query);
			
			while(rs.next()) {
				transaction = new Transaction();
				transaction.setId(rs.getString("transactionID"));
				transaction.setRentTime(rs.getInt("rentTime"));
				transaction.setName(rs.getString("userName"));
				transaction.setEmail(rs.getString("userEmail"));
				transaction.setPhone(rs.getString("userPhone"));
				transaction.setBuildingID(rs.getString("buildingID"));
			}
			
			return transaction;
		} catch (Exception e) {
			return transaction;
		}
	}
	
	public void insert() {
		String query = "insert into transaction values (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, this.generateId());
			ps.setInt(2, this.rentTime);
			ps.setString(3, this.name);
			ps.setString(4, this.email);
			ps.setString(5, this.phone);
			ps.setString(6, this.buildingID);
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Transaction failed!");
			System.exit(0);
		}
		
		System.out.println("Successful transaction!");
	}
	
	public void delete() {
		String query = "delete from transaction where transactionID = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, this.id);
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Fail to delete!");
			System.exit(0);
		}
		
		System.out.println("Successfully deleted!");
	}
	
	public String generateId() {
		String id = "TR";
		
		for(int i = 0; i < 3; i++) {
			Integer digit = ThreadLocalRandom.current().nextInt(0, 10);
			id += digit.toString();
		}
		
		return id;
	}
	
	public void printTransaction(int index) {
		String buildingName = "";
		String type = "";
		
		if(this.buildingID.startsWith("GH")) {
			buildingName = GuestHouse.get(this.buildingID).name;
			type = "Guest House";
		} else if(this.buildingID.startsWith("AP")) {
			buildingName = Apartment.get(this.buildingID).name;
			type = "Apartment";
		}
		
		System.out.printf("| %-2d | %-5s | %-30s | %-15s | %-2d day(s) | %-16s | %-15s |\n",
						  index,
						  this.id,
						  buildingName,
						  type,
						  this.rentTime,
						  this.name,
						  this.phone);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(String buildingID) {
		this.buildingID = buildingID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRentTime() {
		return rentTime;
	}

	public void setRentTime(int rentTime) {
		this.rentTime = rentTime;
	}
}
