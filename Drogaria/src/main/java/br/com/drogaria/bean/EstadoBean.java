package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.domain.Estado;

@SuppressWarnings({ "deprecation", "serial" })
@ManagedBean
@ViewScoped // Indica que o objeto ficará vivo enquanto estiver na tela que o solicitou
public class EstadoBean implements Serializable {

	private Estado estado;
	private List<Estado> estados;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void novo() {
		estado = new Estado();
	}

	@PostConstruct
	public void listar() {
		try {

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.list();

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Occoreu Um Erro Ao Listar Os Estados!");
			ex.printStackTrace();
		}

	}

	public void salvar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);
			novo();

			estados = estadoDAO.list();

			Messages.addGlobalInfo("Estado Salvo Com Sucesso!");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu Um Erro Ao Salvar O Estado!");
			ex.printStackTrace();
		}
	}

	// ActionEvent serve pra capturar o que foi mandado pela view

	public void excluir(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado"); // Diz qual foi o

			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.delete(estado);
			Messages.addGlobalInfo("Estado Excluído Com Sucesso!"); // componente clicado

			estados = estadoDAO.list();

		} catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu Um Erro Ao Excluir O Estado!");
			ex.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado"); // Diz qual foi o
	}

}
