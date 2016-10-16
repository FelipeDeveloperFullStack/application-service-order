package br.com.finance.financeiro_pessoal.domain.gl;


import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.finance.financeiro_pessoal.domain.GenericDomain;

@Entity
@Table(name = "tbl_parceiro")
public class Parceiro extends GenericDomain{

	private static final long serialVersionUID = -4191571551428701093L;
	
}
