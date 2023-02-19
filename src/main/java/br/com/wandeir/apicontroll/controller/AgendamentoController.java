package br.com.wandeir.apicontroll.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import br.com.wandeir.apicontroll.model.Agendamento;
import br.com.wandeir.apicontroll.model.Chat;
import br.com.wandeir.apicontroll.model.Pessoa;
import br.com.wandeir.apicontroll.service.AgendamentoService;
import br.com.wandeir.apicontroll.service.PessoaService;
import br.com.wandeir.apicontroll.service.ServicoService;

@RestController
@RequestMapping("/api/agendamento")
public class AgendamentoController {
	
	private final Logger LOG = LoggerFactory.getLogger(AgendamentoController.class);
	
	private final AgendamentoService agendamentoService;
	
	private final PessoaService pessoaService;
	
	private final ServicoService servicoService;
	
	public AgendamentoController(AgendamentoService agendamentoService, PessoaService pessoaService,ServicoService servicoService) {
		this.agendamentoService = agendamentoService;
		this.pessoaService = pessoaService;
		this.servicoService = servicoService;
	}

	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody Agendamento agendamento) {
		try {
			agendamento.setCliente(pessoaService.findById(agendamento.getCliente().getId()).get());
			agendamento.setPrestador(pessoaService.findById(agendamento.getPrestador().getId()).get());
			agendamento.setServico(servicoService.findById(agendamento.getId()));
			agendamento.setChat(new Chat());
			agendamento.setStatus("Ativo");
			agendamento.setCreatedAt(new Date());
			return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoService.gravarAgendamento(agendamento));
		} catch (Exception e) {
			LOG.error("Erro ao salvar Agendamentos ", e);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Ocorreu um erro inesperado" );
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getAgendamento(@PathVariable Long id) {
		try {
			Optional<Agendamento> op = agendamentoService.recuperaAgendamentoPorId(id);
			if(op.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(op.get());
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Agendamento não encontrada" );
		} catch (Exception e) {
			LOG.error("Erro recuperar agendamentos por id ", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@GetMapping("/{id}/chat")
	public ResponseEntity<Object> getChat(@PathVariable Long id) {
		try {
			Optional<Agendamento> op = agendamentoService.recuperaAgendamentoPorId(id);
			if(op.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(op.get().getChat());
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Agendamento não encontrada" );
		} catch (Exception e) {
			LOG.error("Erro ao recuperar chat do agendamento ", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@GetMapping("/cliente/{id}/{data}")
	public ResponseEntity<Object> getAgendamentosCliente(@PathVariable Long id, @PathVariable Date data) {
		try {
			Optional<Pessoa> pessoa = pessoaService.findById(id);
			if (pessoa.isPresent()) {
				List<Agendamento> op = agendamentoService.recuperaAgendamentoPorDataCliente(data, pessoa.get());
				if(!op.isEmpty()) {
					return ResponseEntity.status(HttpStatus.OK).body(op);
				}
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Agendamentos não encontrados" );
		} catch (Exception e) {
			LOG.error("Erro ao recuperar agendamentos por cliente e data", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@GetMapping("/prestador/{id}/{data}")
	public ResponseEntity<Object> getAgendamentosPrestador(@PathVariable Long id, @PathVariable Date data) {
		try {
			Optional<Pessoa> pessoa = pessoaService.findById(id);
			if (pessoa.isPresent()) {
				List<Agendamento> op = agendamentoService.recuperaAgendamentoPorDataPrestador(data, pessoa.get());
				if(!op.isEmpty()) {
					return ResponseEntity.status(HttpStatus.OK).body(op);
				}
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Agendamentos não encontrados" );
		} catch (Exception e) {
			LOG.error("Erro ao recuperar agendamentos por prestador e data", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@PatchMapping("/{id}/cancelar")
	public ResponseEntity<Object> cancelar(@PathVariable Long id) {
		try {
			Optional<Agendamento> op = agendamentoService.recuperaAgendamentoPorId(id);
			if(op.isPresent()) {
				Agendamento agendamento = op.get();
				agendamento.setStatus("Cancelado");
				agendamento.setUpdatedAt(new Date());
				return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.gravarAgendamento(agendamento));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Agendamento não encontrado" );
		} catch (Exception e) {
			LOG.error("Erro ao cancelar Agendamentos ", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@PatchMapping("/{id}/finalizar")
	public ResponseEntity<Object> finalizar(@PathVariable Long id) {
		try {
			Optional<Agendamento> op = agendamentoService.recuperaAgendamentoPorId(id);
			if(op.isPresent()) {
				Agendamento agendamento = op.get();
				agendamento.setStatus("Finalizado");
				agendamento.setUpdatedAt(new Date());
				return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.gravarAgendamento(agendamento));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Agendamento não encontrado" );
		} catch (Exception e) {
			LOG.error("Erro ao finalizar Agendamentos ", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
}
