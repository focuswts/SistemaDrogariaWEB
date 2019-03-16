package br.com.drogaria.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;

	public void salvar() {
		try {

			if (produto.getCaminho() == null) {
				Messages.addGlobalError("O Campo Foto É Obrigatório!");
			}

			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produtoRetorno = produtoDAO.merge(produto);

			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths
					.get("/home/focuswts/Desktop/JAVAWEB Drogaria/uploads/" + produtoRetorno.getCodigo() + ".png");

			System.out.println(new File("").getAbsolutePath());// Pega Caminho Que O Java Executa

			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.list();

			produto = new Produto();
			produtos = produtoDAO.list();

			Messages.addGlobalInfo("Produto Salvo Com Sucesso!");

		} catch (RuntimeException | IOException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Salvar!");
			ex.printStackTrace();
		}

	}

	public void novo() {
		try {
			produto = new Produto();

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.list();
		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Os Fabricantes!");
			ex.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.list();
		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Os Produtos!");
			ex.printStackTrace();
		}
	}

	// ActionEvent serve pra capturar o que foi mandado pela view

	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado"); // Diz qual foi o

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.delete(produto);

			Path arquivo = Paths.get("/home/focuswts/Desktop/JAVAWEB Drogaria/uploads/" + produto.getCodigo() + ".png");
			Files.deleteIfExists(arquivo);

			Messages.addGlobalInfo("Produto Excluído Com Sucesso!"); // componente clicado

			produtos = produtoDAO.list();

		} catch (RuntimeException | IOException ex) {
			Messages.addGlobalError("Ocorreu Um Erro Ao Excluir O Produto!");
			ex.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			produto.setCaminho("/home/focuswts/Desktop/JAVAWEB Drogaria/uploads/" + produto.getCodigo() + ".png");

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.list();
		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Os Fabricantes!");
			ex.printStackTrace();
		}

	}

	public void upload(FileUploadEvent evento) {

		try {
			UploadedFile arquivoUpload = evento.getFile();

			Path arquivoTemp = Files.createTempFile(null, null);

			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);

			produto.setCaminho(arquivoTemp.toString());

		} catch (IOException erro) {
			Messages.addGlobalInfo("Ocorreu Um Erro Ao Efetuar O Upload Do Arquivo!");
		}
	}

	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			// idDoformulario:idDoComponente
			Map<String, Object> filtros = tabela.getFilters();
			String produto = (String) filtros.get("produto.descricao");
			String fabricante = (String) filtros.get("fabricante.descricao");

			String caminho = Faces.getRealPath("/reports/produtos.jasper");
			// JasperCompileManager.compileReportToFile(caminho);

			Map<String, Object> parametros = new HashMap<>();

			Connection conexao = HibernateUtil.getConexao();
			// caminho = caminho.replaceAll("jrxml", "jasper");

			if (produto == null) {
				parametros.put("PRODUTO_DESCRICAO", "%%");
			} else {
				parametros.put("PRODUTO_DESCRICAO", "%" + produto + "%");
			}
			if (fabricante == null) {
				parametros.put("FABRICANTE_DESCRICAO", "%%");
			} else {
				parametros.put("FABRICANTE_DESCRICAO", "%" + fabricante + "%");
			}

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);

		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu Um Erro Ao Gerar O Relatório!");
			erro.printStackTrace();
		}

	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

}
