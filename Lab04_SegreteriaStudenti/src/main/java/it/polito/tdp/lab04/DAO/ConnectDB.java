package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB 
{	
	// check user e password
	static private final String jdbcUrl = "jdbc:mariadb://127.0.0.1/iscritticorsi?user=root&password=root";

	public static Connection getConnection() 
	{
		try 
		{
			Connection connection = DriverManager.getConnection(jdbcUrl);
			return connection;
		} 
		catch (SQLException sqle) 
		{
			sqle.printStackTrace();
			throw new RuntimeException(String.format("Cannot get a connection to %s", jdbcUrl), sqle);
		}
	}

}
