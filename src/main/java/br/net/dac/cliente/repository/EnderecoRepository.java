package br.net.dac.cliente.repository;


//import java.util.Optional;

//import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import br.net.dac.cliente.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>  {

}
