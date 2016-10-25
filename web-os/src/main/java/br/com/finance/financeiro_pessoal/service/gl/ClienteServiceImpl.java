package br.com.finance.financeiro_pessoal.service.gl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.finance.financeiro_pessoal.domain.fin.MovimentoCaixa;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoOrigemMovimento;
import br.com.finance.financeiro_pessoal.domain.gl.Cliente;
import br.com.finance.financeiro_pessoal.domain.gl.type.Situacao;
import br.com.finance.financeiro_pessoal.repository.fin.MovimentoCaixaRepository;
import br.com.finance.financeiro_pessoal.repository.gl.ClienteRepository;
import br.com.finance.financeiro_pessoal.service.HandlerRuntimeException;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private MovimentoCaixaRepository movimentoCaixaRepository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Cliente salvar(Cliente cliente) {
		return consistirCliente(cliente);
	}
	
	private Cliente consistirCliente(Cliente cliente){
		try {
			if(cliente.getParceiro().isEmpty()){
				HandlerRuntimeException.handlerRuntimeException("O nome do parceiro é obrigatório!", getClass());
			}else{
				return clienteRepository.save(cliente);
			}
		} catch (RuntimeException e) {
			HandlerRuntimeException.handlerRuntimeException(e.getMessage(), getClass());
		}
		return cliente;
	}
	
	public BigDecimal calcularTotalMovimentosRecebidoCliente(Cliente cliente){
		BigDecimal valorTotal = BigDecimal.ZERO;
		for(MovimentoCaixa mov : movimentoCaixaRepository.findByParceiro(cliente)){
			if(mov.getTipoOrigemMovimento() == TipoOrigemMovimento.LANCAMENTO){
				valorTotal = valorTotal.add(mov.getValorMovimento());
			}
			if(mov.getTipoOrigemMovimento() == TipoOrigemMovimento.RECEBIMENTO){
				valorTotal = valorTotal.add(mov.getValorMovimento());
			}
			if(mov.getTipoOrigemMovimento() == TipoOrigemMovimento.TRANSFERENCIA_PARA_ORIGEM){
				valorTotal = valorTotal.add(mov.getValorMovimento());
			}
		}
		return valorTotal;
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
		return clienteRepository.findByParceiroContaining(nome);
	}

	@Override
	public List<Cliente> findByClientesAtivos(Situacao situacao) {
		return clienteRepository.findBySituacao(situacao);
	}

}
