package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Pessoa;

@SuppressWarnings({ "deprecation", "serial" })
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable{

private Funcionario funcionario;
private List<Funcionario> funcionarios;
private List<Pessoa> pessoas;
	
public void salvar() {
	try {

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.merge(funcionario);

		funcionarios = funcionarioDAO.list();

		novo(); // Efetua Um Refresh Em Tudo

		Messages.addGlobalInfo("Funcionário Salvo Com Sucesso!");

	} catch (RuntimeException ex) {
		Messages.addGlobalWarn("Ocorreu Um Erro Ao Salvar!");
		ex.printStackTrace();
	}

}

public void novo() {
	try {
		funcionario = new Funcionario(); // Limpa Cliente

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
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.list("dataAdmissao");

	} catch (RuntimeException ex) {
		Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Os Funcionários!");
		ex.printStackTrace();
	}
}

// ActionEvent serve pra capturar o que foi mandado pela view

public void excluir(ActionEvent evento) {
	try {
		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado"); // Diz qual foi o

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.delete(funcionario);
		Messages.addGlobalInfo("Funcionário Excluído Com Sucesso!"); // componente clicado

		funcionarios = funcionarioDAO.list();

	} catch (RuntimeException ex) {
		Messages.addGlobalError("Ocorreu Um Erro Ao Excluir O Funcionário!");
		ex.printStackTrace();
	}
}

public void editar(ActionEvent evento) {
	try {
		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoas = pessoaDAO.list();
	} catch (RuntimeException ex) {
		Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Os Funcionários!");
		ex.printStackTrace();
	}

}

public Funcionario getFuncionario() {
	return funcionario;
}

public void setFuncionario(Funcionario funcionario) {
	this.funcionario = funcionario;
}

public List<Funcionario> getFuncionarios() {
	return funcionarios;
}

public void setFuncionarios(List<Funcionario> funcionarios) {
	this.funcionarios = funcionarios;
}

public List<Pessoa> getPessoas() {
	return pessoas;
}

public void setPessoas(List<Pessoa> pessoas) {
	this.pessoas = pessoas;
}	

	
}
