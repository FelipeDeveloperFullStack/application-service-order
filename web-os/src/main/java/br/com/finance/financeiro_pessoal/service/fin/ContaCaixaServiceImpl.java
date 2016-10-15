package br.com.finance.financeiro_pessoal.service.fin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.repository.fin.ContaCaixaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ContaCaixaServiceImpl implements ContaCaixaService{
	
	@Autowired
	private ContaCaixaRepository contaCaixaRepository;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ContaCaixa salvar(ContaCaixa contaCaixa) {
		return contaCaixaRepository.save(contaCaixa);
	}

	@Override
	public List<ContaCaixa> listarTodos() {
		return contaCaixaRepository.findAll();
	}

	@Override
	public ContaCaixa procurarPeloID(Long id) {
		return contaCaixaRepository.findOne(id);
	}


}
