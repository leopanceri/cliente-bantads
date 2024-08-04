package br.net.dac.cliente.model;

import java.io.Serializable;
import java.time.LocalDateTime;


//import br.net.dac.cliente.model.Endereco;

public class ClienteDTO implements Serializable {
	

	private Long id;
	private String nome;
	private String cpf;
	private Double salario;
	private String email;
	private String telefone;
	private String status;
	private String statusSet;
	private String motivo;
	private EnderecoDTO endereco;

	public ClienteDTO() {

	}

	public ClienteDTO(Long id, String nome, String cpf, Double salario, String email, String telefone,
			String status, String statusSet, String motivo, EnderecoDTO endereco) {
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
	

	public ClienteDTO(Long id, String status,String statusSet, String motivo) {
		super();
		this.id = id;
		this.status = status;
		this.statusSet = statusSet;
		this.motivo = motivo;
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

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}


}
