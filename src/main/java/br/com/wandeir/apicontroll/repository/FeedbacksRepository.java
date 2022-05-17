package br.com.wandeir.apicontroll.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wandeir.apicontroll.model.FeedbackModel;

@Repository
public interface FeedbacksRepository extends JpaRepository<FeedbackModel, UUID> {
	
	public Optional<FeedbackModel> findByType(String type);
}
