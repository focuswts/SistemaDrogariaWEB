package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.HistoricoDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Historico;
import br.com.drogaria.domain.Produto;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class HistoricoBean implements Serializable {
	private Historico historico;
	private Produto produto;
	private Boolean exibePainelDados;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Boolean getExibePainelDados() {
		return exibePainelDados;
	}

	public void setExibePainelDados(Boolean exibePainelDados) {
		this.exibePainelDados = exibePainelDados;
	}

	@PostConstruct
	public void novo() {
		historico = new Historico();
		produto = new Produto();
		exibePainelDados = false;
	}

	public void buscar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto resultado = produtoDAO.search(produto.getCodigo());

			if (resultado == null) {
				exibePainelDados = false;
				Messages.addGlobalWarn("Não existe produto cadastrado para o código informado");
			} else {
				exibePainelDados = true;
				produto = resultado;
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar o produto");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {

			historico.setHorario(new Date());
			historico.setProduto(produto);

			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicoDAO.save(historico);

			Messages.addGlobalInfo("Histórico Salvo Com Sucesso!");

		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu Um Erro Ao tentar Salvar O Histórico");
			e.printStackTrace();
		}

	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

}
