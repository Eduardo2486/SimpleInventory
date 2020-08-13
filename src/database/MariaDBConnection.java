package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class MariaDBConnection {
	public static Connection Connector() {
		Connection conn = null;
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			
            conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/inventory", "root", "");
            
            System.out.println("Connected database successfully...");
           
			return conn;
		}catch(Exception e) {
			System.out.print("There is an error with the database connection");
			return null;
		}
	}
}
