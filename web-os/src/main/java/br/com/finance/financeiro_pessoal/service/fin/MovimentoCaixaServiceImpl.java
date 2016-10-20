package br.com.finance.financeiro_pessoal.service.fin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.MovimentoCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.SaldoFinanceiro;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoFinanceiro;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoOrigemMovimento;
import br.com.finance.financeiro_pessoal.repository.fin.MovimentoCaixaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MovimentoCaixaServiceImpl implements MovimentoCaixaService{

	@Autowired
	private MovimentoCaixaRepository movimentoCaixaRepository;
	
	@Autowired
	private SaldoFinanceiroService saldoFinanceiroService;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MovimentoCaixa salvar(MovimentoCaixa movimentoCaixa) {
		return movimentoCaixaRepository.save(movimentoCaixa);
	}

	@Override
	public List<MovimentoCaixa> listarTodos() {
		return movimentoCaixaRepository.findAll();
	}

	@Override
	public MovimentoCaixa procurarPeloID(Long id) {
		return movimentoCaixaRepository.findOne(id);
	}

	@Override
	public List<MovimentoCaixa> findByDataMovimentoEquals(Date dataMovimento) {
		return movimentoCaixaRepository.findByDataMovimentoEquals(dataMovimento);
	}

	@Override
	public List<MovimentoCaixa> findByDataMovimentoAndContaCaixa(Date dataMovimento, ContaCaixa contaCaixa) {
		return movimentoCaixaRepository.findByDataMovimentoAndContaCaixa(dataMovimento, contaCaixa);
	}

	@Override
	public Page<MovimentoCaixa> findAll(Pageable pageable) {
		return movimentoCaixaRepository.findAll(pageable);
	}

	@Override
	public MovimentoCaixa findByContaCaixaEndingWith(ContaCaixa contaCaixa) {
		return movimentoCaixaRepository.findByContaCaixaEndingWith(contaCaixa);
	}

	@Override
	public void excluirMovimentoCaixa(Long id) {
		MovimentoCaixa movimentoCaixa = procurarPeloID(id);
		for(SaldoFinanceiro saldoFinanceiro : saldoFinanceiroService.listarTodos()){
			if(saldoFinanceiro.getTipoFinanceiro() == TipoFinanceiro.CAIXA){
				saldoFinanceiroService.salvar(consistirMovimento(movimentoCaixa, saldoFinanceiro));
			}
		}
		movimentoCaixaRepository.delete(id);
	}
	
	private SaldoFinanceiro consistirMovimento(MovimentoCaixa movimentoCaixa, SaldoFinanceiro saldoFinanceiro){
		
		if(movimentoCaixa.getTipoOrigemMovimento() == TipoOrigemMovimento.LANCAMENTO){
			saldoFinanceiro.setTotalEntrada(saldoFinanceiro.getTotalEntrada().subtract(movimentoCaixa.getValorMovimento()));
		}
		if(movimentoCaixa.getTipoOrigemMovimento() == TipoOrigemMovimento.PAGAMENTO){
			saldoFinanceiro.setTotalSaida(saldoFinanceiro.getTotalSaida().subtract(movimentoCaixa.getValorMovimento()));
		}
		if(movimentoCaixa.getTipoOrigemMovimento() == TipoOrigemMovimento.RECEBIMENTO){
			saldoFinanceiro.setTotalEntrada(saldoFinanceiro.getTotalEntrada().subtract(movimentoCaixa.getValorMovimento()));
		}
		if(movimentoCaixa.getTipoOrigemMovimento() == TipoOrigemMovimento.TRANSFERENCIA_PARA_DESTINO){
			saldoFinanceiro.setTotalSaida(saldoFinanceiro.getTotalSaida().subtract(movimentoCaixa.getValorMovimento()));
		}
		if(movimentoCaixa.getTipoOrigemMovimento() == TipoOrigemMovimento.TRANSFERENCIA_PARA_ORIGEM){
			saldoFinanceiro.setTotalEntrada(saldoFinanceiro.getTotalEntrada().subtract(movimentoCaixa.getValorMovimento()));
		}
		
		return saldoFinanceiro;
	}

}
