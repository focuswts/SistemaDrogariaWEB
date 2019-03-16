package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.ClienteDAO;
import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.domain.Cliente;
import br.com.drogaria.domain.Pessoa;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private Cliente cliente;
	private List<Cliente> clientes;
	private List<Pessoa> pessoas;

	public void salvar() {
		try {

			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);

			clientes = clienteDAO.list();

			novo(); // Efetua Um Refresh Em Tudo

			Messages.addGlobalInfo("Cliente Salvo Com Sucesso!");

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Salvar!");
			ex.printStackTrace();
		}

	}

	public void novo() {
		try {
			cliente = new Cliente(); // Limpa Cliente

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.list(); // Recarrega Pessoa

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar As Pessoas!");
			ex.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.list("dataCadastro");

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Os Clientes!");
			ex.printStackTrace();
		}
	}

	// ActionEvent serve pra capturar o que foi mandado pela view

	public void excluir(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado"); // Diz qual foi o

			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.delete(cliente);
			Messages.addGlobalInfo("Cliente Excluído Com Sucesso!"); // componente clicado

			clientes = clienteDAO.list();

		} catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu Um Erro Ao Excluir O Cliente!");
			ex.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.list();
		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Os Clientes!");
			ex.printStackTrace();
		}

	}



	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
