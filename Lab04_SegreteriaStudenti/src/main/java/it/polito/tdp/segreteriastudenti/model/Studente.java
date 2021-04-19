package it.polito.tdp.segreteriastudenti.model;

public class Studente 
{
	private int matricola;
	private String cognome;
	private String nome;
	private String corsoDiStudi;
	
	
	public Studente(int matricola, String cognome, String nome, String corsoDiStudi)
	{
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.corsoDiStudi = corsoDiStudi;
	}

	public int getMatricola()
	{
		return this.matricola;
	}

	public String getCognome()
	{
		return this.cognome;
	}

	public String getNome()
	{
		return this.nome;
	}

	public String getCorsoDiStudi()
	{
		return this.corsoDiStudi;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + matricola;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}

	public String print()
	{
		return String.format("%-10d %-23s %-23s %-15s",
								this.matricola, this.cognome, this.nome, this.corsoDiStudi);
	}

}
