package br.com.finance.financeiro_pessoal.repository.fin;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.MovimentoCaixa;

@Repository
public interface MovimentoCaixaRepository extends JpaRepository<MovimentoCaixa, Long>{
	
	List<MovimentoCaixa> findByDataMovimentoEquals(Date dataMovimento);
	
	MovimentoCaixa findByContaCaixaEndingWith(ContaCaixa contaCaixa);
	
	List<MovimentoCaixa> findByDataMovimentoAndContaCaixa(Date dataMovimento, ContaCaixa contaCaixa);
	
	List<MovimentoCaixa> findByContaCaixa(ContaCaixa contaCaixa);

}
