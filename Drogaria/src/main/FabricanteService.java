package br.com.drogaria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

//http://localhost:8080/drogaria/rest/[Nome]
@Path("fabricante")
public class FabricanteService {

	@GET
	public String listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> fabricantes = fabricanteDAO.list();

		Gson gson = new Gson();
		String json = gson.toJson(fabricantes);

		return json;
	}

	// http://localhost:8080/drogaria/rest/fabricante/{codigo}
	// http://localhost:8080/drogaria/rest/fabricante/10
	// @PathParam("{codigo}" amarra o valor do path a variavel
	@GET
	@Path("{codigo}")
	public String buscar(@PathParam("codigo") Long codigo) {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.search(codigo);

		Gson gson = new Gson();
		String json = gson.toJson(fabricante);
		return json;
	}

	// http://localhost:8080/drogaria/rest/fabricante
	@POST
	public String salvar(String json) {
		Gson gson = new Gson();

		Fabricante fabricante = gson.fromJson(json, Fabricante.class); // Converte o Json Para Um Objeto DO Tipo
																		// Fabricante

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.save(fabricante);

		String jsonSaida = gson.toJson(fabricante);
		return jsonSaida;
	}

	// http://localhost:8080/drogaria/rest/fabricante
	@PUT
	public String editar(String json) {
		Gson gson = new Gson();

		Fabricante fabricante = gson.fromJson(json, Fabricante.class); // Converte o Json Para Um Objeto DO Tipo
																		// Fabricante

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.update(fabricante);

		String jsonSaida = gson.toJson(fabricante);
		return jsonSaida;
	}

	// http://localhost:8080/drogaria/rest/fabricante
	@DELETE
	public String excluir(String json) {

		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.search(fabricante.getCodigo());
		fabricanteDAO.delete(fabricante);

		String saidaJson = gson.toJson(fabricante);
		return saidaJson;

	}

}
