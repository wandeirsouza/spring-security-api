package br.com.wandeir.apicontroll.dto;

import java.util.Date;
import java.util.stream.Stream;

import br.com.wandeir.apicontroll.enums.TipoCliente;
import br.com.wandeir.apicontroll.enums.TipoPessoa;
import br.com.wandeir.apicontroll.model.Pessoa;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PessoaDTO {
	
	private Long id;
	
	private String uuid;
	
	private Double lat;
	
	private Double lng;

	private String nome;

	private String sobrenome;
	
	private String cpf;

	private Date dataNascimento;

	private String email;
	
	private String facebook;
	
	private String instagran;
	
	private String outrasRedesSociais;
	
	private String sexo;
	
	private String whatsappNumber;
	
	private String otherPhoneNumber;
	
	private String custumerTipe;
	
	public Pessoa buildPessoa(Pessoa p) {
		if(p== null ) {
			p = new Pessoa();
		}
		TipoPessoa tipoPesso = Stream.of(TipoPessoa.values())
		.filter(c -> c.getValue().equals(this.sexo))
		.findFirst()
		.orElseThrow();
		
		TipoCliente tipocliente = Stream.of(TipoCliente.values())
				.filter(c -> c.getValue().equals(this.custumerTipe))
				.findFirst()
				.orElseThrow();
		
		p.setCpf(this.cpf);
		p.setUuid(this.uuid);
		p.setDataNascimento(this.dataNascimento);
		p.setNome(this.nome);
		p.setSobrenome(this.sobrenome);
		p.setEmail(this.email);
		p.setFacebook(this.facebook);
		p.setInstagran(this.instagran);
		p.setOtherPhoneNumber(this.otherPhoneNumber);
		p.setSexo(tipoPesso);
		p.setLat(this.lat);
		p.setLng(this.lng);
		p.setWhatsappNumber(this.whatsappNumber);
		p.setOutrasRedesSociais(this.outrasRedesSociais);
		p.setCustumerTipe(tipocliente);
		return p;
	}
	
	public PessoaDTO buildPessoaDTO(Pessoa p) {
		setCpf(p.getCpf());
		setUuid(p.getUuid());
		setDataNascimento(p.getDataNascimento());
		setNome(p.getNome());
		setSobrenome(p.getSobrenome());
		setEmail(p.getEmail());
		setFacebook(p.getFacebook());
		setInstagran(p.getInstagran());;
		setOtherPhoneNumber(p.getOtherPhoneNumber());
		setSexo(p.getSexo().getValue());
		setLat(p.getLat());
		setLng(p.getLng());
		setWhatsappNumber(p.getWhatsappNumber());
		setOutrasRedesSociais(p.getOutrasRedesSociais());
		setId(p.getId());
		setCustumerTipe(p.getCustumerTipe().getValue());
		return this;
	}
}
