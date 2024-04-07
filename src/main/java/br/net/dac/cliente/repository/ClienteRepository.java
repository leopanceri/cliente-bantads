package br.net.dac.cliente.repository;

import br.net.dac.cliente.model.*;
import org.springframework.data.jpa.repository.*;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public Cliente findByEmail(String email);

	public Cliente findByCpf(String cpf);
	
}
