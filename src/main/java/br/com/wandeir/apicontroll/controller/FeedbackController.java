package br.com.wandeir.apicontroll.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wandeir.apicontroll.model.FeedbackModel;
import br.com.wandeir.apicontroll.repository.FeedbacksRepository;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
	
	private final FeedbacksRepository feedbackRepository;


	public FeedbackController(FeedbacksRepository feedbackRepository) {
		this.feedbackRepository = feedbackRepository;
	}


	@GetMapping(path = "/listarTodos", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FeedbackModel>> listarTodos() {
		List<FeedbackModel> lista = feedbackRepository.findAll();
		return ResponseEntity.ok(lista);		
	}

}
