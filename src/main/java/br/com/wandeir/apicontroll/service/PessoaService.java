package br.com.wandeir.apicontroll.service;

import java.util.List;
import java.util.Optional;

import br.com.wandeir.apicontroll.model.Pessoa;
import br.com.wandeir.apicontroll.model.PrestadorServico;

public interface PessoaService {
	public Optional<Pessoa> findById(Long id);
	
	public Pessoa gravarPessoa(Pessoa p );
	
	public PrestadorServico gravarPrestadorServico(PrestadorServico p );
	
	public Optional<Pessoa> getPessoaByUuid(String uuid);
	
	public List<PrestadorServico> getServicosPorPessoa(Long id);
}
