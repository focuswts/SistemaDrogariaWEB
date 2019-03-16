package br.com.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import br.com.drogaria.domain.Cliente;
import br.com.drogaria.domain.Pessoa;

public class ClienteDAOTest {

	@Test
	public void salvar() throws ParseException{
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.search(1L);
		
		
		Cliente cliente = new Cliente();
		cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2018"));
		cliente.setLiberado(true);
		cliente.setPessoa(pessoa);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.save(cliente);
		System.out.println("Cliente Salvo Com Sucesso!");
		
	}
	
	
	
	
}
