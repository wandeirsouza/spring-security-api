package br.com.wandeir.apicontroll.enums.converters;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.wandeir.apicontroll.enums.TipoPessoa;

@Converter(autoApply = true)
public class TipoPessoaConverter implements AttributeConverter<TipoPessoa, String>{

	@Override
	public String convertToDatabaseColumn(TipoPessoa tipoPessoa) {
		if(tipoPessoa==null)
			return null;
		return tipoPessoa.getValue();
	}

	@Override
	public TipoPessoa convertToEntityAttribute(String value) {
		if(value == null)
			return null;
		return Stream.of(TipoPessoa.values())
				.filter(c -> c.getValue().equals(value))
				.findFirst()
				.orElseThrow();
	}

}
