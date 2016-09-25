package br.com.finance.financeiro_pessoal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ApplicationController {
	
	@RequestMapping
	public String abrirLogin(){
		return "/view/login";
	}
	
	@RequestMapping(value = "/pagina_inicial")
	public String abrirPagina_inicial(){
		return "/dashboard/pagina_inicial";
	}
	
	@RequestMapping(value = "/bloqueioPagina")
	public String bloquearPagina(){
		return "/view/bloqueioPagina";
	}
	
}
