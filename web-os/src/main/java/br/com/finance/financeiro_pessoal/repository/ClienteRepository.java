package br.com.finance.financeiro_pessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.finance.financeiro_pessoal.domain.gl.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findByNomeContaining(String nome);
}
