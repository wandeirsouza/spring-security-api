package br.com.wandeir.apicontroll.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wandeir.apicontroll.model.Mensagem;
import br.com.wandeir.apicontroll.service.MensagemService;

@RestController
@RequestMapping("/api/chat")
public class ChatMessagenController {
	
	private final Logger LOG = LoggerFactory.getLogger(ChatMessagenController.class);
	
	private final MensagemService mensagemService;
	
	public ChatMessagenController(MensagemService mensagemService) {
		this.mensagemService = mensagemService;
	}

	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody Mensagem m) {
		try {
			m.setCreatedAt(new Date());
			return ResponseEntity.status(HttpStatus.CREATED).body(mensagemService.gravarMensagem(m));
		} catch (Exception e) {
			LOG.error("Erro ao salvar mensagem ", e);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Ocorreu um erro inesperado" );
	}
	
	@PatchMapping("/mensagem/{id}")
	public ResponseEntity<Object> updateVisualizada(@PathVariable Long id) {
		try {
			Mensagem m = mensagemService.findById(id);
			if(m != null) {
				m.setVisualizadaAt(new Date());
				return ResponseEntity.status(HttpStatus.OK).body(mensagemService.gravarMensagem(m));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Mensagem não encontrada." );
		} catch (Exception e) {
			LOG.error("Erro ao updateVisualizada ", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@GetMapping("/{id}/mensagens")
	public ResponseEntity<Object> recuperaMensagemPorChat(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(mensagemService.getMensagensPorChat(id));
		} catch (Exception e) {
			LOG.error("Erro ao recuperaMensagemPorChat ", e);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Ocorreu um erro inesperado" );
	}

}
