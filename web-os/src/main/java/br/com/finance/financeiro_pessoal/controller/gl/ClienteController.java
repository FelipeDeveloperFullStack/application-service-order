package br.com.finance.financeiro_pessoal.controller.gl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.finance.financeiro_pessoal.domain.gl.Cliente;
import br.com.finance.financeiro_pessoal.domain.gl.type.Situacao;
import br.com.finance.financeiro_pessoal.domain.gl.type.TipoPessoa;
import br.com.finance.financeiro_pessoal.service.gl.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	private ModelAndView mv;
	
	@Autowired
	private ClienteService clienteService;
	
	private final String CAMINHO_PAGINA_PESQUISA_CLIENTE = "/view/global/pesquisaCliente";
	private final String CAMINHO_PAGINA_VISUALIZAR_CLIENTE = "/view/global/visualizarCliente";
	private final String CAMINHO_PAGINA_CADASTRO_CLIENTE = "/view/global/cadastroCliente";
	private final String REDIRECT_CLIENTE = "redirect:/cliente";
	
	@RequestMapping
	public ModelAndView listarCliente(Cliente cliente){
		mv = new ModelAndView(CAMINHO_PAGINA_PESQUISA_CLIENTE);
		mv.addObject("clientes", clienteService.listarTodos());
		return mv;
	}
	
	@RequestMapping(value = "/visualizar_cliente/{id}", method = RequestMethod.GET)
	public ModelAndView visualzarCliente(@PathVariable Long id){
		mv = new ModelAndView(CAMINHO_PAGINA_VISUALIZAR_CLIENTE);
		mv.addObject("cliente", clienteService.procurarPeloID(id));
		mv.addObject("totalMovimentoRecebido", clienteService.calcularTotalMovimentosRecebidoCliente(clienteService.procurarPeloID(id)));
		return mv;
	}
	
	@RequestMapping(value = "/editar_cliente/{id}", method = RequestMethod.GET)
	public ModelAndView editarCliente(@PathVariable Long id){
		Cliente cliente = clienteService.procurarPeloID(id);
		mv = new ModelAndView(CAMINHO_PAGINA_CADASTRO_CLIENTE);
		mv.addObject(cliente);
		mv.addObject("tipoPessoas", TipoPessoa.values());
		mv.addObject("situacao", Situacao.values());
		return mv;
	}
	
	@RequestMapping(value = "/cadastrar_cliente")
	public ModelAndView novoCliente(Cliente cliente){
		mv = new ModelAndView(CAMINHO_PAGINA_CADASTRO_CLIENTE);
		mv.addObject("tipoPessoas", TipoPessoa.values());
		mv.addObject("situacao", Situacao.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult bindingResult, RedirectAttributes attributes){
		if(bindingResult.hasErrors()){
			return novoCliente(cliente);
		}
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		salvar(cliente);
		mv = new ModelAndView(REDIRECT_CLIENTE);
		return mv;
	}
	
	private void salvar(Cliente cliente){
		clienteService.salvar(cliente);
	}
	
	/*@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisaCliente(Cliente cliente){
		
		mv = new ModelAndView(REDIRECT_CLIENTE);
		mv.addObject("clientes", clienteService.procurarPeloNome(cliente.getNome()));
		return mv;
	}*/
	
	

}
