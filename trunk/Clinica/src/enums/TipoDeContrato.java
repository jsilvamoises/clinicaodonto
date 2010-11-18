package enums;

/**
 * Enum com os tipos de contrato possíveis
 * @author giovanicb
 *
 */
public enum TipoDeContrato {
	ORTODONTIA("Ortodontia");
	
	TipoDeContrato(String tipo) {
	}
	
	public static TipoDeContrato getTipoDeContrato(String tipoDeContrato) {
		if(tipoDeContrato != null) {
			for(TipoDeContrato t : values()) {
				if(tipoDeContrato.equalsIgnoreCase(t.name()));
					return t;
			}
		}
		return null;
	}
}
