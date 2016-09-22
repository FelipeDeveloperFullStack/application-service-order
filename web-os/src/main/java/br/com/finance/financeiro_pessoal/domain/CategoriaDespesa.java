package br.com.finance.financeiro_pessoal.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tbl_categoria_despesa")
public class CategoriaDespesa extends GenericDomain{

	private static final long serialVersionUID = 590973506914912052L;
	
	@NotBlank(message = "A categoria é obrigatória!")
	private String categoria;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return this.categoria;
	}
	
	

}
