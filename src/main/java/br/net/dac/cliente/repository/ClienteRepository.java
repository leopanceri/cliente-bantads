package br.net.dac.cliente.repository;

import br.net.dac.cliente.model.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public Cliente findByEmail(String email);

	//public Cliente findByCpf(String cpf);
	
	@Query(value = "SELECT * FROM CLIENTE WHERE CPF = :cpf", nativeQuery = true)
	Optional<Cliente> findByCpf(@Param("cpf") String cpf);
	
}
