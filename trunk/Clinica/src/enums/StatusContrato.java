package enums;

public enum StatusContrato {
	CONCLUIDO("Concluido"), EM_TRATAMENTO("Em Tratamento"), CANCELADO(
			"Cancelado");

	StatusContrato(String tipo) {
	}

	public static StatusContrato getStatusContrato(String status) {
		if (status != null) {
			for (StatusContrato t : values()) {
				if (status.equalsIgnoreCase(t.name())) {				;
                                    return t;
                            }
			}
		}
		return null;
	}
}
