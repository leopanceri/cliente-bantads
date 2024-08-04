package br.net.dac.cliente.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
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
	@Column (name="telefone")
	private String telefone;
	@Column (name="status")      //pendente, aprovada, rejeitada, encerrada
	private String status;
	@Column (name="statusset")
	private String statusSet;
	@Column (name="motivo")
	private String motivo;
	@OneToOne()
	@JoinColumn(name="endereco_id")
	private Endereco endereco;

	

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Cliente(Long id, String nome, String cpf, Double salario, String email, String telefone, String status,
			String statusSet, String motivo, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.email = email;
		this.telefone = telefone;
		this.status = status;
		this.statusSet = statusSet;
		this.motivo = motivo;
		this.endereco = endereco;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusSet() {
		return statusSet;
	}

	public void setStatusSet(String statusSet) {
		this.statusSet = statusSet;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	

}
