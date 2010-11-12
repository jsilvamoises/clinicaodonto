package enums;

/**
 * Enum de sexo.
 * 
 * @author Tiago
 * 
 */
public enum Sexo {
	MASCULINO("Masculino"), FEMININO("Feminino");

	Sexo(String sexo) {
	}

	public static Sexo getSexo(String sex) {
		if (sex != null) {
			for (Sexo e : values()) {
				if (sex.equalsIgnoreCase(e.name()))
					return e;
			}
		}
		return null;
	}
}
