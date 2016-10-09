package br.com.finance.financeiro_pessoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.finance.financeiro_pessoal.service.ClienteService;

@Controller
@RequestMapping
public class ApplicationController {
	
	private ModelAndView mv;
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping
	public String abrirLogin(){
		return "/view/login";
	}
	
	@RequestMapping(value = "/pagina_inicial")
	public ModelAndView abrirPagina_inicial(){
		mv = new ModelAndView("/dashboard/pagina_inicial");
		mv.addObject("totalCliente", clienteService.totalClienteCadastrado());
		return mv;
	}
	
	@RequestMapping(value = "/bloqueioPagina")
	public String bloquearPagina(){
		return "/view/bloqueioPagina";
	}
	
}
