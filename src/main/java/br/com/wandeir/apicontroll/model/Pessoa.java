package br.com.wandeir.apicontroll.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import br.com.wandeir.apicontroll.enums.TipoCliente;
import br.com.wandeir.apicontroll.enums.TipoPessoa;
import br.com.wandeir.apicontroll.enums.converters.TipoClienteConverter;
import br.com.wandeir.apicontroll.enums.converters.TipoPessoaConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String uuid;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Agendamento> agendamentosCliente;
	
	@OneToMany(mappedBy = "prestador", fetch = FetchType.LAZY)
	private List<Agendamento> agendamentosPrestador;
	
	@OneToMany(mappedBy = "prestadorServico", fetch = FetchType.LAZY)
    private List<PrestadorServico> servicosPrestados;
	
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Mensagem> mensagens;
	
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private List<Endereco> enderecos;
	
	private Double lat;
	
	private Double lng;
	
	@Column(length = 50)
	private String nome;
	
	@Column(length = 100)
	private String sobrenome;
	
	@Column(length = 11)
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Email
	@Column(length = 60)
	private String email;
	
	private String facebook;
	
	private String instagran;
	
	private String outrasRedesSociais;
	
	@Convert(converter = TipoPessoaConverter.class)
	private TipoPessoa sexo;
	
	private String whatsappNumber;
	
	private String otherPhoneNumber;
	
	@Convert(converter = TipoClienteConverter.class)
	private TipoCliente custumerTipe;
}
