package br.com.drogaria.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.drogaria.dao.UsuarioDAO;
import br.com.drogaria.domain.Pessoa;
import br.com.drogaria.domain.Usuario;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class AutenticacaoBean {

	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}

	public void autenticar() {
		try {

			System.out.println(usuario.getPessoa().getCpf());
			System.out.println(usuario.getSenha());

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());

			if (usuarioLogado == null) {
				Messages.addGlobalError("CPF E/OU Senha Incorretos!");
				return;
			}

			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException erro) {
			Messages.addGlobalError(erro.getMessage());
			erro.printStackTrace();
		}

	}

	public void logout() {
		usuarioLogado = null;
		try {
			if (usuarioLogado == null) {
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				Messages.addGlobalInfo("Logout Efetuado Com Sucesso!");

				Faces.redirect("./pages/autenticacao.xhtml");
			} else {
				Messages.addGlobalError("Ocorreu Um Erro Ao Efetuar O Logout!");
			}

		} catch (IOException ex) {
			Messages.addGlobalError(ex.getMessage());
			ex.printStackTrace();
		}
	}

}
