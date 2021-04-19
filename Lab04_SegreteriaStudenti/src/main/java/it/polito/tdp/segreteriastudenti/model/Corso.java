package it.polito.tdp.segreteriastudenti.model;

public class Corso 
{
	private String codiceInsegnamento;
	private int crediti;
	private String nome;
	private int periodoDidattico;
	
	
	public Corso(String codiceInsegnamento, int crediti, String nome, int periodoDidattico)
	{
		this.codiceInsegnamento = codiceInsegnamento;
		this.crediti = crediti;
		this.nome = nome;
		this.periodoDidattico = periodoDidattico;
	}

	public String getCodiceInsegnamento()
	{
		return this.codiceInsegnamento;
	}

	public int getCrediti()
	{
		return this.crediti;
	}

	public String getNome()
	{
		return this.nome;
	}

	public int getPeriodoDidattico()
	{
		return this.periodoDidattico;
	}
	
	@Override
	public String toString()
	{
		return this.nome;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceInsegnamento == null) ? 0 : codiceInsegnamento.hashCode());
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
		Corso other = (Corso) obj;
		if (codiceInsegnamento == null)
		{
			if (other.codiceInsegnamento != null)
				return false;
		}
		else if (!codiceInsegnamento.equals(other.codiceInsegnamento))
			return false;
		return true;
	}

	public String print()
	{
		return String.format("%-8s %-8d %-50s %-7d", 
							this.codiceInsegnamento, this.crediti, this.nome, this.periodoDidattico);
	}
	
}
