package br.net.dac.cliente.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.dac.cliente.service.ClienteService;

@CrossOrigin
@RestController
public class ClienteREST {
	
	
	@Autowired
	private ClienteService clienteService;	
	
	@PostMapping("/cadastro")
	public ResponseEntity<ClienteDTO> inserirCliente(@RequestBody ClienteDTO newcliente) {
		return ResponseEntity.ok(clienteService.createClient(newcliente));
	}
	
	@GetMapping("/clientes")
	public ResponseEntity <List<ClienteDTO>> obterTodosClientes(){
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.selectAllClients());
	}
	
	@GetMapping("/busca/{cpf}")
	public ResponseEntity<ClienteDTO> obterClienteCpf(@PathVariable("cpf") String cpf){
		return ResponseEntity.ok(clienteService.selectByCpf(cpf));
	}
	
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<ClienteDTO> obterClienteId(@PathVariable("id") long id){
		return ResponseEntity.ok(clienteService.selectById(id));
	}
	
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity <ClienteDTO> alteraCadastro(@PathVariable("id") long id, @RequestBody ClienteDTO dto) {
		return ResponseEntity.ok(clienteService.updateCliente(id, dto));
	}
	
	@DeleteMapping("/clientes/{id}")
	public void removerCliente(@PathVariable("id")long id) {
		clienteService.deleteCliente(id);
	}
	
}
