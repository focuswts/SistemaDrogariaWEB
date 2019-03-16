package br.com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Cidade;
import br.com.drogaria.domain.Estado;

public class CidadeDAOTest {

	@Test
	public void salvar() {

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.search(3l);

		if (estado == null) {
			System.out.println("Nenhum EstLondrinado Encontrado!");
		} else {

			Cidade cidade = new Cidade();
			cidade.setNome("Londrina");
			cidade.setEstado(estado);

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.save(cidade);
		}
	}

	@Ignore
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.list();

		if (cidades.size() != 0) {
			System.out.println("Cidades Encontradas: " + cidades.size());
			for (Cidade cidade : cidades) {
				System.out.println("Código: " + cidade.getCodigo());
				System.out.println("Nome: " + cidade.getNome());
				System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
				System.out.println("Nome Estado: " + cidade.getEstado().getNome());
				System.out.println("--------------------------------");
			}
		} else {
			System.out.println("Nenhuma Cidade Encontrada!");
		}

	}

	@Ignore
	public void buscar() {
		Long codigo = 1L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.search(codigo);

		if (cidade != null) {
			System.out.println("Código: " + cidade.getCodigo());
			System.out.println("Nome: " + cidade.getNome());
			System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
			System.out.println("Nome Estado: " + cidade.getEstado().getNome());
			System.out.println("--------------------------------");
		} else {
			System.out.println("Nenhuma Cidade Encontrada!");
		}

	}

	@Ignore
	public void excluir() {
		Long codigo = 5L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.search(codigo);

		if (cidade == null) {
			System.out.println("Nenhuma Cidade Encontrada");
		} else {
			cidadeDAO.delete(cidade);

			System.out.println("Registro Excluído: " + cidade.getNome());
		}
	}

	@Ignore
	public void editar() {
		Long codigoCidade = 10L;
		Long codigoEstado = 1L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.search(codigoCidade);

		if (cidade == null) {
			System.out.println("Nenhuma Cidade Encontrada");
		} else {

			System.out.println("----Cidade A ser Editada--------");
			System.out.println("Código: " + cidade.getCodigo());
			System.out.println("Nome: " + cidade.getNome());
			System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
			System.out.println("Nome Estado: " + cidade.getEstado().getNome());
			System.out.println("--------------------------------");
			
			EstadoDAO estadoDAO = new EstadoDAO();
			Estado estado = estadoDAO.search(codigoEstado);
			cidade.setEstado(estado);
cidade.setNome("São Paulo");
			cidadeDAO.update(cidade);
			System.out.println("--------Cidade Editada--------");
			System.out.println("Código: " + cidade.getCodigo());
			System.out.println("Nome: " + cidade.getNome());
			System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
			System.out.println("Nome Estado: " + cidade.getEstado().getNome());
			System.out.println("--------------------------------");

		}

	}

	@Ignore
	public void buscarPorEstado() {
		Long estadoCodigo = 1L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.buscarPorEstado(estadoCodigo);

		if (cidades.size() != 0) {
			System.out.println("Cidades Encontradas: " + cidades.size());
			for (Cidade cidade : cidades) {
				System.out.println("Código: " + cidade.getCodigo());
				System.out.println("Nome: " + cidade.getNome());
				System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
				System.out.println("Nome Estado: " + cidade.getEstado().getNome());
				System.out.println("--------------------------------");
			}
		} else {
			System.out.println("Nenhuma Cidade Encontrada!");
		}

	}
	
	
	
	
	
	
}
