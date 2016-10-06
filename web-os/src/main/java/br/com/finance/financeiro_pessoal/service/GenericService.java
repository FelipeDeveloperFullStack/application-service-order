package br.com.finance.financeiro_pessoal.service;

import java.util.List;

public interface GenericService<T> {

	T salvar(T objeto);

	List<T> listarTodos();

	T procurarPeloID(Long id);

	void excluir(Long codigo);

}
