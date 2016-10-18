package br.com.finance.financeiro_pessoal.domain.gl;

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

import br.com.finance.financeiro_pessoal.domain.gl.type.Situacao;
import br.com.finance.financeiro_pessoal.domain.gl.type.TipoPessoa;

@Entity
@Table(name = "tbl_cliente")
public class Cliente extends Parceiro{

	private static final long serialVersionUID = -3773578657921276220L;
	
	@Column(name = "cli_tipo_pessoa")
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;
	
	@Column(name = "cli_cpf")
	private String cpf;
	
	@Column(name = "cli_orgao_expedidor")
	private String orgaoExpedidor;
	
	@Column(name = "cli_uf_orgao_expedidor")
	private String ufOrgaoExpedidor;
	
	@Column(name = "cli_cnpj")
	private String cnpj;
	
	@Column(name = "cli_razao_social")
	private String razaoSocial;
	
	@Column(name = "cli_nome_fantasia")
	private String nomeFantasia;
	
	@Column(name = "cli_inscricao_estadual")
	private String inscricaoEstadual;
	
	@Column(name = "cli_inscricao_estaduao_st")
	private String inscricaoEstadualST;
	
	@Column(name = "cli_inscricao_municipal")
	private String inscricaoMunicipal;
	
	@Column(name = "cli_rg")
	private String rg;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "cli_dataNascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento = new Date();
	
	@Column(name = "cli_sexo")
	private String Sexo;
	
	@Column(name = "cli_cep")
	private String cep;
	
	@Column(name = "cli_uf")
	private String uf;
	
	@Column(name = "cli_municipio")
	private String municipio;
	
	@Column(name = "cli_logradouro")
	private String logradouro;
	
	@Column(name = "cli_numero")
	private String numero;
	
	@Column(name = "cli_complemento")
	private String complemento;
	
	@Column(name = "cli_bairro")
	private String bairro;
	
	@Column(name = "cli_ponto_referencia")
	private String pontoReferencia;

	@Column(name = "cli_endereco_cobranca")
	private String enderecoCobranca;
	
	@Column(name = "cli_telefoneCelular")
	private String telefoneCelular;
	
	@Column(name = "cli_telefoneResidencial")
	private String telefoneResidencial;
	
	@Column(name = "cli_ramal")
	private String ramal;
	
	@Column(name = "cli_site")
	private String site;
	
	@Column(name = "cli_email")
	private String email;
	
	@Column(name = "cli_observacao")
	private String observacao;
	
	//Crédito
	@Column(name = "cli_multa")
	private BigDecimal multa = new BigDecimal(0.0);
	
	@Column(name = "cli_mora")
	private BigDecimal mora = new BigDecimal(0.0);
	
	@Column(name = "cli_dias_tolerancia")
	private Long diasTolerancia = new Long(0);
	
	@Column(name = "cli_dias_bloqueio")
	private Long diasBloqueio = new Long(0);
	
	@Column(name = "cli_dias_prazo_pagamento")
	private Long diasPrazoPagamento = new Long(0);
	
	@Column(name = "cli_situacao")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEnderecoCobranca() {
		return enderecoCobranca;
	}

	public void setEnderecoCobranca(String enderecoCobranca) {
		this.enderecoCobranca = enderecoCobranca;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public String getUfOrgaoExpedidor() {
		return ufOrgaoExpedidor;
	}

	public void setUfOrgaoExpedidor(String ufOrgaoExpedidor) {
		this.ufOrgaoExpedidor = ufOrgaoExpedidor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoEstadualST() {
		return inscricaoEstadualST;
	}

	public void setInscricaoEstadualST(String inscricaoEstadualST) {
		this.inscricaoEstadualST = inscricaoEstadualST;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getMulta() {
		return multa;
	}

	public void setMulta(BigDecimal multa) {
		this.multa = multa;
	}

	public BigDecimal getMora() {
		return mora;
	}

	public void setMora(BigDecimal mora) {
		this.mora = mora;
	}

	public Long getDiasTolerancia() {
		return diasTolerancia;
	}

	public void setDiasTolerancia(Long diasTolerancia) {
		this.diasTolerancia = diasTolerancia;
	}

	public Long getDiasBloqueio() {
		return diasBloqueio;
	}

	public void setDiasBloqueio(Long diasBloqueio) {
		this.diasBloqueio = diasBloqueio;
	}

	public Long getDiasPrazoPagamento() {
		return diasPrazoPagamento;
	}

	public void setDiasPrazoPagamento(Long diasPrazoPagamento) {
		this.diasPrazoPagamento = diasPrazoPagamento;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Cliente [parceiro=" + getParceiro() + ", tipoPessoa=" + tipoPessoa + ", cpf=" + cpf + ", orgaoExpedidor="
				+ orgaoExpedidor + ", ufOrgaoExpedidor=" + ufOrgaoExpedidor + ", cnpj=" + cnpj + ", razaoSocial="
				+ razaoSocial + ", nomeFantasia=" + nomeFantasia + ", inscricaoEstadual=" + inscricaoEstadual
				+ ", inscricaoEstadualST=" + inscricaoEstadualST + ", inscricaoMunicipal=" + inscricaoMunicipal
				+ ", rg=" + rg + ", dataNascimento=" + dataNascimento + ", Sexo=" + Sexo + ", cep=" + cep + ", uf=" + uf
				+ ", municipio=" + municipio + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento="
				+ complemento + ", bairro=" + bairro + ", pontoReferencia=" + pontoReferencia + ", enderecoCobranca="
				+ enderecoCobranca + ", telefoneCelular=" + telefoneCelular + ", telefoneResidencial="
				+ telefoneResidencial + ", ramal=" + ramal + ", site=" + site + ", email=" + email + ", observacao="
				+ observacao + ", multa=" + multa + ", mora=" + mora + ", diasTolerancia=" + diasTolerancia
				+ ", diasBloqueio=" + diasBloqueio + ", diasPrazoPagamento=" + diasPrazoPagamento + ", situacao="
				+ situacao + "]";
	}

	
}
