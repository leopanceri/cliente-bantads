package br.net.dac.cliente.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.net.dac.cliente.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public Cliente findByEmail(String email);
	public Cliente findByCpf(String cpf);
	public List<Cliente> findByCpfContaining(String cpf);
	public boolean existsByCpf(String cpf);
	
	public List<Cliente> findByStatus(String Status);
	
	@Transactional
	@Modifying
	@Query("UPDATE Cliente c SET c.status = :status, c.statusSet = :dataHora, c.motivo = :motivo WHERE c.id = :id")
	public void updateClienteStatus(@Param("status") String status, @Param("dataHora") LocalDateTime dataHora, 
			@Param("motivo") String motivo, @Param("id")long id);
	
}
