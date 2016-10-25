package br.com.finance.financeiro_pessoal.service.gl;

import java.math.BigDecimal;
import java.util.List;

import br.com.finance.financeiro_pessoal.domain.gl.Cliente;
import br.com.finance.financeiro_pessoal.domain.gl.type.Situacao;
import br.com.finance.financeiro_pessoal.service.GenericService;

public interface ClienteService extends GenericService<Cliente>{
	
	Long totalClienteCadastrado();
	
	List<Cliente> procurarPeloNome(String nome);
	
	List<Cliente> findByClientesAtivos(Situacao situacao);
	
	BigDecimal calcularTotalMovimentosRecebidoCliente(Cliente cliente);
}
