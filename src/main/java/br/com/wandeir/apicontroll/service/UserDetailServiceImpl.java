package br.com.wandeir.apicontroll.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.wandeir.apicontroll.data.UserDetailsData;
import br.com.wandeir.apicontroll.model.UserModel;
import br.com.wandeir.apicontroll.repository.UserRepository;

@Component
public class UserDetailServiceImpl  implements UserDetailsService{

	private final UserRepository repository;
	
	public UserDetailServiceImpl(UserRepository repository) {
		this.repository = repository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserModel> user = repository.findByLogin(username);
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("Usuário ["+username+"] não encontrado");
		}
		return new UserDetailsData(user);
	}
}