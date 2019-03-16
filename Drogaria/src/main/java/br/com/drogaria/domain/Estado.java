package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")

//Declara que a classe é uma entidade do Hibernate (Diz que irá gerar uma tabela da entidade)
@Entity
public class Estado extends GenericDomain {

	@Column(length = 2, nullable = false) // Diz que o atributo terá o valor 2 de comprimento de string e nao pode ser
											// vazia
	private String sigla;

	@Column(length = 50, nullable = false) // Diz que o atributo terá o valor de 50 e não será vazia
	private String nome;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
