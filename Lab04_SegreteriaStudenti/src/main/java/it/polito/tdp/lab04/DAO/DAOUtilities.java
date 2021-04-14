package it.polito.tdp.lab04.DAO;

public abstract class DAOUtilities
{
	public static void close(AutoCloseable... resources) throws Exception
	{
		for(AutoCloseable r : resources)
			r.close();
	}
}
