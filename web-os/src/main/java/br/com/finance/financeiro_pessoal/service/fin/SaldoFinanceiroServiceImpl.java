package br.com.finance.financeiro_pessoal.service.fin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.MovimentoCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.SaldoFinanceiro;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoFinanceiro;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoOrigemMovimento;
import br.com.finance.financeiro_pessoal.repository.fin.SaldoFinanceiroRepository;
import br.com.finance.financeiro_pessoal.util.DateUtil;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class SaldoFinanceiroServiceImpl implements SaldoFinanceiroService {

	@Autowired
	private SaldoFinanceiroRepository saldoFinanceiroRepository;
	
	@Autowired
	private ContaCaixaService contaCaixaService;
	
	@Override
	public SaldoFinanceiro calcularSaldoFinanceiro(MovimentoCaixa movimentoCaixa){
		ContaCaixa contaCaixa = contaCaixaService.procurarPeloID(movimentoCaixa.getContaCaixa().getId());
		BigDecimal saldoInicial = BigDecimal.ZERO;
		if(!contaCaixa.isPossuiMovimento()){
			saldoInicial = contaCaixa.getSaldoInicial();
			calcularResumoCaixa(saldoInicial, movimentoCaixa);
			atualizarContaCaixa(contaCaixa);
		}else{
			calcularResumoCaixa(verificarSeExisteSaldoFinalDiaAnterior(movimentoCaixa, movimentoCaixa.getContaCaixa().getSaldoInicial()),movimentoCaixa);
		}
		return obterSaldoFinanceiro(movimentoCaixa);
	}
	
	private BigDecimal verificarSeExisteSaldoFinalDiaAnterior(MovimentoCaixa movimentoCaixa, BigDecimal saldoInicial){
		BigDecimal saldo = findByDataMovimentoSaldoFinalDiaAnterior(DateUtil.asLocalDate(movimentoCaixa.getDataMovimento()));
		if(saldo == BigDecimal.ZERO){
			return saldoInicial;
		}
		return saldo;
	}
	
	private void calcularResumoCaixa(BigDecimal saldoFinalAnteriorOuSaldoInicialAtual, MovimentoCaixa movimentoCaixa){
		BigDecimal saldoInicial = saldoFinalAnteriorOuSaldoInicialAtual;
		BigDecimal totalEntrada = BigDecimal.ZERO;
		BigDecimal totalSaida = BigDecimal.ZERO;
		BigDecimal saldoOperacional = BigDecimal.ZERO;
		
		if(movimentoCaixa.getTipoOrigemMovimento() == TipoOrigemMovimento.RECEBIMENTO){
			totalEntrada = movimentoCaixa.getValorMovimento();
		}
		if(movimentoCaixa.getTipoOrigemMovimento() == TipoOrigemMovimento.PAGAMENTO){
			totalSaida = movimentoCaixa.getValorMovimento();
		}
		if(movimentoCaixa.getTipoOrigemMovimento() == TipoOrigemMovimento.LANCAMENTO){
			totalEntrada = movimentoCaixa.getValorMovimento();
		}
		if(movimentoCaixa.getTipoOrigemMovimento() == TipoOrigemMovimento.TRANSFERENCIA_PARA_ORIGEM){
			totalEntrada = movimentoCaixa.getValorMovimento();
		}
		if(movimentoCaixa.getTipoOrigemMovimento() == TipoOrigemMovimento.TRANSFERENCIA_PARA_DESTINO){
			totalSaida = movimentoCaixa.getValorMovimento();
		}
		
		SaldoFinanceiro saldoFinanceiro = obterSaldoFinanceiro(movimentoCaixa);
		
		if(saldoFinanceiro == null){
			saldoFinanceiro = new SaldoFinanceiro();
			saldoOperacional = calcularSaldoOperacional(saldoFinanceiro.getTotalEntrada().add(totalEntrada), saldoFinanceiro.getTotalSaida().add(totalSaida));
		}else{
			saldoOperacional = calcularSaldoOperacional(saldoFinanceiro.getTotalEntrada().add(totalEntrada), saldoFinanceiro.getTotalSaida().add(totalSaida));
		}
		
		salvar(setarSaldoFinanceiro(movimentoCaixa,calcularSaldoFinal(saldoOperacional, saldoInicial)
				,saldoInicial, saldoOperacional
				,TipoFinanceiro.CAIXA, totalEntrada, totalSaida));
		
	}
	
	private SaldoFinanceiro setarSaldoFinanceiro(MovimentoCaixa movimentoCaixa, BigDecimal saldoFinal, BigDecimal saldoInicial
			,BigDecimal saldoOperacional, TipoFinanceiro tipoFinanceiro
			,BigDecimal totalEntrada, BigDecimal totalSaida){
		
		SaldoFinanceiro saldoFinanceiro = obterSaldoFinanceiro(movimentoCaixa);
		
		if(saldoFinanceiro == null){
			saldoFinanceiro = new SaldoFinanceiro();
			return setarSaldoFinanceiro(saldoFinanceiro, movimentoCaixa, saldoFinal, saldoInicial, saldoOperacional, tipoFinanceiro, totalEntrada, totalSaida);
		}else{
			return setarSaldoFinanceiro(saldoFinanceiro, movimentoCaixa, saldoFinal, saldoInicial, saldoOperacional, tipoFinanceiro, totalEntrada, totalSaida);
		}
	}
	
	private SaldoFinanceiro setarSaldoFinanceiro(SaldoFinanceiro saldoFinanceiro,MovimentoCaixa movimentoCaixa, BigDecimal saldoFinal, BigDecimal saldoInicial
			,BigDecimal saldoOperacional, TipoFinanceiro tipoFinanceiro
			,BigDecimal totalEntrada, BigDecimal totalSaida){
		
		saldoFinanceiro.setDataMovimento(movimentoCaixa.getDataMovimento());
		saldoFinanceiro.setSaldoFinal(saldoFinal);
		saldoFinanceiro.setSaldoInicial(saldoInicial);
		saldoFinanceiro.setSaldoOperacional(saldoOperacional);
		saldoFinanceiro.setTipoFinanceiro(tipoFinanceiro);
		saldoFinanceiro.setTotalEntrada(saldoFinanceiro.getTotalEntrada().add(totalEntrada));
		saldoFinanceiro.setTotalSaida(saldoFinanceiro.getTotalSaida().add(totalSaida));
		
		return saldoFinanceiro;
	}
	
	private SaldoFinanceiro obterSaldoFinanceiro(MovimentoCaixa movimentoCaixa){
		SaldoFinanceiro saldoFinanceiro = saldoFinanceiroRepository.findByDataMovimentoAndTipoFinanceiro
				(movimentoCaixa.getDataMovimento(), TipoFinanceiro.CAIXA);
		return saldoFinanceiro;
	}
	
	
	private BigDecimal calcularSaldoFinal(BigDecimal saldoOperacional, BigDecimal saldoInicial){
		return saldoOperacional.add(saldoInicial);
	}
	
	private BigDecimal calcularSaldoOperacional(BigDecimal totalEntrada, BigDecimal totalSaida){
		return totalEntrada.subtract(totalSaida);
	}
	
	private void atualizarContaCaixa(ContaCaixa contaCaixa){
		contaCaixa.setPossuiMovimento(Boolean.TRUE);
		contaCaixaService.salvar(contaCaixa);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SaldoFinanceiro salvar(SaldoFinanceiro saldoFinanceiro) {
		return saldoFinanceiroRepository.save(saldoFinanceiro);
	}

	@Override
	public List<SaldoFinanceiro> listarTodos() {
		return saldoFinanceiroRepository.findAll();
	}

	@Override
	public SaldoFinanceiro procurarPeloID(Long id) {
		return saldoFinanceiroRepository.findOne(id);
	}

	@Override
	public SaldoFinanceiro findByDataMovimentoAndTipoFinanceiro(Date dataMovimento, TipoFinanceiro tipoFinanceiro) {
		return saldoFinanceiroRepository.findByDataMovimentoAndTipoFinanceiro(dataMovimento, tipoFinanceiro);
	}

	@Override
	public BigDecimal findByDataMovimentoSaldoFinalDiaAnterior(LocalDate dataMovimentoAnteriorSaldoFinal) {
		SaldoFinanceiro saldoFinanceiroDataMovimentoSaldoFinalAnterior = saldoFinanceiroRepository
				.findByDataMovimento(DateUtil.asDate(dataMovimentoAnteriorSaldoFinal.minusDays(1)));
		return saldoFinanceiroDataMovimentoSaldoFinalAnterior == null 
				? BigDecimal.ZERO : saldoFinanceiroDataMovimentoSaldoFinalAnterior.getSaldoFinal() ;
	}
	


}
