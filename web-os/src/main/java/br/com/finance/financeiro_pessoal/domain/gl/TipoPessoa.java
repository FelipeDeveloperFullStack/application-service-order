package br.com.finance.financeiro_pessoal.domain.gl;

public enum TipoPessoa {

	FISICA("PESSOA FÍSICA"), JURIDICA("PESSOA JURÍDICA");
	
	private String descricao;
	
	TipoPessoa(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
