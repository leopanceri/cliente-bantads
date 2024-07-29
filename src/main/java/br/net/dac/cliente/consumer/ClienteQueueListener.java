package br.net.dac.cliente.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.net.dac.cliente.model.ClienteDTO;
import br.net.dac.cliente.service.ClienteService;

@Component
public class ClienteQueueListener {
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private RabbitTemplate template;

	

	@RabbitListener(queues="cliente-crud")
	public ClienteTransfer recebeCliente (@Payload ClienteTransfer clienteTransfer) {
		ClienteDTO c = new ClienteDTO();
		try {
			if(clienteTransfer.getMessage().equals("CRIAR")) {
				c= clienteService.createClient(clienteTransfer.getClienteDto());
				clienteTransfer.setClienteDto(c);
				clienteTransfer.setMessage("CRIADO");
			}
			if(clienteTransfer.getMessage().equals("ATUALIZAR")) {
				c= clienteService.updateCliente(clienteTransfer.getClienteDto().getId(),
						clienteTransfer.getClienteDto());
				clienteTransfer.setClienteDto(c);
				clienteTransfer.setMessage("ATUALIZADO");
			}
			template.convertAndSend("cliente-resposta",clienteTransfer);
			return clienteTransfer;
		} catch(Exception e) {
			clienteTransfer.setClienteDto(null);
			clienteTransfer.setMessage(e.getMessage());
			return clienteTransfer;
		}
	}
	
}
