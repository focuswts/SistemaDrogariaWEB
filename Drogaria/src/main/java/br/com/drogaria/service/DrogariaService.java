package br.com.drogaria.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//http://localhost:8080/drogaria/rest/[Nome]
@Path("drogaria")
public class DrogariaService {

	@GET
	public String exibir() {
		
		
		return "Curso De Java";
	}
	
	
	
	
}
