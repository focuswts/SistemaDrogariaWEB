package br.com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {

	@Ignore
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		FabricanteDAO fabricanteDAO = new FabricanteDAO();

		fabricante.setDescricao("Bio Fórmula");

		fabricanteDAO.save(fabricante);

	}

	@Ignore
	public void listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> resultSet = fabricanteDAO.list();

		System.out.println("Total De Fabricantes Encontradas: " + resultSet.size());

		for (Fabricante fabricante : resultSet) {
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}

	}

	@Ignore
	public void buscar() {
		Long codigo = 2l;

		FabricanteDAO fabricanteDAO = new FabricanteDAO();

		Fabricante fabricante = fabricanteDAO.search(codigo);

		if (fabricante == null) {
			System.out.println("Nenhuma Fabricante Encontrada!");
		} else {

			System.out.println("Código Fabricante: " + fabricante.getCodigo() + " Nome: " + fabricante.getDescricao());
		}
	}

	@Ignore
	public void excluir() {
		Long codigo = 3l;

		FabricanteDAO fabricanteDAO = new FabricanteDAO();

		Fabricante fabricante = fabricanteDAO.search(codigo);

		if (fabricante == null) {
			System.out.println("Nenhuma Fabricante Encontrada!");
		} else {
			fabricanteDAO.delete(fabricante);
			System.out.println("Registro Removido: ");
			System.out.println("Código Fabricante: " + fabricante.getCodigo() + " Nome: " + fabricante.getDescricao());
		}
	}

	@Ignore
	public void edit() {
		Long codigo = 4l;

		FabricanteDAO fabricanteDAO = new FabricanteDAO();

		Fabricante fabricante = fabricanteDAO.search(codigo);

		if (fabricante == null) {
			System.out.println("Nenhuma Fabricante Encontrada!");
		} else {
			System.out.println("Registro Antigo: ");
			System.out.println("Código Fabricante: " + fabricante.getCodigo() + " Nome: " + fabricante.getDescricao());

			fabricante.setDescricao("Meldan");
			fabricanteDAO.update(fabricante);
			System.out.println("Registro Atualizado: ");
			System.out.println("Código Fabricante: " + fabricante.getCodigo() + " Nome: " + fabricante.getDescricao());

		}
	}

	@Test
	public void merge() {
		// Fabricante fabricante = new Fabricante();
		// fabricante.setDescricao("Bio Fórmula A");
		// FabricanteDAO fabricanteDAO = new FabricanteDAO();
		// fabricanteDAO.merge(fabricante);

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.search(8L);
		fabricante.setDescricao("Ultra Labs");
		fabricanteDAO.merge(fabricante);
	}

}
