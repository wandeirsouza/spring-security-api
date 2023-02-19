package br.com.wandeir.apicontroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wandeir.apicontroll.model.PrestadorServico;

@Repository
public interface PrestadorServicoRepository extends JpaRepository<PrestadorServico, Long> {

}
