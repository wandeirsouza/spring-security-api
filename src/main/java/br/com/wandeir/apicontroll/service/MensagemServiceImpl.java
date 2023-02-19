package br.com.wandeir.apicontroll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import br.com.wandeir.apicontroll.model.Chat;
import br.com.wandeir.apicontroll.model.Mensagem;
import br.com.wandeir.apicontroll.repository.MensagemRepository;

@Component
public class MensagemServiceImpl implements MensagemService {
	
	private final MensagemRepository mensagemRepository;
	
	public MensagemServiceImpl(MensagemRepository mensagemRepository) {
		this.mensagemRepository = mensagemRepository;
	}

	@Override
	public List<Mensagem> getMensagensPorChat(Long idChat) {
		Mensagem m = new Mensagem();
		Chat chat = new Chat();
		chat.setId(idChat);
		
		m.setChat(chat);
		Example<Mensagem> exemplo = Example.of(m);
		return mensagemRepository.findAll(exemplo, Sort.by(Sort.Direction.ASC, "createdAt"));
	}

	@Override
	public Mensagem gravarMensagem(Mensagem m) {
		return mensagemRepository.save(m);
	}

	@Override
	public Mensagem findById(Long id) {
		Optional<Mensagem> op = mensagemRepository.findById(id);
		if(op.isPresent())
			return op.get();
		
		return null;
	}

}
