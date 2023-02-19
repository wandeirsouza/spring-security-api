package br.com.wandeir.apicontroll.service;

import java.util.List;

import br.com.wandeir.apicontroll.model.Servico;
import br.com.wandeir.apicontroll.model.TipoServico;

public interface ServicoService {
	public Servico findById(Long id);
	
	public List<Servico> findAll();
	public List<Servico> findAllByTipo(Long id);
	public List<TipoServico> findAllTipos();
}
