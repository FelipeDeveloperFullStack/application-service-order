package br.com.finance.financeiro_pessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.finance.financeiro_pessoal.domain.CategoriaDespesa;

@Repository
public interface CategoriaDespesaRepository extends JpaRepository<CategoriaDespesa, Long>{

	@Query("SELECT c FROM CategoriaDespesa c WHERE c.categoria LIKE %?1%")
	List<CategoriaDespesa> findByCategoriaDespesa(String categoria);
}
