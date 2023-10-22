package br.com.wandeir.apicontroll.enums.converters;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;

import br.com.wandeir.apicontroll.enums.TipoCliente;

public class TipoClienteConverter implements AttributeConverter<TipoCliente, String> {

	@Override
	public String convertToDatabaseColumn(TipoCliente custumerTipe) {
		if(custumerTipe==null)
			return null;
		return custumerTipe.getValue();
	}

	@Override
	public TipoCliente convertToEntityAttribute(String value) {
		if(value == null)
			return null;
		return Stream.of(TipoCliente.values())
				.filter(c -> c.getValue().equals(value))
				.findFirst()
				.orElseThrow();
	}

}
