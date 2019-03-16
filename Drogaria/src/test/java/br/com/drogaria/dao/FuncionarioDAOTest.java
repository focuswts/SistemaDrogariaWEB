package br.com.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Pessoa;

public class FuncionarioDAOTest {

	@Test
	public void salvar() throws ParseException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.search(1L);

		Funcionario funcionario = new Funcionario();
		funcionario.setCarteiraTrabalho("075.35137.42-4");
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2018"));
		funcionario.setPessoa(pessoa);

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.save(funcionario);

		System.out.println("Funcionário Salvo Com Sucesso!");

	}

}
