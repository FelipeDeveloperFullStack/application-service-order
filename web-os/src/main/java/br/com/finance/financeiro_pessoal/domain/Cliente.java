package br.com.finance.financeiro_pessoal.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tbl_cliente")
public class Cliente extends GenericDomain{

	private static final long serialVersionUID = -3773578657921276220L;
	
	@Column(name = "cli_nome")
	@NotBlank(message = "O nome do cliente é obrigatório!")
	private String nome;
	
	@Column(name = "cli_cpf_cnpj")
	@NotBlank(message = "O CPF/CNPJ do cliente é obrigatório!")
	private String cpf_cnpj;
	
	@Column(name = "cli_rg")
	private String rg;
	
	@Column(name = "cli_dataNascimento")
	private Date dataNascimento;

	@Column(name = "cli_endereco")
	@NotBlank(message = "O endereço do cliente é obrigatório!")
	private String endereco;
	
	@Column(name = "cli_enderecoCobranca")
	@NotBlank(message = "O endereço de cobrança do cliente é obrigatório!")
	private String enderecoCobranca;
	
	@Column(name = "cli_telefoneCelular")
	private String telefoneCelular;
	
	@Column(name = "cli_telefoneResidencial")
	private String telefoneResidencial;
	
	@Column(name = "cli_email")
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
}
