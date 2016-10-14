package br.com.finance.financeiro_pessoal.service;

import java.util.List;

import br.com.finance.financeiro_pessoal.domain.gl.Cliente;

public interface ClienteService extends GenericService<Cliente>{
	
	Long totalClienteCadastrado();
	
	List<Cliente> procurarPeloNome(String nome);
	
}
