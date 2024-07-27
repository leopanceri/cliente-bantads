package br.net.dac.cliente.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.net.dac.cliente.model.Cliente;
import br.net.dac.cliente.model.Endereco;
import br.net.dac.cliente.repository.ClienteRepository;
import br.net.dac.cliente.repository.EnderecoRepository;
import br.net.dac.cliente.rest.ClienteDTO;
import br.net.dac.cliente.rest.StatusConta;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repoCliente;
	@Autowired
	private EnderecoRepository repoEndereco;
	@Autowired
	private ModelMapper mapperCliente, mapperEndereco;

	public List<ClienteDTO> selectAllClients() {
		List<Cliente> lista= repoCliente.findAll();
		return lista.stream().map(e -> mapperCliente.map(e, ClienteDTO.class)).collect(Collectors.toList());
	}

	public ClienteDTO selectByCpf(String cpf) {
		Cliente c = repoCliente.findByCpf(cpf);
		return mapperCliente.map(c, ClienteDTO.class);
	}

	public ClienteDTO selectById(long id) {
		Cliente c = repoCliente.findById(id).orElseThrow(()-> new EntityNotFoundException());
		return mapperCliente.map(c, ClienteDTO.class);
	}

	public ClienteDTO updateCliente(long id, ClienteDTO dto) {
		Endereco e = mapperEndereco.map(dto.getEndereco(), Endereco.class);
		repoEndereco.save(e);
		Cliente cliente = mapperCliente.map(dto, Cliente.class);
		cliente = repoCliente.save(cliente);
		return mapperCliente.map(cliente, ClienteDTO.class);
	}

	public void deleteCliente (long clienteId) {
        repoCliente.deleteById(clienteId);
    }


	public ClienteDTO createClient(ClienteDTO newcliente) {
		try {
			if(repoCliente.existsByCpf(newcliente.getCpf())){
				throw new RuntimeException("CPF já cadastrado no sistema");
			}
			Endereco end = mapperEndereco.map(newcliente.getEndereco(), Endereco.class);
			repoEndereco.save(end);
			newcliente.setStatus(StatusConta.PENDENTE);
			Cliente cliente = mapperCliente.map(newcliente, Cliente.class);
			cliente.setEndereco(end);
			repoCliente.save(cliente);
			return mapperCliente.map(cliente, ClienteDTO.class);
		}catch (Exception e) {
			throw new RuntimeException("Falha ao salvar novo cliente: " + e.getMessage());

		}
	}

}
