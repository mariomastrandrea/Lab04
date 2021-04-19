package it.polito.tdp.segreteriastudenti.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.segreteriastudenti.model.Corso;
import it.polito.tdp.segreteriastudenti.model.Studente;

public class StudenteDAO 
{
	/*
	 *  Retrieves all 'Studente' objects stored in DB
	 */
	public Map<Integer, Studente> getAllStudentiByMatricola()
	{
		String sqlQuery = String.format("%s %s", 
										"SELECT *",
										"FROM studente");
		
		Map<Integer, Studente> studentiByMatricola = new HashMap<>();

		try( Connection connection = ConnectDB.getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet queryResult = statement.executeQuery(sqlQuery))
		{
			while(queryResult.next())
			{
				int matricola = queryResult.getInt("matricola");
				String cognome = queryResult.getString("cognome");
				String nome = queryResult.getString("nome");
				String CDS = queryResult.getString("CDS");

				Studente s = new Studente(matricola, cognome, nome, CDS);
				studentiByMatricola.put(matricola, s);
			}
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("SQL error in 'getAllStudenti()'", sqle);
		}
	
		return studentiByMatricola;
	}
	
	public Set<Corso> getCorsiACuiEIscritto(Studente studente) 
	{
		String sqlQuery = String.format("%s %s %s %s",
										"SELECT c.codins, c.crediti, c.nome, c.pd",
										"FROM studente s, corso c, iscrizione i",
										"WHERE s.matricola = i.matricola AND c.codins = i.codins",
												"AND s.matricola = ?");
		
		Set<Corso> corsi = new HashSet<>();
		
		try( Connection connection = ConnectDB.getConnection() )
		{
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, studente.getMatricola());
			ResultSet queryResult = statement.executeQuery();
			
			while(queryResult.next())
			{
				String codiceInsegnamento = queryResult.getString("codins");
				int crediti = queryResult.getInt("crediti");
				String nome = queryResult.getString("nome");
				int periodoDidattico = queryResult.getInt("pd");

				Corso corso = new Corso(codiceInsegnamento, crediti, nome, periodoDidattico);
				corsi.add(corso);
			}
			
			DAOUtilities.closeResources(queryResult, statement);
		}
		catch (SQLException sqle)
		{
			throw new RuntimeException("SQL error in 'getCorsiACuiEIscritto()", sqle);
		}
		
		return corsi;
	}
}
