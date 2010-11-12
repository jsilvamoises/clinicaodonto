package enums;

/**
 * Enum que identifica o privilégio de acesso ao sistema
 * 
 * @author Giovani
 * 
 */
public enum Privilegio {
	ADMINISTRADOR("Administrador"), FUNCIONARIO("Funcionario");

	Privilegio(String privilegio) {

	}

	/**
	 * Metodo que auxilia a validação de uma String vinda da GUI
	 * 
	 * @param privilegio
	 * @return
	 */
	public static Privilegio getPrivilegio(String privilegio) {
		if (privilegio != null) {
			for (Privilegio p : values()) {
				if (privilegio.equalsIgnoreCase(p.name()))
					return p;
			}
		}
		return null;
	}
}
