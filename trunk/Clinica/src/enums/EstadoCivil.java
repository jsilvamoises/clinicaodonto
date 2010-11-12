package enums;

/**
 * Enum de estados civis.
 * 
 * @author Tiago
 * 
 */
public enum EstadoCivil {
	Solteiro, Casado, Divorciado, Viuvo;

	public static EstadoCivil getEstadoCivil(String estadoCivil) {
		if (estadoCivil != null) {
			for (EstadoCivil e : values()) {
				if (estadoCivil.equalsIgnoreCase(e.name()))
					return e;
			}
		}
		return null;
	}
}
