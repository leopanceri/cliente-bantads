package br.net.dac.cliente.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import br.net.dac.cliente.model.ClienteTransfer;

public class ClienteProducer {
	
	@Autowired
	private RabbitTemplate template;

	public static final String FILA_CLIENTE_CRUD = "FILA_CLIENTE_CRUD";
	public static final String FILA_CLIENTE_RESPOSTA = "FILA_CLIENTE_RESPOSTA";
	public static final String FILA_ATUALIZA_STATUS ="FILA_ATUALIZA_STATUS";
	
	
	public void enviaRespostaCliente(ClienteTransfer clienteTransfer) {
		template.convertAndSend(FILA_CLIENTE_RESPOSTA, clienteTransfer);
	}
}


