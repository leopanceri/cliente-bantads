package br.net.dac.cliente.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.dac.cliente.model.Cliente;
import br.net.dac.cliente.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;

@CrossOrigin
@RestController
public class ClienteREST {
	@Autowired
	private ClienteRepository repoCliente;
	@Autowired
	private ModelMapper mapperCliente;
	
	public static List<Cliente> listaCliente = new ArrayList<>();
	
	
	@PostMapping("/cadastro")
	public ClienteDTO inserirCliente(@RequestBody ClienteDTO cliente) {
		cliente.setStatus(StatusConta.ANALISE);
		repoCliente.save(mapperCliente.map(cliente, Cliente.class));
		Cliente c = repoCliente.findByEmail(cliente.getEmail());
		return mapperCliente.map(c, ClienteDTO.class);
	}
	
	@GetMapping("/clientes")
	public List<ClienteDTO> obterTodosClientes(){
		List<Cliente> lista= repoCliente.findAll();
		
		return lista.stream().map(e -> mapperCliente.map(e, ClienteDTO.class)).collect(Collectors.toList());
	}
	
	@GetMapping("/clientes/{id}")
	public ClienteDTO obterClienteId(@PathVariable("id") long id){
		Cliente c = repoCliente.findById(id).orElseThrow(()-> new EntityNotFoundException());
		return mapperCliente.map(c, ClienteDTO.class);
	}
	
	
	@PutMapping("/clientes/{id}")
	public ClienteDTO alteraCadastro(@PathVariable("id") long id, @RequestBody ClienteDTO dto) {
		Cliente cliente = mapperCliente.map(dto, Cliente.class);
		cliente.setId(id);
		cliente = repoCliente.save(cliente);
		return mapperCliente.map(cliente, ClienteDTO.class);
	}
	
	@DeleteMapping("/clientes/{id}")
	public void removerCliente(@PathVariable("id")long id) {
		repoCliente.deleteById(id);
	}
	
}
