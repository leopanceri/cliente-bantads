package br.net.dac.cliente.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import jakarta.persistence.EntityNotFoundException;

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

	public ResponseEntity<ClienteDTO> selectByCpf(String cpf) {
		Cliente c = repoCliente.findByCpf(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(mapperCliente.map(c, ClienteDTO.class));
	}

	public ResponseEntity<ClienteDTO> selectById(long id) {
		Optional<Cliente> c = repoCliente.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(mapperCliente.map(c, ClienteDTO.class));
	}

	public void deleteCliente (long clienteId) {
        repoCliente.deleteById(clienteId);
    }


	public ClienteDTO createClient(ClienteDTO newcliente) {
		Endereco end = mapperEndereco.map(newcliente.getEndereco(), Endereco.class);
		Cliente cliente = mapperCliente.map(newcliente, Cliente.class);
		try {
			repoEndereco.save(end);
			newcliente.setStatus(StatusConta.PENDENTE);
			cliente.setEndereco(end);
			repoCliente.save(cliente);
			return mapperCliente.map(cliente, ClienteDTO.class);
		}catch (Exception e) {
			repoEndereco.delete(end);			
			throw new RuntimeException("Falha ao salvar novo cliente: " + e.getMessage());
		}
	}
	
	public ClienteDTO updateCliente(long id, ClienteDTO dto) {
		try {
			Endereco e = mapperEndereco.map(dto.getEndereco(), Endereco.class);
			repoEndereco.save(e);
			Cliente cliente = mapperCliente.map(dto, Cliente.class);
			cliente = repoCliente.save(cliente);
			return mapperCliente.map(cliente, ClienteDTO.class);
		}catch (Exception e) {
			throw new RuntimeException("Falha ao salvar novo cliente: " + e.getMessage());
		}	
	}

}
