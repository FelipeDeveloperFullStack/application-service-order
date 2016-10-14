package br.com.finance.financeiro_pessoal.repository.fin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.finance.financeiro_pessoal.domain.fin.ContaCaixa;

@Repository
public interface ContaCaixaRepository extends JpaRepository<ContaCaixa, Long>{

}
