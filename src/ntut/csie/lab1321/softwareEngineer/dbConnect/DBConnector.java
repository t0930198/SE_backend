package ntut.csie.lab1321.softwareEngineer.dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	public static Connection connectToMySQL(){
		Connection connection = null;
		String connectionURL = "";
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connectionURL = "jdbc:mysql://127.0.0.1:3306/softwareengineering?user=se&password=1234";
			if(connection == null || connection.isClosed()){
				connection = DriverManager.getConnection(connectionURL);
			}
			
		}catch(ClassNotFoundException e){
				e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
