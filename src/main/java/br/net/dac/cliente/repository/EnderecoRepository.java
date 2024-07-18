package br.net.dac.cliente.repository;


//import java.util.Optional;

import org.springframework.data.jpa.repository.*;
//import org.springframework.data.repository.query.Param;

import br.net.dac.cliente.model.*;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>  {
	
}
