package br.com.finance.financeiro_pessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.finance.financeiro_pessoal.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
