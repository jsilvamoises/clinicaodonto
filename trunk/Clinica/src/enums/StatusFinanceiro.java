package enums;

public enum StatusFinanceiro {
	EM_ABERTO("Em aberto"), EM_DIA("Em dia"), ATRASADO("Atrasado");
	
	private StatusFinanceiro(String nome) {
	}
	
	public static StatusFinanceiro getStatusFinanceiro(String statusFinanceiro) {
		if (statusFinanceiro != null) {
			for (StatusFinanceiro e : values()) {
				if (statusFinanceiro.equalsIgnoreCase(e.name()))
					return e;
			}
		}
		return null;
	}
}
