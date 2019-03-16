package br.com.drogaria.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//Essa classe distribui a chave primária para as outras e necessita ser serializable

@SuppressWarnings("serial") // Avisa o compilador para ignorar avisos do tipo serial

//Anotação diz que esta classe não é uma tabela,mais será usada por outros para gerar tabelas
@MappedSuperclass
public class GenericDomain implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericDomain other = (GenericDomain) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Id // O "@ID" serve para dizer aõ código que é uma chave primária
	@GeneratedValue(strategy = GenerationType.AUTO) // Indica que o banco irá gerenciar a geração da chave
	private long codigo;

	@Override
	public String toString() {
		return String.format("%s[codigo=%d]", getClass().getSimpleName(), getCodigo());
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

}
