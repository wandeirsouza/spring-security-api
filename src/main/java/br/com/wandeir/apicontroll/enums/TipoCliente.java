package br.com.wandeir.apicontroll.enums;

public enum TipoCliente {
	CLIENTE("c"), PRESTADOR("p");
	
	private String value;
	
	private TipoCliente(String value) {
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
