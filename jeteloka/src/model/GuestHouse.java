package model;

import database.Connect;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GuestHouse extends Building {

	private String ownerName;
	private String isGarage;
	
	private static Connect connect = Connect.getConnection();
	
	public static ArrayList<GuestHouse> getAll() {
		String query = "select * from guesthouse";
		ArrayList<GuestHouse> guestHouses = new ArrayList<>();
		GuestHouse guestHouse = null;
		
		try {
			ResultSet rs = connect.executeQuery(query);
			
			while(rs.next()) {
				guestHouse = new GuestHouse();
				guestHouse.setId(rs.getString("buildingID"));
				guestHouse.setName(rs.getString("name"));
				guestHouse.setLocation(rs.getString("location"));
				guestHouse.setOwnerName(rs.getString("ownerName"));
				
				switch(rs.getString("garage")) {
				case "0":
					guestHouse.setGarage("No");
					break;
				case "1":
					guestHouse.setGarage("Yes");
					break;
				}
				
				guestHouse.setArea(rs.getInt("area"));
				guestHouses.add(guestHouse);
			}
			
			return guestHouses;
		} catch (Exception e) {
			return guestHouses;
		}
	}
	
	public static GuestHouse get(String id) {
		String query = "select * from guesthouse where buildingID = '" + id + "'";
		GuestHouse guestHouse = null;
		
		try {
			ResultSet rs = connect.executeQuery(query);
			
			while(rs.next()) {
				guestHouse = new GuestHouse();
				guestHouse.setId(rs.getString("buildingID"));
				guestHouse.setName(rs.getString("name"));
				guestHouse.setLocation(rs.getString("location"));
				guestHouse.setOwnerName(rs.getString("ownerName"));
				
				switch(rs.getString("garage")) {
				case "0":
					guestHouse.setGarage("No");
					break;
				case "1":
					guestHouse.setGarage("Yes");
					break;
				}
				
				guestHouse.setArea(rs.getInt("area"));
			}
			
			return guestHouse;
		} catch (Exception e) {
			return guestHouse;
		}
	}
	
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getGarage() {
		return isGarage;
	}

	public void setGarage(String isGarage) {
		this.isGarage = isGarage;
	}

	@Override
	public void printBuilding(int index) {
		System.out.printf("| %-5d | %-5s | %-30s | %-11s | %-15s | %-20s | %-5d | %-8s |  - %10s |\n", 
						  index,
						  this.id,
						  this.name,
						  "Guest House",
						  this.location,
						  this.ownerName,
						  this.area,
						  this.isGarage,
						  "");
	}
}
