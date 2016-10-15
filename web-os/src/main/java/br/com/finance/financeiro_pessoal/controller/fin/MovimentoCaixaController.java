package br.com.finance.financeiro_pessoal.controller.fin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.service.fin.ContaCaixaService;

@RestController
@RequestMapping(value = "/movimento_caixa")
public class MovimentoCaixaController {
	
	private ModelAndView mv;
	
	@Autowired
	private ContaCaixaService contaCaixaService;
	
	private static final String PAGINA_PRINCIPAL = "/view/financeiro/pesquisaMovimentoCaixa";
	
	@RequestMapping
	public ModelAndView abrirPaginaMovimentoCaixa(ContaCaixa contaCaixa){
		mv = new ModelAndView(PAGINA_PRINCIPAL);
		mv.addObject("contasFinanceira", contaCaixaService.listarTodos());
		return mv;
	}

}
