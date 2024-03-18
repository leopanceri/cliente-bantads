package br.net.dac.cliente.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.dac.cliente.model.Cliente;

@CrossOrigin
@RestController
public class ClienteREST {
	public static List<Cliente> listaCliente = new ArrayList<>();
	
	@GetMapping("/clientes")
	public List<Cliente> obterTodosClientes(){
		return listaCliente;
	}
	
	@GetMapping("/clientes/{id}")
	public Cliente obterClienteId(@PathVariable("id") int id){
		Cliente c = listaCliente.stream().filter(clt -> clt.getId() == id).findAny().orElse(null);
		return c;
	}
	
	@PostMapping("/clientes")
	public Cliente inserirCliente(@RequestBody Cliente cliente) {
		Cliente c = listaCliente.stream().max(Comparator.comparing(Cliente::getId)).orElse(null);
		if(c==null)
			cliente.setId(1);
		else
			cliente.setId(c.getId()+1);
		listaCliente.add(cliente);
		return cliente;
	}
	
	@PutMapping("/clientes/{id}")
	public Cliente alteraCadastro(@PathVariable("id") int id, @RequestBody Cliente cliente) {
		Cliente c = listaCliente.stream().filter(clt -> clt.getId() == id).findAny().orElse(null);
		if(c != null) {
			c.setNome(cliente.getNome());
			c.setSalario(cliente.getSalario());
			c.setEmail(cliente.getEmail());
			c.setSenha(cliente.getSenha());
			c.setCep(cliente.getCep());
			c.setEndereco(cliente.getEndereco());
		}
		return c;
	}
	
	@DeleteMapping("/clientes/{id}")
	public Cliente removerCliente(@PathVariable("id")int id) {
		Cliente cliente = listaCliente.stream().filter(clt -> clt.getId() == id).findAny().orElse(null);
		if (cliente != null)
			listaCliente.removeIf(c -> c.getId() == id);
		return cliente;
	}
	
	
	static {
		listaCliente.add(new Cliente(1, "Leonardo", "11111111111", 7800.00, "leo@leo", "leo", "88888888", "Rua rua 123"));
		listaCliente.add(new Cliente(2, "Ricardo", "22222222222", 12700.00, "ric@ric", "ric", "88888888", "Rua rua 133"));
		listaCliente.add(new Cliente(3, "Nicolas", "33333333333", 10000.00, "nic@nic", "nic", "88888888", "Rua rua 160"));
	}
}
