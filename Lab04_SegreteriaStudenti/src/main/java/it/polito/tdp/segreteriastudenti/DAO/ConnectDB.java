package it.polito.tdp.segreteriastudenti.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB 
{	
	static private final String jdbcUrl = "jdbc:mariadb://127.0.0.1/iscritticorsi";
	static private final String user = "root";
	static private final String password = "root";
	

	public static Connection getConnection() 
	{
		try 
		{
			Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
			return connection;
		} 
		catch (SQLException sqle) 
		{
			sqle.printStackTrace();
			throw new RuntimeException(String.format("Cannot get a connection to URL: %s", jdbcUrl), sqle);
		}
	}

}
