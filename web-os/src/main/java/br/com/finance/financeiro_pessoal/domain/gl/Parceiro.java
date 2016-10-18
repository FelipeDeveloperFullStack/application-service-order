package br.com.finance.financeiro_pessoal.domain.gl;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.finance.financeiro_pessoal.domain.GenericDomain;

@Entity
@Table(name = "tbl_parceiro")
public class Parceiro extends GenericDomain{

	private static final long serialVersionUID = -4191571551428701093L;
	
	@Column(name = "cli_parceiro")
	@NotEmpty(message = "O parceiro é obrigatório!")
	private String parceiro;

	public String getParceiro() {
		return parceiro;
	}

	public void setParceiro(String parceiro) {
		this.parceiro = parceiro;
	}
	
	
	
}
