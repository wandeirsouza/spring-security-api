package br.com.wandeir.apicontroll.service;

import java.util.List;

import br.com.wandeir.apicontroll.model.Mensagem;

public interface MensagemService {
	
	public List<Mensagem> getMensagensPorChat(Long idChat);
	
	public Mensagem gravarMensagem(Mensagem m);
	
	public Mensagem findById(Long id);
}
