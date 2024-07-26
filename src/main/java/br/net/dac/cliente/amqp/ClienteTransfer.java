package br.net.dac.cliente.amqp;

import br.net.dac.cliente.rest.ClienteDTO;

public class ClienteTransfer {
	
	private ClienteDTO clienteDto;
	private String action;
	private String message;
	
	public ClienteTransfer() {
		
	}

	public ClienteTransfer(ClienteDTO clienteDto, String action, String message) {
		super();
		this.clienteDto = clienteDto;
		this.action = action;
		this.message = message;
	}

	public ClienteDTO getClienteDto() {
		return clienteDto;
	}

	public void setClienteDto(ClienteDTO clienteDto) {
		this.clienteDto = clienteDto;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
