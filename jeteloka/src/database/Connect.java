package database;

import java.sql.*;

public final class Connect {
	
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "jeteloka";
	private final String HOST = "localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	private Connection connection;
	private Statement statement;
	private static Connect connect;
	
	/**
	 * This constructor method uses singleton design pattern,
	 * so this class only contains an instance.
	 */
	private Connect() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to connect to the database, the system is terminated!");
			System.exit(0);
		}
	}
	
	/**
	 * This method is for getting an instance of Connect class.
	 * @return			an instance of Connect class
	 */
	public static synchronized Connect getConnection() {
		return connect = (connect == null) ? new Connect() : connect;
	}
	
	/**
	 * This method is for executing SELECT SQL statements.
	 * @param query		the query statement to be executed
	 * @return			the result of executed query
	 */
	public ResultSet executeQuery(String query) {
		ResultSet resultSet = null;
		
		try {
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	/**
	 * This method is for executing INSERT, UPDATE, DELETE SQL statements.
	 * @param query		the query statement to be executed
	 */
	public void executeUpdate(String query) {
		
		try {
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method compiled a given query into SQL statement.
	 * @param query					the query statement to be executed
	 * @return						the prepared statement object
	 */
	public PreparedStatement prepareStatement(String query) {
		PreparedStatement prepareStatement = null;
		
		try {
			prepareStatement = connection.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prepareStatement;
	}
}