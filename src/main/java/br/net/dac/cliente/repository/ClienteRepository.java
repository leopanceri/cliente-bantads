package br.net.dac.cliente.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.net.dac.cliente.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public Cliente findByEmail(String email);
	public List<Cliente> findByCpfContaining(String cpf);
	public boolean existsByCpf(String cpf);
	
	public List<Cliente> findByStatus(String Status);

	@Query(value = "update cliente set status = ?1 set statusSet = ?2 set motivo = ?3 where id = ?4", nativeQuery=true)
	@Modifying
	public void updateClienteStatus(String status, LocalDateTime dataHora, String motivo, long id);
	
	/*
	 * 
	 @Query(value="update conta set id_gerente = ?1  where id_conta = ?2",nativeQuery=true)
     @Modifying
     public void updateGerenteConta(Long id_gerente, Long id);
     
	 */
}
