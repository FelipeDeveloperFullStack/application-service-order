package br.com.finance.financeiro_pessoal.service.fin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
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
		
		calcularTodoSaldosFinanceiros(movimentoCaixa.getContaCaixa(), TipoFinanceiro.CAIXA);
		
		BigDecimal saldo = findByDataMovimentoSaldoFinalDiaAnterior(DateUtil.asLocalDate(movimentoCaixa.getDataMovimento()), movimentoCaixa.getContaCaixa(), TipoFinanceiro.CAIXA);
		if(saldo == BigDecimal.ZERO){
			return saldoInicial;
		}
		return saldo;
	}
	
	private void calcularResumoCaixa(BigDecimal saldoFinalAnteriorOuSaldoInicialAtual,MovimentoCaixa movimentoCaixa){
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
	
	private SaldoFinanceiro setarSaldoFinanceiro(MovimentoCaixa movimentoCaixa
			,BigDecimal saldoFinal, BigDecimal saldoInicial
			,BigDecimal saldoOperacional, TipoFinanceiro tipoFinanceiro
			,BigDecimal totalEntrada, BigDecimal totalSaida){
		
		SaldoFinanceiro saldoFinanceiro = obterSaldoFinanceiro(movimentoCaixa);
		
		if(saldoFinanceiro == null){
			saldoFinanceiro = new SaldoFinanceiro();
			return setarSaldoFinanceiro(saldoFinanceiro, movimentoCaixa, saldoFinal
					, saldoInicial, saldoOperacional
					, tipoFinanceiro, totalEntrada, totalSaida);
		}else{
			return setarSaldoFinanceiro(saldoFinanceiro, movimentoCaixa
					, saldoFinal, saldoInicial, saldoOperacional
					, tipoFinanceiro, totalEntrada, totalSaida);
		}
	}
	
	private SaldoFinanceiro setarSaldoFinanceiro(SaldoFinanceiro saldoFinanceiro
			,MovimentoCaixa movimentoCaixa, BigDecimal saldoFinal, BigDecimal saldoInicial
			,BigDecimal saldoOperacional, TipoFinanceiro tipoFinanceiro
			,BigDecimal totalEntrada, BigDecimal totalSaida){
		
		saldoFinanceiro.setDataMovimento(movimentoCaixa.getDataMovimento());
		saldoFinanceiro.setSaldoFinal(saldoFinal);
		saldoFinanceiro.setSaldoInicial(saldoInicial);
		saldoFinanceiro.setSaldoOperacional(saldoOperacional);
		saldoFinanceiro.setTipoFinanceiro(tipoFinanceiro);
		saldoFinanceiro.setTotalEntrada(saldoFinanceiro.getTotalEntrada().add(totalEntrada));
		saldoFinanceiro.setTotalSaida(saldoFinanceiro.getTotalSaida().add(totalSaida));
		saldoFinanceiro.setContaCaixa(movimentoCaixa.getContaCaixa());
		
		return saldoFinanceiro;
	}
	
	private SaldoFinanceiro obterSaldoFinanceiro(MovimentoCaixa movimentoCaixa){
		SaldoFinanceiro saldoFinanceiro = saldoFinanceiroRepository.findByDataMovimentoAndContaCaixaAndTipoFinanceiro
				(movimentoCaixa.getDataMovimento(), movimentoCaixa.getContaCaixa() ,TipoFinanceiro.CAIXA);
		return saldoFinanceiro;
	}
	
	
	@Override
	public BigDecimal calcularSaldoFinal(BigDecimal saldoOperacional, BigDecimal saldoInicial){
		return saldoOperacional.add(saldoInicial);
	}
	
	@Override
	public BigDecimal calcularSaldoOperacional(BigDecimal totalEntrada, BigDecimal totalSaida){
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
	public SaldoFinanceiro findByDataMovimentoAndTipoFinanceiroAndContaCaixa(Date dataMovimento, TipoFinanceiro tipoFinanceiro, ContaCaixa contaCaixa) {
		return saldoFinanceiroRepository.findByDataMovimentoAndContaCaixaAndTipoFinanceiro(dataMovimento, contaCaixa ,tipoFinanceiro);
	}

	@Override
	public BigDecimal findByDataMovimentoSaldoFinalDiaAnterior(LocalDate dataMovimentoAnteriorSaldoFinal, ContaCaixa contaCaixa, TipoFinanceiro tipoFinanceiro) {
		List<SaldoFinanceiro> saldosAnterioresDaDataMovimento = findByDataMovimentoBeforeAndContaCaixa(DateUtil.asDate(dataMovimentoAnteriorSaldoFinal), contaCaixa);
		SaldoFinanceiro saldo = new SaldoFinanceiro();
		if(saldosAnterioresDaDataMovimento.isEmpty()){
			saldo = findByDataMovimentoAndTipoFinanceiroAndContaCaixa(DateUtil.asDate(dataMovimentoAnteriorSaldoFinal), TipoFinanceiro.CAIXA, contaCaixa);
			if(saldo == null){
				return BigDecimal.ZERO;
			}else{
				return saldo.getSaldoInicial();
			}
		}else{
			Collections.sort(saldosAnterioresDaDataMovimento, new Comparator<SaldoFinanceiro>() {
				@Override
				public int compare(SaldoFinanceiro saldo1, SaldoFinanceiro saldo2) {
					return saldo1.getDataMovimento().compareTo(saldo2.getDataMovimento());
				}
			});
			saldo = saldosAnterioresDaDataMovimento.get(saldosAnterioresDaDataMovimento.size() - 1);
			if(saldo == null){
				return BigDecimal.ZERO;
			}else{
				return saldo.getSaldoFinal();
			}
		}
	}

	@Override
	public List<SaldoFinanceiro> findByContaCaixa(ContaCaixa contaCaixa) {
		return saldoFinanceiroRepository.findByContaCaixa(contaCaixa);
	}

	@Override
	public List<SaldoFinanceiro> findByDataMovimentoBeforeAndContaCaixa(Date dataMovimento, ContaCaixa contaCaixa) {
		return saldoFinanceiroRepository.findByDataMovimentoBeforeAndContaCaixa(dataMovimento, contaCaixa);
	}
	
	//Usar esse método nos outros programas
	private void calcularTodoSaldosFinanceiros(ContaCaixa contaCaixa, TipoFinanceiro tipoFinanceiro){
		List<SaldoFinanceiro> saldos = saldoFinanceiroRepository.findByContaCaixaAndTipoFinanceiro(contaCaixa, tipoFinanceiro);
		if(saldos != null){
			Collections.sort(saldos, new Comparator<SaldoFinanceiro>() {
				@Override
				public int compare(SaldoFinanceiro s1, SaldoFinanceiro s2) {
					return s1.getDataMovimento().compareTo(s2.getDataMovimento());
				}
			});
			for(SaldoFinanceiro saldo : saldos){
				saldo.setSaldoOperacional(calcularSaldoOperacional(saldo.getTotalEntrada(), saldo.getTotalSaida()));
				BigDecimal saldoAnterior = findByDataMovimentoSaldoFinalDiaAnterior(DateUtil.asLocalDate(saldo.getDataMovimento()), contaCaixa, tipoFinanceiro);
				if(saldoAnterior == BigDecimal.ZERO){
					saldo.setSaldoInicial(contaCaixa.getSaldoInicial());
				}else{
					saldo.setSaldoInicial(saldoAnterior);
				}
				saldo.setSaldoFinal(calcularSaldoFinal(saldo.getSaldoOperacional(), saldo.getSaldoInicial()));
				salvar(saldo);
			}
		}
	}
}
