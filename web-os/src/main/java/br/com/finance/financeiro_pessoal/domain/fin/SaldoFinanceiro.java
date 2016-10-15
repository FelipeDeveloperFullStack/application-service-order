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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import br.com.finance.financeiro_pessoal.domain.GenericDomain;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoFinanceiro;

@Entity
@Table(name = "tbl_saldo_financeiro")
public class SaldoFinanceiro extends GenericDomain {

	private static final long serialVersionUID = -6179514232085984795L;

	@Column(name = "sf_data_movimento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataMovimento = new Date();

	@Column(name = "sf_saldo_inicial")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal saldoInicial = BigDecimal.ZERO;
	
	@Column(name = "sf_total_entrada")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal totalEntrada = BigDecimal.ZERO;
	
	@Column(name = "sf_total_saida")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal totalSaida = BigDecimal.ZERO;
	
	@Column(name = "sf_saldo_operacional")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal saldoOperacional = BigDecimal.ZERO;
	
	@Column(name = "sf_tipo_financeiro")
	@Enumerated(EnumType.STRING)
	private TipoFinanceiro tipoFinanceiro;

	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public BigDecimal getTotalEntrada() {
		return totalEntrada;
	}

	public void setTotalEntrada(BigDecimal totalEntrada) {
		this.totalEntrada = totalEntrada;
	}

	public BigDecimal getTotalSaida() {
		return totalSaida;
	}

	public void setTotalSaida(BigDecimal totalSaida) {
		this.totalSaida = totalSaida;
	}

	public BigDecimal getSaldoOperacional() {
		return saldoOperacional;
	}

	public void setSaldoOperacional(BigDecimal saldoOperacional) {
		this.saldoOperacional = saldoOperacional;
	}

	public TipoFinanceiro getTipoFinanceiro() {
		return tipoFinanceiro;
	}

	public void setTipoFinanceiro(TipoFinanceiro tipoFinanceiro) {
		this.tipoFinanceiro = tipoFinanceiro;
	}

	
}
