package br.com.finance.financeiro_pessoal.domain.fin;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import br.com.finance.financeiro_pessoal.domain.GenericDomain;
import br.com.finance.financeiro_pessoal.domain.gl.type.Situacao;

@Entity
@Table(name = "tbl_conta_caixa")
public class ContaCaixa extends GenericDomain{

	private static final long serialVersionUID = 6853277500133159036L;
	
	@Column(name = "cc_descricao")
	@NotEmpty(message = "A descrição é obrigatória!")
	private String descricao;
	
	@Column(name = "cc_saldo_inicial")
	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0,00")
	@DecimalMax(value = "9999999.99", message = "O valor não pode ser maior que 9.999.999,99")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal saldoInicial = BigDecimal.ZERO;
	
	@Column(name = "cc_data_inicial")
	@NotNull(message = "A data inicial é obrigatória!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataInicial = new Date();
	
	@Column(name = "cc_situacao")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	

}
