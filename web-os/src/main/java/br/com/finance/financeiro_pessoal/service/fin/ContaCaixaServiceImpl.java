package br.com.finance.financeiro_pessoal.service.fin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.domain.gl.type.Situacao;
import br.com.finance.financeiro_pessoal.repository.fin.ContaCaixaRepository;
import br.com.finance.financeiro_pessoal.service.HandlerRuntimeException;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ContaCaixaServiceImpl implements ContaCaixaService{
	
	@Autowired
	private ContaCaixaRepository contaCaixaRepository;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ContaCaixa salvar(ContaCaixa contaCaixa) {
		try {
			if(contaCaixa.getId() != null){
				contaCaixa = setarContaCaixa(contaCaixa);
			}
			return contaCaixaRepository.save(contaCaixa);
		} catch (RuntimeException e) {
			HandlerRuntimeException.handlerRuntimeException(e.getMessage(), ContaCaixaServiceImpl.class);
		}
		return contaCaixa;
	}
	
	private ContaCaixa setarContaCaixa(ContaCaixa contaCaixa){
		ContaCaixa conta = procurarPeloID(contaCaixa.getId());
		if(conta.isPossuiMovimento()){
			contaCaixa.setDataInicial(conta.getDataInicial());
			contaCaixa.setSaldoInicial(conta.getSaldoInicial());
			contaCaixa.setPossuiMovimento(conta.isPossuiMovimento());
		}
		return contaCaixa;
	}

	@Override
	public List<ContaCaixa> listarTodos() {
		return contaCaixaRepository.findAll();
	}

	@Override
	public ContaCaixa procurarPeloID(Long id) {
		return contaCaixaRepository.findOne(id);
	}

	@Override
	public List<ContaCaixa> findByContaCaixaAtivo(Situacao situacao) {
		return contaCaixaRepository.findBySituacao(situacao);
	}
	
	@Override
	public void atualizarContaCaixa(ContaCaixa contaCaixa, Boolean possuiMovimento){
		contaCaixa.setPossuiMovimento(possuiMovimento);
		contaCaixaRepository.save(contaCaixa);
	}


}
