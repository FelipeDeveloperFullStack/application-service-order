package br.com.finance.financeiro_pessoal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/conta_caixa")
public class ContaCaixaController {
	
	private ModelAndView mv;
	
	private static final String CAMINHO_LISTAGEM_CONTA_CAIXA = "/view/financeiro/pesquisaContaCaixa";
	private static final String CAMINHO_CADASTRO_CONTA_CAIXA = "/view/financeiro/cadastroContaCaixa";
	
	@RequestMapping
	public ModelAndView listagemContaCaixa(){
		mv = new ModelAndView(CAMINHO_LISTAGEM_CONTA_CAIXA);
		return mv;
	}
	
	@RequestMapping(value = "/cadastro_conta_caixa", method = RequestMethod.GET)
	public ModelAndView cadastroContaCaixa(){
		mv = new ModelAndView(CAMINHO_CADASTRO_CONTA_CAIXA);
		return mv;
	}

}
