package br.net.dac.cliente.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.net.dac.cliente.rest.ClienteDTO;
import br.net.dac.cliente.service.ClienteService;

@Component
public class ClienteQueueListener {
	@Autowired
	private ClienteService clienteService;

	@RabbitListener(queues="clientes.v1.cliente-novo")
	public void onClientCreated(ClienteDTO clienteDto) {
		System.out.println("Id recebido " + clienteDto.getNome());
        System.out.println("Valor recebido " + clienteDto.getEmail());
        clienteService.createClient(clienteDto);
	}
}
