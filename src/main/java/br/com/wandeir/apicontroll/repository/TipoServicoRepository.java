package br.com.wandeir.apicontroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wandeir.apicontroll.model.TipoServico;

@Repository
public interface TipoServicoRepository extends JpaRepository<TipoServico, Long> {

}
