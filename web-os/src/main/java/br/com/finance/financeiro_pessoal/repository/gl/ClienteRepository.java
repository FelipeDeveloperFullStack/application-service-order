package br.com.finance.financeiro_pessoal.repository.gl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.finance.financeiro_pessoal.domain.gl.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findByNomeContaining(String nome);
}
