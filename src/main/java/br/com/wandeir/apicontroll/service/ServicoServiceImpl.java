package br.com.wandeir.apicontroll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import br.com.wandeir.apicontroll.model.Servico;
import br.com.wandeir.apicontroll.model.TipoServico;
import br.com.wandeir.apicontroll.repository.ServicoRepository;
import br.com.wandeir.apicontroll.repository.TipoServicoRepository;

@Component
public class ServicoServiceImpl implements ServicoService {

	private final ServicoRepository servicoRepository;
	private final TipoServicoRepository tipoServicoRepository;
	
	public ServicoServiceImpl(ServicoRepository servicoRepository, TipoServicoRepository tipoServicoRepository) {
		this.servicoRepository = servicoRepository;
		this.tipoServicoRepository = tipoServicoRepository;
	}

	@Override
	public Servico findById(Long id) {
		Optional<Servico> servico = servicoRepository.findById(id);
		if(servico.isPresent())
			return servicoRepository.findById(id).get();
		return null;	
	}

	@Override
	public List<Servico> findAll() {
		return servicoRepository.findAll();
	}

	@Override
	public List<Servico> findAllByTipo(Long id) {
		Servico m = new Servico();
		m.setTipoServico(new TipoServico());
		m.getTipoServico().setId(id);
		Example<Servico> exemplo = Example.of(m);
		return servicoRepository.findAll(exemplo, Sort.by(Sort.Direction.ASC, "descricao"));
	}

	@Override
	public List<TipoServico> findAllTipos() {
		return tipoServicoRepository.findAll();
	}

}
