package br.com.wandeir.apicontroll.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wandeir.apicontroll.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
	
	public Optional<UserModel> findByLogin(String login);

}
