package br.com.finance.financeiro_pessoal.service.fin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.finance.financeiro_pessoal.domain.fin.SaldoFinanceiro;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoFinanceiro;
import br.com.finance.financeiro_pessoal.repository.fin.SaldoFinanceiroRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class SaldoFinanceiroServiceImpl implements SaldoFinanceiroService {

	@Autowired
	private SaldoFinanceiroRepository saldoFinanceiroRepository;
	
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


}
