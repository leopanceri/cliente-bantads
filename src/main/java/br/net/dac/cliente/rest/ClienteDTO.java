package br.net.dac.cliente.rest;

//import br.net.dac.cliente.model.Endereco;

public class ClienteDTO {
	private Long id;	
	private String nome;	
	private String cpf;	
	private Double salario;	
	private String email;
	private EnderecoDTO endereco;
	private Long endereco;
	private String telefone;
	private StatusConta status;
	

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

	public StatusConta getStatus() {
		return status;
	}

	public void setStatus(StatusConta status) {
		this.status = status;
	}

	public EnderecoDTO getEndereco() {
	public Long getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
	public void setEndereco(Long endereco) {
		this.endereco = endereco;
	}
	
	
	
	
}
