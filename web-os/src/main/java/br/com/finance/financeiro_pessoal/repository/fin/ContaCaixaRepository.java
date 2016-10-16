package br.com.finance.financeiro_pessoal.repository.fin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;
import br.com.finance.financeiro_pessoal.domain.gl.type.Situacao;

@Repository
public interface ContaCaixaRepository extends JpaRepository<ContaCaixa, Long>{
	
	List<ContaCaixa> findBySituacao(Situacao situacao);
	
}
