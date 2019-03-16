package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.CidadeDAO;
import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.domain.Cidade;
import br.com.drogaria.domain.Estado;
import br.com.drogaria.domain.Pessoa;

@SuppressWarnings({ "deprecation", "serial" })
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private Estado estado;

	private List<Estado> estados;

	private List<Cidade> cidades;

	public void salvar() {
		try {

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);

			pessoas = pessoaDAO.list("nome");

			novo(); // Efetua Um Refresh Em Tudo

			Messages.addGlobalInfo("Pessoa Salva Com Sucesso!");

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Salvar!");
			ex.printStackTrace();
		}

	}

	public void novo() {
		try {
			pessoa = new Pessoa(); // Limpa Pessoa

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.list(); // Recarrega Estados

			estado = new Estado(); // Recarrega Estados
			cidades = new ArrayList<Cidade>(); // Recarrega Cidades

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar As Cidades!");
			ex.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.list("nome");

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Ás Pessoas!");
			ex.printStackTrace();
		}
	}

	// ActionEvent serve pra capturar o que foi mandado pela view

	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada"); // Diz qual foi o

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.delete(pessoa);
			Messages.addGlobalInfo("Pessoa Excluída Com Sucesso!"); // componente clicado

			pessoas = pessoaDAO.list();

		} catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu Um Erro Ao Excluir O Estado!");
			ex.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			estado = pessoa.getCidade().getEstado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.list("nome");

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma pessoa");
		}

	}

	public void popular() {
		try {
			if (estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
			} else {
				cidades = new ArrayList<>();
			}

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar As Pessoas!");
			ex.printStackTrace();
		}

	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
