package br.com.finance.financeiro_pessoal.controller.fin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/movimento_caixa")
public class MovimentoCaixa {
	
	private ModelAndView mv;
	
	private static final String PAGINA_PRINCIPAL = "/view/financeiro/pesquisaMovimentoCaixa";
	
	@RequestMapping
	public ModelAndView abrirPaginaMovimentoCaixa(){
		mv = new ModelAndView(PAGINA_PRINCIPAL);
		return mv;
	}

}
