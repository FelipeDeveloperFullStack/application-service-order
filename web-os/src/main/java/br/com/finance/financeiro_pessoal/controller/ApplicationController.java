package br.com.finance.financeiro_pessoal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ApplicationController {
	
	@RequestMapping
	public String cadastro(){
		return "/dashboard/index";
	}
	
	@RequestMapping(value = "/bloqueioPagina")
	public String bloquearPagina(){
		return "/view/bloqueioPagina";
	}
	
}
