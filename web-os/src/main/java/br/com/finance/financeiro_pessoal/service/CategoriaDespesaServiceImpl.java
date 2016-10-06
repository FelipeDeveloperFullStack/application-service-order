package br.com.finance.financeiro_pessoal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.finance.financeiro_pessoal.domain.CategoriaDespesa;
import br.com.finance.financeiro_pessoal.repository.CategoriaDespesaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CategoriaDespesaServiceImpl implements CategoriaDespesaService {

	@Autowired
	private CategoriaDespesaRepository categoriaDespesaRepository;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public CategoriaDespesa salvar(CategoriaDespesa categoriaDespesa) {
		try {
			if (categoriaDespesa != null) {
				return categoriaDespesaRepository.save(categoriaDespesa);
			} else {
				HandlerRuntimeException.handlerRuntimeException("Objeto categoriaDespesa está nulo: Exceção ocorreu na classe: ", getClass());
			}
		} catch (RuntimeException e) {
			HandlerRuntimeException.handlerRuntimeException(e.getMessage(), getClass());
		}
		return categoriaDespesa;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(Long codigo){
		try {
			categoriaDespesaRepository.delete(codigo);
		} catch (RuntimeException e) {
			HandlerRuntimeException.handlerRuntimeException(e.getMessage(), getClass());
		}
	}

	public List<CategoriaDespesa> listarTodos() {
		return categoriaDespesaRepository.findAll();
	}

	public CategoriaDespesa procurarPeloID(Long id) {
		return categoriaDespesaRepository.findOne(id);
	}


}
