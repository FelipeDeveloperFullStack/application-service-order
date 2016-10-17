package br.com.finance.financeiro_pessoal.service.fin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import br.com.finance.financeiro_pessoal.domain.fin.MovimentoCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.SaldoFinanceiro;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoFinanceiro;
import br.com.finance.financeiro_pessoal.service.GenericService;

public interface SaldoFinanceiroService extends GenericService<SaldoFinanceiro>{

	SaldoFinanceiro findByDataMovimentoAndTipoFinanceiro(Date dataMovimento, TipoFinanceiro tipoFinanceiro);
	
	BigDecimal findByDataMovimentoSaldoFinalDiaAnterior(LocalDate dataMovimentoAnteriorSaldoFinal);
	
	public SaldoFinanceiro calcularSaldoFinanceiro(MovimentoCaixa movimentoCaixa);
}
