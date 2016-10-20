package br.com.finance.financeiro_pessoal.service.fin;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.MovimentoCaixa;
import br.com.finance.financeiro_pessoal.service.GenericService;

public interface MovimentoCaixaService extends GenericService<MovimentoCaixa>{
	
	List<MovimentoCaixa> findByDataMovimentoEquals(Date dataMovimento);
	
	Page<MovimentoCaixa> findAll(Pageable pageable);
	
	MovimentoCaixa findByContaCaixaEndingWith(ContaCaixa contaCaixa);
	
	List<MovimentoCaixa> findByDataMovimentoAndContaCaixa(Date dataMovimento, ContaCaixa contaCaixa);
	
	void excluirMovimentoCaixa(Long id);

}
