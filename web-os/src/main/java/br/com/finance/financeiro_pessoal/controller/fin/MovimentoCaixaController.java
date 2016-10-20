package br.com.finance.financeiro_pessoal.controller.fin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.finance.financeiro_pessoal.domain.fin.MovimentoCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoFinanceiro;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoOrigemMovimento;
import br.com.finance.financeiro_pessoal.domain.gl.type.Situacao;
import br.com.finance.financeiro_pessoal.service.fin.ContaCaixaService;
import br.com.finance.financeiro_pessoal.service.fin.MovimentoCaixaService;
import br.com.finance.financeiro_pessoal.service.fin.SaldoFinanceiroService;
import br.com.finance.financeiro_pessoal.service.gl.ClienteService;

@RestController
@RequestMapping("/movimento_caixa")
public class MovimentoCaixaController {
	
	private ModelAndView mv;
	
	@Autowired
	private MovimentoCaixaService movimentoCaixaService;
	
	@Autowired
	private ContaCaixaService contaCaixaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private SaldoFinanceiroService saldoFinanceiroService;
	
	//private static final String REDIRECT_PAGINA_PRINCIPAL = "redirect:/movimento_caixa";
	private static final String PAGINA_PRINCIPAL = "/view/financeiro/pesquisaMovimentoCaixa";
	private static final String PAGINA_CADASTRO_MOVIMENTO_CAIXA = "/view/financeiro/cadastroMovimentoCaixa";
	
	@RequestMapping
	public ModelAndView abrirPaginaMovimentoCaixa(MovimentoCaixa movimentoCaixa){
		mv = new ModelAndView(PAGINA_PRINCIPAL);
		mv.addObject("contasFinanceira", contaCaixaService.findByContaCaixaAtivo(Situacao.ATIVO));
		
		mv.addObject("resumoCaixa", saldoFinanceiroService.findByDataMovimentoAndTipoFinanceiroAndContaCaixa(movimentoCaixa.getDataMovimento()
					,TipoFinanceiro.CAIXA, movimentoCaixa.getContaCaixa()));
		mv.addObject("movimentoDeCaixa", movimentoCaixaService.findByDataMovimentoAndContaCaixa(movimentoCaixa.getDataMovimento(), movimentoCaixa.getContaCaixa()));
		return mv;
	}
	
	@RequestMapping(value = "/cadastro_movimento_caixa", method = RequestMethod.GET)
	public ModelAndView abrirCadastroMovimentoCaixa(MovimentoCaixa movimentoCaixa){
		mv = new ModelAndView(PAGINA_CADASTRO_MOVIMENTO_CAIXA);
		mv.addObject("contasFinanceira", contaCaixaService.findByContaCaixaAtivo(Situacao.ATIVO));
		mv.addObject("parceiros", clienteService.findByClientesAtivos(Situacao.ATIVO));
		return mv;
	}
	
	@RequestMapping(value = "/salvar_movimento_caixa", method = RequestMethod.POST)
	public ModelAndView salvarMovimentoCaixa(@Valid MovimentoCaixa movimentoCaixa, BindingResult bindingResult, RedirectAttributes attributes){
		movimentoCaixa.setTipoOrigemMovimento(TipoOrigemMovimento.LANCAMENTO);
		if(bindingResult.hasErrors()){
			return abrirCadastroMovimentoCaixa(movimentoCaixa);
		}
		attributes.addFlashAttribute("mensagem", "Movimento de caixa salvo com sucesso!");
		saldoFinanceiroService.calcularSaldoFinanceiro(movimentoCaixa);
		movimentoCaixaService.salvar(movimentoCaixa);
		return abrirPaginaMovimentoCaixa(movimentoCaixa);
	}
	
	@RequestMapping(value = "/pesquisar_movimento", method = RequestMethod.GET)
	public ModelAndView pesquisarMovimentoCaixa(MovimentoCaixa movimentoCaixa){
		mv = new ModelAndView(PAGINA_PRINCIPAL);
		mv.addObject("movimentoDeCaixa", movimentoCaixaService.findByDataMovimentoAndContaCaixa(movimentoCaixa.getDataMovimento(), movimentoCaixa.getContaCaixa()));
		mv.addObject("resumoCaixa", saldoFinanceiroService.findByDataMovimentoAndTipoFinanceiroAndContaCaixa(movimentoCaixa.getDataMovimento(), TipoFinanceiro.CAIXA, movimentoCaixa.getContaCaixa()));
		mv.addObject("contasFinanceira", contaCaixaService.findByContaCaixaAtivo(Situacao.ATIVO));
		return mv;
	}

}
