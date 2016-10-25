package br.com.finance.financeiro_pessoal.service.fin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.MovimentoCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.SaldoFinanceiro;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoFinanceiro;
import br.com.finance.financeiro_pessoal.service.GenericService;

public interface SaldoFinanceiroService extends GenericService<SaldoFinanceiro>{

	SaldoFinanceiro findByDataMovimentoAndTipoFinanceiroAndContaCaixa(Date dataMovimento, TipoFinanceiro tipoFinanceiro, ContaCaixa contaCaixa);
	
	BigDecimal findByDataMovimentoSaldoFinalDiaAnterior(LocalDate dataMovimentoAnteriorSaldoFinal, ContaCaixa contaCaixa, TipoFinanceiro tipoFinanceiro);
	
	SaldoFinanceiro calcularSaldoFinanceiro(MovimentoCaixa movimentoCaixa);
	
	List<SaldoFinanceiro> findByContaCaixa(ContaCaixa contaCaixa);
	
	BigDecimal calcularSaldoFinal(BigDecimal saldoOperacional, BigDecimal saldoInicial);
	
	BigDecimal calcularSaldoOperacional(BigDecimal totalEntrada, BigDecimal totalSaida);
	
	List<SaldoFinanceiro> findByDataMovimentoBeforeAndContaCaixa(Date dataMovimento, ContaCaixa contaCaixa);
	
	void calcularTodoSaldosFinanceiros(ContaCaixa contaCaixa, TipoFinanceiro tipoFinanceiro);
	
}
