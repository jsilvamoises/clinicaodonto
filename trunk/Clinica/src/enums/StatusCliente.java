package enums;

public enum StatusCliente {
	CONCLUIDO("Concluido"), ABANDONO("Abandono"), 
        EM_TRATAMENTO("Em Tratamento"), CANCELADO("Cancelado");
	
	
	StatusCliente(String tipo) {
	}
	
	public static StatusCliente getStatusCliente(String status){
		if(status != null) {
			for(StatusCliente t : values()) {
				if(status.equalsIgnoreCase(t.name())) {
                                     System.out.println(t.name());
					return t;
                            }
			}
		}
		return null;
	}
}
