package br.net.dac.cliente.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.dac.cliente.model.ClienteDTO;
import br.net.dac.cliente.service.ClienteService;

@CrossOrigin
@RestController
public class ClienteREST {


	@Autowired
	private ClienteService clienteService;

	
	@GetMapping("/clientes")
	public ResponseEntity  obterTodosClientes(){
		try {
			return clienteService.selectAllClients();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass());
		}
		
	}

	@GetMapping("/busca/{cpf}")
	public ResponseEntity<Object> obterClienteCpf(@PathVariable("cpf") String cpf){
		try {
			return ResponseEntity.ok(clienteService.selectByCpf(cpf));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getClass());
		}	
	}


	@GetMapping("/clientes/{id}")
	public ResponseEntity obterClienteId(@PathVariable("id") long id){
		try {
			return clienteService.selectById(id);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getClass());
		}
		
	}

	
	/*
	@PostMapping("/cadastro")
	public ResponseEntity<HttpStatus> inserirCliente(@RequestBody ClienteDTO newcliente) {
		clienteService.createClient(newcliente);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity <ClienteDTO> alteraCadastro(@PathVariable("id") long id, @RequestBody ClienteDTO dto) {
		return ResponseEntity.ok(clienteService.updateCliente(id, dto));
	}

	@DeleteMapping("/clientes/{id}")
	public void removerCliente(@PathVariable("id")long id) {
		clienteService.deleteCliente(id);
	}
	*/
}
