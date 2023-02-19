package br.com.wandeir.apicontroll.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.wandeir.apicontroll.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	public Optional<Pessoa> findByUuid(String uuid);
	
	@Query(value = "select nl.id, nl.uuid from (select *,  (6371 * "
			+ "        acos( "
			+ "            cos(radians(?1)) * "
			+ "            cos(radians(p.lat)) * "
			+ "            cos(radians(?2) - radians(p.lng)) + "
			+ "            sin(radians(?1)) * "
			+ "            sin(radians(p.lat)) "
			+ "        )) as distancia FROM pessoa p ) as nl where distancia <= 5",
			nativeQuery = true)
	List<Pessoa> findUserByStatusAndName(Double lat, Double lng);
}
