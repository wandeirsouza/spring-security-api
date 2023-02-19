package br.com.wandeir.apicontroll.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Agendamento")
public class Agendamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "cliente_id")
	private Pessoa cliente;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "prestador_id")
	private Pessoa prestador;
	
	@ManyToOne
    @JoinColumn(name = "servico_id")
	private Servico servico;
	
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Temporal(TemporalType.DATE)
	private Date dataServico;
	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "chat_id")
	private Chat chat;
}
