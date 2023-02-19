package br.com.wandeir.apicontroll.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wandeir.apicontroll.dto.EnderecoDTO;
import br.com.wandeir.apicontroll.dto.PessoaDTO;
import br.com.wandeir.apicontroll.model.Endereco;
import br.com.wandeir.apicontroll.model.Pessoa;
import br.com.wandeir.apicontroll.model.PrestadorServico;
import br.com.wandeir.apicontroll.model.Servico;
import br.com.wandeir.apicontroll.service.PessoaService;
import br.com.wandeir.apicontroll.service.ServicoService;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
	
	private final PessoaService pessoaService;
	
	private final ServicoService servicoService;
	
	private final Logger LOG = LoggerFactory.getLogger(PessoaController.class);
	
	public PessoaController(PessoaService pessoaService, ServicoService servicoService) {
		this.pessoaService = pessoaService;
		this.servicoService = servicoService;
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> salvar(@RequestBody PessoaDTO p) {
		try {					
			if(pessoaService.getPessoaByUuid(p.getUuid()).isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Usuário já existe" );
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(new PessoaDTO().buildPessoaDTO(pessoaService.gravarPessoa(p.buildPessoa(null))));
		} catch (Exception e) {
			LOG.error("Erro ao salvar pessoa", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@PostMapping(value = "/atualiza", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> atualizar(@RequestBody PessoaDTO p) {
		try {
			Optional<Pessoa> opPessoa = pessoaService.findById(p.getId());
			if(opPessoa.isPresent()) {
				Pessoa recuperada = opPessoa.get();
				return ResponseEntity.status(HttpStatus.OK).body(new PessoaDTO().buildPessoaDTO(pessoaService.gravarPessoa(p.buildPessoa(recuperada))));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Usuário não encontrado" );
			}
		} catch (Exception e) {
			LOG.error("Erro ao salvar pessoa", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@PostMapping(value = "/endereco", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> salvarEndereco(@RequestBody EnderecoDTO e) {
		try {
			Optional<Pessoa> opPessoa = pessoaService.findById(e.getIdPessoa());
			if(opPessoa.isPresent()) {
				Pessoa recuperada = opPessoa.get();
				if(recuperada.getEnderecos() != null && !recuperada.getEnderecos().isEmpty()) {
					Endereco endRec = recuperada.getEnderecos().get(0);
					Endereco endAux = e.buildEndereco(endRec);
					recuperada.getEnderecos().clear();
					recuperada.getEnderecos().add(endAux);
					pessoaService.gravarPessoa(recuperada);
					return ResponseEntity.status(HttpStatus.CREATED).body(e.buildEnderecoDTO(endAux));
				} else {
					Endereco endAux = e.buildEndereco(null);
					recuperada.setEnderecos(new ArrayList<Endereco>());
					endAux.setPessoa(recuperada);
					recuperada.getEnderecos().add(endAux);
					pessoaService.gravarPessoa(recuperada);
					return ResponseEntity.status(HttpStatus.CREATED).body(e.buildEnderecoDTO(endAux));
				}
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Endereço não encontrado" );
			}
		} catch (Exception ex) {
			LOG.error("Erro ao salvar pessoa", ex);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@GetMapping(value = "/endereco/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> recuperarEndereco(@PathVariable Long id) {
		try {
			Optional<Pessoa> opPessoa = pessoaService.findById(id);
			if(opPessoa.isPresent()) {
				Pessoa recuperada = opPessoa.get();
				if(recuperada.getEnderecos() != null && !recuperada.getEnderecos().isEmpty()) {
					Endereco endRec = recuperada.getEnderecos().get(0);
					return ResponseEntity.status(HttpStatus.OK).body(new EnderecoDTO().buildEnderecoDTO(endRec));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(new EnderecoDTO());
				}
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Endereço não encontrado" );
			}
		} catch (Exception ex) {
			LOG.error("Erro ao salvar pessoa", ex);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getPessoa(@PathVariable Long id) {
		try {
			Optional<Pessoa> op = pessoaService.findById(id);
			if(op.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(new PessoaDTO().buildPessoaDTO(op.get()));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Pessoa não encontrada" );
		} catch (Exception e) {
			LOG.error("Erro ao salvar pessoa", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@GetMapping("/uuid/{id}")
	public ResponseEntity<Object> getPessoaPorUuid(@PathVariable String id) {
		try {
			Optional<Pessoa> op = pessoaService.getPessoaByUuid(id);
			if(op.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(new PessoaDTO().buildPessoaDTO(op.get()));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Pessoa não encontrada" );
		} catch (Exception e) {
			LOG.error("Erro ao salvar pessoa", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@GetMapping("/{id}/servicos")
	public ResponseEntity<Object> getServiosPrestados(@PathVariable Long id) {
		try {
			List<PrestadorServico> retorno = pessoaService.getServicosPorPessoa(id);
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
		} catch (Exception e) {
			LOG.error("Erro ao recuperar serviços prestados por pessoa", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}
	
	@PatchMapping("/servicos/{id}/{idServico}")
	public ResponseEntity<Object> adcionaServicos(@PathVariable Long id,  @PathVariable Long idServico) {
		try {
			Optional<Pessoa> op = pessoaService.findById(id);
			Servico opServico = servicoService.findById(idServico);
			if(op.isPresent() && opServico != null) {
				Pessoa pessoa = op.get();
				PrestadorServico ps = new PrestadorServico();
				ps.setPrestadorServico(pessoa);
				ps.setServicoPrestado(opServico);
				return ResponseEntity.status(HttpStatus.OK).body(pessoaService.gravarPrestadorServico(ps));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: Pessoa ou Servço não encontrado" );
		} catch (Exception e) {
			LOG.error("Erro ao adicionar prestador serviço ", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Ocorreu um erro inesperado" );
	}

}
