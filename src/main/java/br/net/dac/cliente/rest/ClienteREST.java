package br.net.dac.cliente.rest;


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

import br.net.dac.cliente.model.StatusConta;
import br.net.dac.cliente.producer.ClienteProducer;
import br.net.dac.cliente.service.ClienteService;

@CrossOrigin
@RestController
public class ClienteREST {


	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteProducer clienteProducer;

	
	@GetMapping("/clientes")
	public ResponseEntity<?> obterTodosClientes(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(clienteService.selectAllClients());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass());
		}	
	}
	
	
	@GetMapping("/gerentes/inicio")
	public ResponseEntity<?> obterCientesPendente(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(clienteService.selectClientesAnalise());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass());
		}
	}
	
	@GetMapping("/gerentes/clientes/{cpf}")
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
	
	
	@PutMapping("/gerentes/clientes/aprovar/{id}")
	public ResponseEntity<?> aprovaCliente( @PathVariable("id")long id) {
		try {
			clienteService.alteraStatus(StatusConta.APROVADO.toString(), null, id);
			return ResponseEntity.status(HttpStatus.OK).body("CADASTRO APROVADO");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}
		
	}
	
	@PutMapping("/gerentes/clientes/rejeitar/{id}")
	public ResponseEntity<?> rejeitaCliente( @PathVariable("id")long id, @RequestBody String motivo) {
		try {
			clienteService.alteraStatus(StatusConta.REJEITADO.toString(), motivo, id);
			return ResponseEntity.status(HttpStatus.OK).body("CADASTRO REJEITADO");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}
	}
	
	@GetMapping("/buscaid/{id}")
	public ResponseEntity<Object> obterClienteid(@PathVariable("id") long id){ 
		try {
			return ResponseEntity.status(HttpStatus.OK).body(clienteService.selectClienteById(id));	
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("CLIENTE NÂO ENCONTRADO - id: " + id);
		}
	}
	


	
}
