package br.com.drogaria.dao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {

	@Test
	public void salvar() {
		Produto produto = new Produto();
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.search(4L);

		produto.setDescricao("Buscopan 50MG C/10 Comprimidos");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("13.70"));
		produto.setQuantidade(new Short("7"));

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.save(produto);
		System.out.println("Produto Salvo Com Sucesso!");
		
	}

}
