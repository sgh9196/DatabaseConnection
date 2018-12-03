package driving.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCBind {
	
	private static Connection connection;
	
	public Connection connect() throws SQLException {
		
		try {
			
			if(connection == null) {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC", "root", "epqldks");
			}
			
			return connection;
			
		} catch(SQLException e) {
			throw e;
		}
		
	}
	
	/* Database 생성 */
	public boolean createDatabase(String dbName) {
		
		boolean tmp = false;
		
		try {
			
			Statement stmt = connect().createStatement();
			stmt.executeUpdate("CREATE DATABASE " + dbName);
			tmp = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tmp;
		
	}
	
}
