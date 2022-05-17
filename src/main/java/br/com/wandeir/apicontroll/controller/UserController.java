package br.com.wandeir.apicontroll.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wandeir.apicontroll.model.UserModel;
import br.com.wandeir.apicontroll.repository.UserRepository;

@RestController
@RequestMapping("/api/usuario")
public class UserController {
	
	private final UserRepository repository;
	private final PasswordEncoder encoder;
	
	public UserController(UserRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}
	
	@GetMapping("/listarTodos")
	public ResponseEntity<List<UserModel>> listartodos() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody UserModel usuario) {
		if(repository.findByLogin(usuario.getLogin()).isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Usuário já existe" );
		}
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}


}
