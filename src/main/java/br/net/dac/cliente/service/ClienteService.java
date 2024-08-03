package br.net.dac.cliente.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.net.dac.cliente.model.Cliente;
import br.net.dac.cliente.model.ClienteDTO;
import br.net.dac.cliente.model.Endereco;
import br.net.dac.cliente.model.StatusConta;
import br.net.dac.cliente.repository.ClienteRepository;
import br.net.dac.cliente.repository.EnderecoRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repoCliente;
	@Autowired
	private EnderecoRepository repoEndereco;
	@Autowired
	private ModelMapper mapperCliente, mapperEndereco;

	public ResponseEntity<List<ClienteDTO>> selectAllClients() {
		List<Cliente> lista= repoCliente.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(lista.stream().map(e -> mapperCliente.map(e, ClienteDTO.class)).collect(Collectors.toList()));
	}
	
	public ResponseEntity<List<ClienteDTO>> selectClientesAnalise(){
		List<Cliente> lista= repoCliente.findByStatus("PENDENTE");
		return ResponseEntity.status(HttpStatus.OK).body(lista.stream().map(e -> mapperCliente.map(e, ClienteDTO.class)).collect(Collectors.toList()));
	}
	
	public ClienteDTO selectByCpf(String cpf) {
			Cliente c = repoCliente.findByCpf(cpf);
			return mapperCliente.map(c, ClienteDTO.class);
	}

	public ClienteDTO selectClienteById(long id) {
			Optional<Cliente> c = repoCliente.findById(id);
			return mapperCliente.map(c, ClienteDTO.class);
	}

	public String deleteCliente (long clienteId) {
			Long enderecoId = selectClienteById(clienteId).getEndereco().getId();
			repoCliente.deleteById(clienteId);
			repoEndereco.deleteById(enderecoId);	
			return "CLIENTE REMOVIDO";
    }
	
	public void alteraStatus(String status, long id) {
		ClienteDTO c = selectClienteById(id);
		c.setStatus(StatusConta.valueOf(status));
	}


	public ClienteDTO createClient(ClienteDTO newcliente){
		newcliente.setStatus(StatusConta.PENDENTE);
		Endereco end = mapperEndereco.map(newcliente.getEndereco(), Endereco.class);
		Cliente cliente = mapperCliente.map(newcliente, Cliente.class);
		try {
			repoEndereco.save(end);
			cliente.setEndereco(end);
			repoCliente.save(cliente);
			return mapperCliente.map(cliente, ClienteDTO.class);
		}catch (Exception e) {
			repoEndereco.delete(end);
			throw new RuntimeException("Falha ao cadastrar novo cliente: " + e.getMessage());
		}
	}
	
	public ClienteDTO updateCliente(long id, ClienteDTO dto) {
		try {
			Endereco e = mapperEndereco.map(dto.getEndereco(), Endereco.class);
			repoEndereco.save(e);
			Cliente cliente = repoCliente.findById(id).get();
			dto.setStatus(StatusConta.valueOf(cliente.getStatus()));
			dto.setStatusSet(cliente.getStatusSet());
			dto.setMotivo(cliente.getMotivo());
			cliente = mapperCliente.map(dto, Cliente.class);
			repoCliente.save(cliente);
			return mapperCliente.map(cliente, ClienteDTO.class);
		}catch (Exception e) {
			throw new RuntimeException("Falha ao salvar novo cliente: " + e.getMessage());
		}	
	}

}