package br.com.finance.financeiro_pessoal.domain.gl;

import java.io.Serializable;

public class Parceiro implements Serializable{

	private static final long serialVersionUID = -2026852964552249597L;
	
	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
