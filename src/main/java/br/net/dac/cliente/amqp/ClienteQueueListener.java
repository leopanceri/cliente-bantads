package br.net.dac.cliente.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.net.dac.cliente.rest.ClienteDTO;
import br.net.dac.cliente.service.ClienteService;

@Component
public class ClienteQueueListener {
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private RabbitTemplate template;

	@RabbitListener(queues="clientes.v1.cliente-novo")
	public void recebeCliente (ClienteTransfer clienteTransfer) {
		ClienteDTO c = new ClienteDTO();
		ClienteTransfer ct = new ClienteTransfer();
		if(clienteTransfer.getMessage().equals("CRIAR")) {
			c= clienteService.createClient(clienteTransfer.getClienteDto());
			ct.setClienteDto(c);
			ct.setMessage("CRIADO");
		}
		if(clienteTransfer.getMessage().equals("ATUALIZAR")) {
			c= clienteService.updateCliente(clienteTransfer.getClienteDto().getId(),
					clienteTransfer.getClienteDto());
			ct.setClienteDto(c);
			ct.setMessage("ATUALIZADO");
		}
		template.convertAndSend("cliente-criado",ct);
	}
}
