package br.com.finance.financeiro_pessoal.domain.fin.type;

public enum TipoOrigemMovimento {
	
	LANCAMENTO("Lançamento"),
	TRANSFERENCIA("Transferência"),
	RECEBIMENTO("Recebimento"),
	PAGAMENTO("Pagamento"), 
	VENDA("Venda"),
	COMPRA("Compra");
	
	private String tipo;
	
	TipoOrigemMovimento(String tipo) {
		 this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

}
