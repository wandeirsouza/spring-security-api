package br.com.wandeir.apicontroll.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetornoPaginadoDTO {
	
	private int totalItens;
	private int totalPaginas;
	private int pagAtual;
	private List<?> dados;

}
