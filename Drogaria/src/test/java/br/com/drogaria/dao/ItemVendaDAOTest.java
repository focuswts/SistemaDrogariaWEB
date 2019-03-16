package br.com.drogaria.dao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.drogaria.domain.ItemVenda;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;

public class ItemVendaDAOTest {

	@Test
	public void salvar() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.search(4L);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.search(2L);
		ItemVenda itemVenda = new ItemVenda();

		itemVenda.setVenda(venda);
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(new Short("4"));
		BigDecimal quantidade = new BigDecimal(itemVenda.getQuantidade());

		BigDecimal valorParcial = itemVenda.getProduto().getPreco().multiply(quantidade);
		itemVenda.setValorParcial(valorParcial);

		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		itemVendaDAO.save(itemVenda);
		System.out.println("Venda Efetuada Com Sucesso!");

		System.out.println("Funcionário: " + venda.getFuncionario().getPessoa().getNome());
		System.out.println("Cliente: " + venda.getCliente().getPessoa().getNome());
		System.out.println("Produto: " + itemVenda.getProduto().getDescricao());
		System.out.println("Quantidade: " + itemVenda.getQuantidade());
		System.out.println("Valor Unitário: " + itemVenda.getProduto().getPreco());
		System.out.println("Valor Parcial: " + valorParcial);
	}

}
