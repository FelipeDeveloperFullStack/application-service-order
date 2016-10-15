package br.com.finance.financeiro_pessoal.controller.fin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.domain.gl.Situacao;
import br.com.finance.financeiro_pessoal.service.fin.ContaCaixaService;

@RestController
@RequestMapping("/conta_caixa")
public class ContaCaixaController {
	
	private ModelAndView mv;
	
	@Autowired
	private ContaCaixaService contaCaixaService;
	
	private static final String CAMINHO_LISTAGEM_CONTA_CAIXA = "/view/financeiro/pesquisaContaCaixa";
	private static final String CAMINHO_CADASTRO_CONTA_CAIXA = "/view/financeiro/cadastroContaCaixa";
	private static final String REDIRECT_PRINCIPAL = "redirect:/conta_caixa";
	//private static final String REDIRECT_PAGINA_CADASTRO = "redirect:/conta_caixa/cadastro_conta_caixa";
	
	@RequestMapping
	public ModelAndView listagemContaCaixa(ContaCaixa contaCaixa){
		mv = new ModelAndView(CAMINHO_LISTAGEM_CONTA_CAIXA);
		mv.addObject("contaCaixas", contaCaixaService.listarTodos());
		return mv;
	}
	
	@RequestMapping(value = "/cadastro_conta_caixa", method = RequestMethod.GET)
	public ModelAndView cadastroContaCaixa(ContaCaixa contaCaixa){
		mv = new ModelAndView(CAMINHO_CADASTRO_CONTA_CAIXA);
		mv.addObject("situacao", getSituacao());
		return mv;
	}
	
	private Situacao[] getSituacao(){
		return Situacao.values();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvarContaCaixa(@Valid ContaCaixa contaCaixa, BindingResult bindingResult, RedirectAttributes attributes){
		if(bindingResult.hasErrors()){
			return cadastroContaCaixa(contaCaixa);
		}
		attributes.addFlashAttribute("mensagem", "Conta caixa salvo com sucesso!");
		contaCaixaService.salvar(contaCaixa);
		return new ModelAndView(REDIRECT_PRINCIPAL);
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editarContaCaixa(@PathVariable Long id){
		ContaCaixa contaCaixa = contaCaixaService.procurarPeloID(id);
		mv = new ModelAndView(CAMINHO_CADASTRO_CONTA_CAIXA);
		mv.addObject(contaCaixa);
		mv.addObject("situacao", getSituacao());
		return mv;
	}
	

}
