package br.com.finance.financeiro_pessoal.domain.fin;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import br.com.finance.financeiro_pessoal.domain.GenericDomain;
import br.com.finance.financeiro_pessoal.domain.fin.type.TipoOrigemMovimento;
import br.com.finance.financeiro_pessoal.domain.gl.Cliente;
import br.com.finance.financeiro_pessoal.domain.gl.Parceiro;

@Entity
@Table(name = "tbl_movimento_caixa")
public class MovimentoCaixa extends GenericDomain{
	
	private static final long serialVersionUID = 4933752812585187865L;

	@OneToOne
	@JoinColumn(name = "mc_parceiro")
	@NotNull(message = "O parceiro é obrigatório!")
	private Parceiro parceiro;
	
	@OneToOne
	@JoinColumn(name = "mc_conta_caixa")
	@NotNull(message = "A conta financeira é obrigatório!")
	private ContaCaixa contaCaixa;
	
	@Column(name = "mc_data_movimento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "A data do movimento é obrigatória!")
	private Date dataMovimento = new Date();
	
	@Column(name = "mc_valor_movimento")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorMovimento = BigDecimal.ZERO;
	
	@Column(name = "mc_tipo_origem_movimento")
	@Enumerated(EnumType.STRING)
	private TipoOrigemMovimento tipoOrigemMovimento;
	
	public Parceiro getParceiro() {
		return parceiro;
	}

	public void setParceiro(Cliente parceiro) {
		this.parceiro = parceiro;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public BigDecimal getValorMovimento() {
		return valorMovimento;
	}

	public void setValorMovimento(BigDecimal valorMovimento) {
		this.valorMovimento = valorMovimento;
	}

	public TipoOrigemMovimento getTipoOrigemMovimento() {
		return tipoOrigemMovimento;
	}

	public void setTipoOrigemMovimento(TipoOrigemMovimento tipoOrigemMovimento) {
		this.tipoOrigemMovimento = tipoOrigemMovimento;
	}

	
}
