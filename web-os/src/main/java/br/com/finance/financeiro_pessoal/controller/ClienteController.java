package br.com.finance.financeiro_pessoal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	private ModelAndView mv;
	
	private final String CAMINHO_PAGINA_PESQUISA_CLIENTE = "/view/global/pesquisaCliente";
	private final String CAMINHO_PAGINA_VISUALIZAR_CLIENTE = "/view/global/visualizarCliente";
	private final String CAMINHO_PAGINA_CADASTRO_CLIENTE = "/view/global/cadastroCliente";
	
	@RequestMapping
	public ModelAndView listarCliente(){
		mv = new ModelAndView(CAMINHO_PAGINA_PESQUISA_CLIENTE);
		return mv;
	}
	
	@RequestMapping(value = "/visualizar_cliente")
	public ModelAndView visualzarCliente(){
		mv = new ModelAndView(CAMINHO_PAGINA_VISUALIZAR_CLIENTE);
		return mv;
	}
	
	@RequestMapping(value = "/cadastrar_cliente")
	public ModelAndView cadastrarCliente(){
		mv = new ModelAndView(CAMINHO_PAGINA_CADASTRO_CLIENTE);
		return mv;
	}

}
