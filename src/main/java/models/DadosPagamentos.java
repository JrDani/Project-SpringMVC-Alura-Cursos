package models;

import java.math.BigDecimal;

public class DadosPagamentos {

	private BigDecimal value;

	public DadosPagamentos(BigDecimal total) {
		this.value = total;
	}
	
	public DadosPagamentos() {
	}
	
	public BigDecimal getValue() {
		return value;
	}

}
