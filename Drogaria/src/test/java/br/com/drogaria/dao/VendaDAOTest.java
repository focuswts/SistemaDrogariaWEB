package br.com.drogaria.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import br.com.drogaria.domain.Cliente;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Venda;

public class VendaDAOTest {

	@Test
	public void salvar() {

		Date horario = new Date();
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.search(1L);
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.search(2L);
		Venda venda = new Venda();
		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);
		venda.setHorario(horario);
		venda.setValorTotal(new BigDecimal("13.80"));

		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.save(venda);

		System.out.println("Venda Efetuada Com Sucesso!");
	}

}
