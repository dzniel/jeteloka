package model;

import database.Connect;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Apartment extends Building {

	private int floorNumber;
	
	private static Connect connect = Connect.getConnection();
	
	public static ArrayList<Apartment> getAll() {
		String query = "select * from apartment";
		ArrayList<Apartment> apartments = new ArrayList<>();
		Apartment apartment = null;
		
		try {
			ResultSet rs = connect.executeQuery(query);
			
			while(rs.next()) {
				apartment = new Apartment();
				apartment.setId(rs.getString("buildingID"));
				apartment.setName(rs.getString("name"));
				apartment.setFloorNumber(rs.getInt("floorNumber"));
				apartment.setLocation(rs.getString("location"));
				apartment.setArea(rs.getInt("area"));
				apartments.add(apartment);
			}
			
			return apartments;
		} catch (Exception e) {
			return apartments;
		}
	}
	
	public static Apartment get(String id) {
		String query = "select * from apartment where buildingID = '" + id + "'";
		Apartment apartment = null;
		
		try {
			ResultSet rs = connect.executeQuery(query);
			
			while(rs.next()) {
				apartment = new Apartment();
				apartment.setId(rs.getString("buildingID"));
				apartment.setName(rs.getString("name"));
				apartment.setFloorNumber(rs.getInt("floorNumber"));
				apartment.setLocation(rs.getString("location"));
				apartment.setArea(rs.getInt("area"));
			}
			
			return apartment;
		} catch (Exception e) {
			return apartment;
		}
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	@Override
	public void printBuilding(int index) {
		System.out.printf("| %-5d | %-5s | %-30s | %-11s | %-15s |  - %17s | %-5d |  - %5s | %d%-11s |\n", 
						  index,
						  this.id,
						  this.name,
						  "Apartment",
						  this.location,
						  "",
						  this.area,
						  "",
						  this.floorNumber,
						  "th Floor");
	}
}
