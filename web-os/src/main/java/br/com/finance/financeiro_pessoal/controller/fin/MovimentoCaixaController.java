package br.com.finance.financeiro_pessoal.controller.fin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.finance.financeiro_pessoal.domain.fin.MovimentoCaixa;
import br.com.finance.financeiro_pessoal.service.fin.ContaCaixaService;

@RestController
@RequestMapping("/movimento_caixa")
public class MovimentoCaixaController {
	
	private ModelAndView mv;
	
	@Autowired
	private ContaCaixaService contaCaixaService;
	
	private static final String PAGINA_PRINCIPAL = "/view/financeiro/pesquisaMovimentoCaixa";
	
	@RequestMapping
	public ModelAndView abrirPaginaMovimentoCaixa(MovimentoCaixa movimentoCaixa){
		mv = new ModelAndView(PAGINA_PRINCIPAL);
		mv.addObject("contasFinanceira", contaCaixaService.listarTodos());
		mv.addObject(new MovimentoCaixa());
		return mv;
	}
	
	@RequestMapping(value = "/pesquisar_movimento", method = RequestMethod.POST)
	public ModelAndView pesquisarMovimentoCaixa(@Valid MovimentoCaixa movimentoCaixa, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return abrirPaginaMovimentoCaixa(movimentoCaixa);
		}
		return mv;
	}

}
