package it.polito.tdp.segreteriastudenti.DAO;

import java.sql.SQLException;

public abstract class DAOUtilities
{
	public static void closeResources(AutoCloseable... resources) throws SQLException 
	{
		try
		{
			for(AutoCloseable r : resources)
				r.close();
		}
		catch(Exception e)
		{
			throw new SQLException("Error occured closing db resources", e);
		}
		
	}
}
