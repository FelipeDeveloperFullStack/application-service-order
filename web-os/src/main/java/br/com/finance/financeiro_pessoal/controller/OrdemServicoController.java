package br.com.finance.financeiro_pessoal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/os")
public class OrdemServicoController {
	
	private ModelAndView mv;
	
	private final String CAMINHO_PAGINA_VISUALIZA_OS = "/view/gestao_servico/visualizaOrdemServico";
	
	@RequestMapping(value = "/dados_os")
	public ModelAndView visualizarOrdemServico(){
		mv = new ModelAndView(CAMINHO_PAGINA_VISUALIZA_OS);
		return mv;
	}

}
