package br.com.wandeir.apicontroll.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wandeir.apicontroll.service.ServicoService;

@RestController
@RequestMapping("/api/servico")
public class ServicoController {
	
	private final Logger LOG = LoggerFactory.getLogger(ChatMessagenController.class);
	
	private final ServicoService servicoService;
	
	public ServicoController(ServicoService servicoService) {
		this.servicoService = servicoService;
	}

	@GetMapping("/")
	public ResponseEntity<Object> recuperaTodosServicos() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicoService.findAll());
		} catch (Exception e) {
			LOG.error("Erro ao recuperaTodosServicos ", e);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Ocorreu um erro inesperado" );
	}
	
	@GetMapping("/tiposervico")
	public ResponseEntity<Object> recuperaTodosTiposServicos() {
		try {			
			return ResponseEntity.status(HttpStatus.OK).body(servicoService.findAllTipos());
		} catch (Exception e) {
			LOG.error("Erro ao recuperaTodosTiposServicos ", e);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Ocorreu um erro inesperado" );
	}
	
	@GetMapping("/tiposervico/{id}")
	public ResponseEntity<Object> recuperaTodosServicosPorTipo(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicoService.findAllByTipo(id));
		} catch (Exception e) {
			LOG.error("Erro ao recuperaTodosServicosPorTipo ", e);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Ocorreu um erro inesperado" );
	}

}
