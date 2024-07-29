package br.net.dac.cliente.consumer;

import java.io.Serializable;

import br.net.dac.cliente.model.ClienteDTO;

public class ClienteTransfer implements Serializable {

	private ClienteDTO clienteDto;
	private String message;

	public ClienteTransfer() {

	}

	public ClienteTransfer(ClienteDTO clienteDto, String message) {
		this.clienteDto = clienteDto;
		this.message = message;
	}

	public ClienteDTO getClienteDto() {
		return clienteDto;
	}

	public void setClienteDto(ClienteDTO clienteDto) {
		this.clienteDto = clienteDto;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
