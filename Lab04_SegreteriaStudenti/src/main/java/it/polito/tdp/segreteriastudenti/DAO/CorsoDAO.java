package it.polito.tdp.segreteriastudenti.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import it.polito.tdp.segreteriastudenti.model.Corso;
import it.polito.tdp.segreteriastudenti.model.Studente;

public class CorsoDAO 
{	
	/*
	 *  Retrieves all 'Corso' objects stored in DB
	 */
	public Set<Corso> getAllCorsi() 
	{
		final String sql = "SELECT * FROM corso";

		Set<Corso> corsi = new HashSet<Corso>();

		try( Connection connection = ConnectDB.getConnection() )
		{
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet queryResult = statement.executeQuery();

			while (queryResult.next()) 
			{
				String codins = queryResult.getString("codins");
				int numeroCrediti = queryResult.getInt("crediti");
				String nome = queryResult.getString("nome");
				int periodoDidattico = queryResult.getInt("pd");

				Corso corso = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(corso);
			}
			
			DAOUtilities.closeResources(queryResult, statement);
		} 
		catch (SQLException sqle) 
		{
			throw new RuntimeException("SQL error in 'getAllCorsi()'", sqle);
		}
		
		return corsi;
	}
	
	/*
	 * Retrieves all students of a particular course
	 */
	public Set<Studente> getStudentiIscrittiAlCorso(Corso corso) 
	{
		String sqlQuery = String.format("%s %s %s %s",
										"SELECT s.matricola, s.cognome, s.nome, s.CDS",
										"FROM studente s, corso c, iscrizione i",
										"WHERE s.matricola = i.matricola AND c.codins = i.codins",
												"AND c.codins = ?");
		
		Set<Studente> iscrittiCorso = new HashSet<>();
		
		try( Connection connection = ConnectDB.getConnection() )
		{
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, corso.getCodiceInsegnamento());
			ResultSet queryResult = statement.executeQuery();
			
			while(queryResult.next())
			{
				int matricola = queryResult.getInt("matricola");
				String cognome = queryResult.getString("cognome");
				String nome = queryResult.getString("nome");
				String CDS = queryResult.getString("CDS");

				Studente iscritto = new Studente(matricola, cognome, nome, CDS);
				iscrittiCorso.add(iscritto);
			}
			
			DAOUtilities.closeResources(queryResult, statement);
		}
		catch (SQLException sqle)
		{
			throw new RuntimeException("SQL error in 'getStudentiIscrittiAlCorso()", sqle);
		}
		
		return iscrittiCorso;
	}

	public boolean newIscrizione(Studente studente, Corso corso) 
	{
		String checkQuery = String.format("%s %s %s",
											"SELECT *",
											"FROM iscrizione",
											"WHERE matricola = ? AND codins = ?");
		Connection connection;
		boolean studentAlreadyEnrolled;
		
		try
		{
			connection = ConnectDB.getConnection();
			
			PreparedStatement statement1 = connection.prepareStatement(checkQuery);
			statement1.setInt(1, studente.getMatricola());
			statement1.setString(2, corso.getCodiceInsegnamento());
			
			ResultSet query1Result = statement1.executeQuery();
			
			studentAlreadyEnrolled = query1Result.next();
			
			DAOUtilities.closeResources(query1Result, statement1);
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("SQL Error in 'newIscrizione()'", sqle);
		}
		
		if(studentAlreadyEnrolled)
			return false;	//iscrizione not registered
		else 
		{
			String sqlInsert = String.format("%s %s", 
											 "INSERT INTO iscrizione (matricola, codins)",
											 	"VALUES (?, ?)");
			int rowInserted = 0;
			
			try
			{
				PreparedStatement statement2 = connection.prepareStatement(sqlInsert);
				statement2.setInt(1, studente.getMatricola());
				statement2.setString(2, corso.getCodiceInsegnamento());
				
				rowInserted = statement2.executeUpdate();
				
				DAOUtilities.closeResources(statement2, connection);
			}
			catch(SQLException sqle)
			{
				throw new RuntimeException("SQL error in 'newIscrizione()'", sqle);
			}
			
			if(rowInserted != 1)
				return false;	//iscrizione not registered correctly
			else
				return true;	//iscrizione registered correctly
		}
	}
	

}
