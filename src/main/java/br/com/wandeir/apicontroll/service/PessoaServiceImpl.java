package br.com.wandeir.apicontroll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import br.com.wandeir.apicontroll.model.Pessoa;
import br.com.wandeir.apicontroll.model.PrestadorServico;
import br.com.wandeir.apicontroll.repository.PessoaRepository;
import br.com.wandeir.apicontroll.repository.PrestadorServicoRepository;

@Component
public class PessoaServiceImpl implements PessoaService {
	
	private final PessoaRepository pessoaRepository;
	
	private final PrestadorServicoRepository prestadorRepository;
	
	public PessoaServiceImpl(PessoaRepository pessoaRepository, PrestadorServicoRepository prestadorRepository) {
		this.pessoaRepository = pessoaRepository;
		this.prestadorRepository = prestadorRepository;
	}

	@Override
	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
	}

	@Override
	public Pessoa gravarPessoa(Pessoa p) {
		return pessoaRepository.save(p);
	}

	@Override
	public Optional<Pessoa> getPessoaByUuid(String uuid) {
		return pessoaRepository.findByUuid(uuid);
	}

	@Override
	public PrestadorServico gravarPrestadorServico(PrestadorServico p) {
		return prestadorRepository.save(p);
	}
	
	@Override
	public List<PrestadorServico> getServicosPorPessoa(Long id) {
		PrestadorServico m = new PrestadorServico();
		Pessoa pessoa = new Pessoa();
		pessoa.setId(id);
		m.setPrestadorServico(pessoa);
		
		Example<PrestadorServico> exemplo = Example.of(m);
		return prestadorRepository.findAll(exemplo, Sort.by(Sort.Direction.ASC, "id"));
	}

	@Override
	public List<Pessoa> findUserByStatusAndName(Double lat, Double lng) {
		return pessoaRepository.findUserByStatusAndName(lat, lng);
	}

}
