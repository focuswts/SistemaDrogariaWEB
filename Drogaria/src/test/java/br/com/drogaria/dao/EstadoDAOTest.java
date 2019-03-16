package br.com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Estado;

public class EstadoDAOTest {

	@Test
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Paraná");
		estado.setSigla("PR");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.save(estado);

	}

	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultSet = estadoDAO.list();

		System.out.println("Total De Estados Encontrados: " + resultSet.size());

		for (Estado estado : resultSet) {
			System.out.println(estado.getSigla() + " - " + estado.getNome());
		}

	}

	@Ignore
	public void buscar() {
		Long codigo = 2l;

		EstadoDAO estadoDAO = new EstadoDAO();

		Estado estado = estadoDAO.search(codigo);

		if (estado == null) {
			System.out.println("Nenhum Estado Encontrado!");
		} else {

			System.out.println("Código Estado: " + estado.getCodigo() + " Nome: " + estado.getNome() + " Sigla: "
					+ estado.getSigla());
		}
	}

	@Ignore
	public void excluir() {
		Long codigo = 2l;

		EstadoDAO estadoDAO = new EstadoDAO();

		Estado estado = estadoDAO.search(codigo);

		if (estado == null) {
			System.out.println("Registro Inexistente");
		} else {
			estadoDAO.delete(estado);
			System.out.println("Registro Removido: ");
			System.out.println("Código Estado: " + estado.getCodigo() + " Nome: " + estado.getNome() + " Sigla: "
					+ estado.getSigla());

		}
	}

	@Ignore
	public void edit() {
		Long codigo = 1l;

		EstadoDAO estadoDAO = new EstadoDAO();

		Estado estado = estadoDAO.search(codigo);

		if (estado == null) {
			System.out.println("Registro Inexistente");
		} else {

			System.out.println("Registro Antigo: ");
			System.out.println("Código Estado: " + estado.getCodigo() + " Nome: " + estado.getNome() + " Sigla: "
					+ estado.getSigla());

			estado.setSigla("SP");
			estadoDAO.update(estado);
			System.out.println("Registro Atualizado: ");
			System.out.println("Código Estado: " + estado.getCodigo() + " Nome: " + estado.getNome() + " Sigla: "
					+ estado.getSigla());

		}
	}

}
