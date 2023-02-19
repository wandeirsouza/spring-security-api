package br.com.wandeir.apicontroll.dto;

import br.com.wandeir.apicontroll.model.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoDTO {
	private Long id;
	
	private String cep;
	
	private String endereco;	
	
	private String complemento;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;
	
	private Long idPessoa;
	
	public Endereco buildEndereco(Endereco e) {
		if(e == null)
			e = new Endereco();
		e.setBairro(this.bairro);
		e.setCep(this.cep);
		e.setCidade(this.cidade);
		e.setComplemento(this.complemento);
		e.setEndereco(this.endereco);
		e.setEstado(this.estado);
		
		return e;
	}
	
	public EnderecoDTO buildEnderecoDTO(Endereco e) {
		setBairro(e.getBairro());
		setCep(e.getCep());
		setCidade(e.getCidade());
		setComplemento(e.getComplemento());
		setEndereco(e.getEndereco());
		setEstado(e.getEstado());
		setId(e.getPessoa().getId());
		return this;
	}
}
