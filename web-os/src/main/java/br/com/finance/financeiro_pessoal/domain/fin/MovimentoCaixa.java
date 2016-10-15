package br.com.finance.financeiro_pessoal.domain.fin;

import java.time.LocalDate;

import br.com.finance.financeiro_pessoal.domain.gl.Parceiro;

public class MovimentoCaixa {

	private Parceiro parceiro;
	
	private ContaCaixa contaCaixa;
	
	private LocalDate dataMovimento;
}
