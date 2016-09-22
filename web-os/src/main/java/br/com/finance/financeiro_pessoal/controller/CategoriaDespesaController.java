package br.com.finance.financeiro_pessoal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.finance.financeiro_pessoal.domain.CategoriaDespesa;
import br.com.finance.financeiro_pessoal.service.CategoriaDespesaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaDespesaController {

	@Autowired
	private CategoriaDespesaService categoriaDespesaService;
	private ModelAndView mv;
	private static final String CAMINHO_PAGINA = "/view/configuracao/despesas/categoriaDespesa";
	private static final String REDIRECT_PAGE = "redirect:/categoria";
	
	@RequestMapping
	public ModelAndView categoriaDespesa(CategoriaDespesa categoriaDespesa) {
		mv = new ModelAndView(CAMINHO_PAGINA);
		mv.addObject("categoriasDespesas", categoriaDespesaService.listarTodos());
		return mv;
	}
	
	/*@RequestMapping(value = "/procurar", method = RequestMethod.GET)
	public ModelAndView procurarCategoria(String table_search){
		mv = new ModelAndView(CAMINHO_PAGINA);
		mv.addObject("categoriasDespesas", categoriaDespesaService.findByCategoriaDespesa(table_search));
		return mv;
	}*/

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Valid CategoriaDespesa categoriaDespesa, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			return categoriaDespesa(categoriaDespesa);
		}
		attributes.addFlashAttribute("mensagem", "Categoria cadastrada com sucesso!");
		categoriaDespesaService.salvar(categoriaDespesa);
		return new ModelAndView(REDIRECT_PAGE);
	}
	
	@RequestMapping(value = "{codigo}", method = RequestMethod.POST)
	public String editar(@PathVariable("codigo") Long codigo, CategoriaDespesa categoriaDespesa){
		categoriaDespesa.setId(codigo);
		categoriaDespesaService.salvar(categoriaDespesa);
		return REDIRECT_PAGE;
	}
	
	@RequestMapping(value = "{codigo}",method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes){
		CategoriaDespesa categoria = categoriaDespesaService.procurarPeloID(codigo);
		attributes.addFlashAttribute("mensagem", "A categoria '"+categoria.getCategoria() + "' removido com sucesso!");
		categoriaDespesaService.excluir(codigo);
		return REDIRECT_PAGE;
	}

}
