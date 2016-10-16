package br.com.finance.financeiro_pessoal.service.gl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.finance.financeiro_pessoal.domain.gl.Cliente;
import br.com.finance.financeiro_pessoal.domain.gl.type.Situacao;
import br.com.finance.financeiro_pessoal.repository.gl.ClienteRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente procurarPeloID(Long id) {
		return clienteRepository.findOne(id);
	}


	@Override
	public Long totalClienteCadastrado() {
		return clienteRepository.count();
	}

	@Override
	public List<Cliente> procurarPeloNome(String nome) {
		return clienteRepository.findByNomeContaining(nome);
	}

	@Override
	public List<Cliente> findByClientesAtivos(Situacao situacao) {
		return clienteRepository.findBySituacao(situacao);
	}

}
