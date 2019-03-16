package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.dao.UsuarioDAO;
import br.com.drogaria.domain.Pessoa;
import br.com.drogaria.domain.Usuario;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;

	public void salvar() {
		try {

			UsuarioDAO UsuarioDAO = new UsuarioDAO();
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
			usuario.setSenha(hash.toHex());

			UsuarioDAO.merge(usuario);

			usuarios = UsuarioDAO.list("tipo");

			novo(); // Efetua Um Refresh Em Tudo

			Messages.addGlobalInfo("Usuario Salvo Com Sucesso!");

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Salvar!");
			ex.printStackTrace();
		}

	}

	public void novo() {
		try {
			usuario = new Usuario(); // Limpa Usuario

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.list("nome"); // Recarrega Pessoa

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar As Pessoas!");
			ex.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.list();

		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Os Usuarios!");
			ex.printStackTrace();
		}
	}

	// ActionEvent serve pra capturar o que foi mandado pela view

	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado"); // Diz qual foi o

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.delete(usuario);
			Messages.addGlobalInfo("Usuario Excluído Com Sucesso!"); // componente clicado

			usuarios = usuarioDAO.list();

		} catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu Um Erro Ao Excluir O Usuario!");
			ex.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.list();
		} catch (RuntimeException ex) {
			Messages.addGlobalWarn("Ocorreu Um Erro Ao Listar Os Usuarios!");
			ex.printStackTrace();
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
