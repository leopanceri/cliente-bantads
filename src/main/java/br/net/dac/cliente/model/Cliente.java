package br.net.dac.cliente.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table (name = "clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue
	@Column (name="id")
	private Long id;
	@Column (name="nome")
	private String nome;
	@Column (name="cpf")
	private String cpf;
	@Column (name="salario")
	private Double salario;
	@Column (name="email")
	private String email;
	@Column (name="cep")
	private String cep;
	@Column (name="endereco")
	private String endereco;
	@Column (name="telefone")
	private String telefone;   
	
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(Long id, String nome, String cpf, Double salario, String email, String cep,
			String endereco, String status) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.email = email;
		this.cep = cep;
		this.endereco = endereco;
		this.telefone = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
