package br.net.dac.cliente.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.net.dac.cliente.model.ClienteDTO;
import br.net.dac.cliente.model.StatusConta;
import br.net.dac.cliente.service.ClienteService;

@Component
public class ClienteQueueListener {
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private RabbitTemplate template;

	public static final String FILA_CLIENTE_CRUD = "FILA_CLIENTE_CRUD";
	public static final String FILA_CLIENTE_RESPOSTA = "FILA_CLIENTE_RESPOSTA";
	public static final String FILA_ATUALIZA_STATUS ="FILA_ATUALIZA_STATUS";

	@RabbitListener(queues=FILA_CLIENTE_CRUD)
	public void recebeCliente (@Payload ClienteTransfer clienteTransfer) {
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
			if(clienteTransfer.getMessage().equals("REMOVER")) {
				clienteService.deleteCliente(c.getId());
				clienteTransfer.setMessage("REMOVIDO");
			}
			template.convertAndSend(FILA_CLIENTE_RESPOSTA,clienteTransfer);
			
		} catch(Exception e) {
			clienteTransfer.setMessage("FALHA");
			template.convertAndSend(FILA_CLIENTE_RESPOSTA, clienteTransfer );
		}			
	}
	
	@RabbitListener(queues=FILA_ATUALIZA_STATUS)
	public void recebeCliente (@Payload ClienteDTO clienteDto) {
		try {
			switch (clienteDto.getStatus()) {
			case APROVADO:
				//gerar senha , enviar email e alterar conta para ativa
				break;
			case REJEITADO:
				//enviar email 
				break;				
			}
			clienteService.updateCliente(clienteDto.getId(), clienteDto);
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
}
