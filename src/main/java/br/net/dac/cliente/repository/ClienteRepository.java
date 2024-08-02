package br.net.dac.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.dac.cliente.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public Cliente findByEmail(String email);
	public Cliente findByCpf(String cpf);
	public boolean existsByCpf(String cpf);
	
	public List<Cliente> findByStatus(String Status);


}
