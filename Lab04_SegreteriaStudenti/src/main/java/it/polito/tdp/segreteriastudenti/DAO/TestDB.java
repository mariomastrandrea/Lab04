package it.polito.tdp.segreteriastudenti.DAO;

public class TestDB 
{

	public static void main(String[] args) 
	{

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		cdao.getAllCorsi();
		
		
	}

}
