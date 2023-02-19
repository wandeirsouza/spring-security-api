package br.com.wandeir.apicontroll.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.com.wandeir.apicontroll.model.Agendamento;
import br.com.wandeir.apicontroll.model.Pessoa;

public interface AgendamentoService {
	
	public Agendamento gravarAgendamento(Agendamento agendamento);
	
	public List<Agendamento> recuperaAgendamentoPorDataPrestador(Date date, Pessoa pessoa);
	
	public List<Agendamento> recuperaAgendamentoPorDataCliente(Date date, Pessoa pessoa);
	
	public Optional<Agendamento> recuperaAgendamentoPorId(Long id);
}
