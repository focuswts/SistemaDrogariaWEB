package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.CidadeDAO;
import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.domain.Cidade;
import br.com.drogaria.domain.Estado;

@SuppressWarnings({ "deprecation", "serial" })
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public void salvar() {
		try {

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.list();

			cidade = new Cidade();
			cidades = cidadeDAO.list();

			Messages.addGlobalInfo("Cidade Salva Com Sucesso!");

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Salvar!");
			ex.printStackTrace();
		}

	}

	public void novo() {
		try {
			cidade = new Cidade();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.list("nome");
		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Os Estados!");
			ex.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.list();

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Ás Cidades!");
			ex.printStackTrace();
		}
	}

	// ActionEvent serve pra capturar o que foi mandado pela view

	public void excluir(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada"); // Diz qual foi o

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.delete(cidade);
			Messages.addGlobalInfo("Cidade Excluída Com Sucesso!"); // componente clicado

			cidades = cidadeDAO.list();

		} catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu Um Erro Ao Excluir O Estado!");
			ex.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.list();
		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Os Estados!");
			ex.printStackTrace();
		}
		
		
		
	}
	
	

}
