package br.com.wandeir.apicontroll.enums;

public enum TipoPessoa {
	MASCULINO("Maculino"), FEMININO("Feminino"), OUTRO("Outro");
	
	private String value;
	
	private TipoPessoa(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
