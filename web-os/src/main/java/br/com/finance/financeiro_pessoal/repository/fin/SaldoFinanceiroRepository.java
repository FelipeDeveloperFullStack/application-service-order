package br.com.finance.financeiro_pessoal.repository.fin;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.SaldoFinanceiro;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoFinanceiro;

@Repository
public interface SaldoFinanceiroRepository extends JpaRepository<SaldoFinanceiro, Long>{
	
	SaldoFinanceiro findByDataMovimentoAndTipoFinanceiroAndContaCaixa(Date dataMovimento, TipoFinanceiro tipoFinanceiro, ContaCaixa contaCaixa);

	SaldoFinanceiro findByDataMovimentoAndContaCaixaAndTipoFinanceiro(Date dataMovimento, ContaCaixa contaCaixa, TipoFinanceiro tipoFinanceiro);
	
	List<SaldoFinanceiro> findByContaCaixa(ContaCaixa contaCaixa);
	
	List<SaldoFinanceiro> findByDataMovimentoBefore(Date dataMovimento);
}
