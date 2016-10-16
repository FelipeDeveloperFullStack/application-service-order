package br.com.finance.financeiro_pessoal.service.fin;

import java.util.List;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.domain.gl.type.Situacao;
import br.com.finance.financeiro_pessoal.service.GenericService;

public interface ContaCaixaService extends GenericService<ContaCaixa>{
	
	List<ContaCaixa> findByContaCaixaAtivo(Situacao situacao);

}
