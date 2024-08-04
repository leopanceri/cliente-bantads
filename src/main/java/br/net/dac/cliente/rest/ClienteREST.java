package br.net.dac.cliente.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.net.dac.cliente.model.ClienteDTO;
import br.net.dac.cliente.service.ClienteService;

@CrossOrigin
@RestController
public class ClienteREST {


	@Autowired
	private ClienteService clienteService;

	
	@GetMapping("/clientes")
	public ResponseEntity obterTodosClientes(){
		try {
			return clienteService.selectAllClients();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass());
		}	
	}
	
	
	@GetMapping("/gerentes/inicio")
	public ResponseEntity obterCientesAnalise(){
		try {
			return clienteService.selectClientesAnalise();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass());
		}
	}
	
	@GetMapping("/busca/{cpf}")
	public ResponseEntity<Object> obterClienteCpf(@PathVariable("cpf") String cpf){ 
		try {
			return ResponseEntity.status(HttpStatus.OK).body(clienteService.selectByCpf(cpf));	
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("CLIENTE NÂO ENCONTRADO - CPF: " + cpf);
		}
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Object> removerCliente(@PathVariable("id")long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(clienteService.deleteCliente(id));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("CLIENTE NÃO FOI REMOVIDO");
		}
	}

	@PutMapping("/alterastatus/{id}")
	public void alteraStatus( @PathVariable("id")long id, @RequestBody String status) {
		clienteService.alteraStatus(status, id);
	}
	
	@GetMapping("/buscaid/{id}")
	public ResponseEntity<Object> obterClienteid(@PathVariable("id") long id){ 
		try {
			return ResponseEntity.status(HttpStatus.OK).body(clienteService.selectClienteById(id));	
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("CLIENTE NÂO ENCONTRADO - id: " + id);
		}
	}
	
	@GetMapping("/clientes/ids")
	public ResponseEntity<List<ClienteDTO>> obterClientesPorIds(@RequestParam List<Long> ids) {
		try {
			List<ClienteDTO> clientes = clienteService.selectByIds(ids);
			return ResponseEntity.ok(clientes);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	
}
