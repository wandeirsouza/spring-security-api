package br.com.wandeir.apicontroll.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import br.com.wandeir.apicontroll.model.Agendamento;
import br.com.wandeir.apicontroll.model.Pessoa;
import br.com.wandeir.apicontroll.repository.AgendamentoRepostory;

@Component
public class AgendamentoServiceImpl implements AgendamentoService {
	
	private final AgendamentoRepostory agendamentoRepository;
	
	public AgendamentoServiceImpl(AgendamentoRepostory agendamentoRepository) {
		this.agendamentoRepository = agendamentoRepository;
	}

	@Override
	public Agendamento gravarAgendamento(Agendamento agendamento) {
		return agendamentoRepository.save(agendamento);
	}

	@Override
	public List<Agendamento> recuperaAgendamentoPorDataPrestador(Date date, Pessoa pessoa) {
		Agendamento a = new Agendamento();
		a.setDataServico(date);
		a.setPrestador(pessoa);
		Example<Agendamento> exemplo = Example.of(a);
		return agendamentoRepository.findAll(exemplo, Sort.by(Sort.Direction.ASC, "createdAt"));
	}

	@Override
	public List<Agendamento> recuperaAgendamentoPorDataCliente(Date date, Pessoa pessoa) {
		Agendamento a = new Agendamento();
		a.setDataServico(date);
		a.setCliente(pessoa);
		Example<Agendamento> exemplo = Example.of(a);
		return agendamentoRepository.findAll(exemplo, Sort.by(Sort.Direction.ASC, "createdAt"));
	}

	@Override
	public Optional<Agendamento> recuperaAgendamentoPorId(Long id) {
		return agendamentoRepository.findById(id);
	}

}
