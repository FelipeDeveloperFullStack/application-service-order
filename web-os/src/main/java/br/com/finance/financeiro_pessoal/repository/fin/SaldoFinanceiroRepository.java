package br.com.finance.financeiro_pessoal.repository.fin;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.finance.financeiro_pessoal.domain.fin.SaldoFinanceiro;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoFinanceiro;

@Repository
public interface SaldoFinanceiroRepository extends JpaRepository<SaldoFinanceiro, Long>{
	
	SaldoFinanceiro findByDataMovimentoAndTipoFinanceiro(Date dataMovimento, TipoFinanceiro tipoFinanceiro);

	SaldoFinanceiro findByDataMovimento(Date dataMovimento);
	
}
