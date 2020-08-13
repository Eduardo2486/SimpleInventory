package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.MariaDBConnection;

public class LoginModel {
	Connection connection;
	
	public LoginModel() {
		connection = MariaDBConnection.Connector();
		if(connection == null) {
			System.out.println("Error: No connection with the database.");
			System.exit(0);
		}
	}
	
	public boolean Login(String username, String password) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		}catch(Exception e) {
			System.out.println("There is an error with your username or password: "+e);
			return false;
		}finally {
			ps.close();
			rs.close();
		}
	}
}
