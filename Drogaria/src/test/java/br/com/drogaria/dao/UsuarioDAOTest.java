package br.com.drogaria.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Pessoa;
import br.com.drogaria.domain.Usuario;

public class UsuarioDAOTest {


	@Test
	@Ignore
	public void salvar() {

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.search(2L);
	
		System.out.println("Pessoa Encontrada!");
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
		
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenhaSemCriptografia("q1w2r3b4");

		SimpleHash hash = new SimpleHash("md5",usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());
		usuario.setTipo('A');

	UsuarioDAO usuarioDAO = new UsuarioDAO();
	usuarioDAO.save(usuario);
	System.out.println("Usuário Salvo Com Sucesso!");
		
	
	}
	
	@Test
	public void autenticar() {
		String cpf = "079.715.178.12";
		String senha = "q1w2r3b4";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);
		
		
		System.out.println("Usuário Autenticado: " + usuario);
	}
	
	

}
