package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")

@Entity
public class Cidade extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String nome;

				// estados,porém somente um para a cidade
	@ManyToOne // Uma cidade pode possuir somente um estado, ManyToOne diz que são Vários
	
	//"@JoinColumn" serve para customizar colunas com chave estrangeira
	@JoinColumn(nullable = false) //Diz que a chave estrangeira não pode ser nula
	private Estado estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
