package it.polito.tdp.segreteriastudenti.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.segreteriastudenti.DAO.CorsoDAO;
import it.polito.tdp.segreteriastudenti.DAO.StudenteDAO;

public class SegreteriaStudentiModel 
{
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	private Set<Corso> corsi;
	private Corso noCorso;
	private Map<Integer, Studente> studentiByMatricola;
	
	
	public SegreteriaStudentiModel()
	{
		this.corsoDao = new CorsoDAO();
		this.studenteDao = new StudenteDAO();
		this.corsi = this.corsoDao.getAllCorsi();
		this.noCorso = new Corso(null, 0, "(nessuno)", 0);
		this.studentiByMatricola = this.studenteDao.getAllStudentiByMatricola();
	}
	
	public Collection<Corso> getAllCorsi()
	{
		return this.corsi;
	}
	
	public Corso getNoCorso()
	{
		return this.noCorso;
	}
	
	public boolean existsStudente(int matricola)
	{
		return this.studentiByMatricola.containsKey(matricola);
	}
	
	public Studente getStudente(int matricola)
	{
		return this.studentiByMatricola.get(matricola);
	}

	public Collection<Studente> getIscrittiA(Corso selectedCorso)
	{
		if(!this.corsi.contains(selectedCorso))
			return null;
		
		return this.corsoDao.getStudentiIscrittiAlCorso(selectedCorso);
	}

	public Collection<Corso> getCorsiStudente(Studente searchedStudente)
	{
		if(!this.studentiByMatricola.containsKey(searchedStudente.getMatricola()))
			return null;
		
		return this.studenteDao.getCorsiACuiEIscritto(searchedStudente);
	}

	public boolean newIscrizione(Studente selectedStudente, Corso selectedCorso)
	{
		return this.corsoDao.newIscrizione(selectedStudente, selectedCorso);
	}
	
	public boolean newIscrizione(Corso selectedCorso, Studente selectedStudente)
	{
		return this.newIscrizione(selectedStudente, selectedCorso);
	}
}
