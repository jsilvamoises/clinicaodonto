package enums;

/**
 * Enum com os tipos de contrato possíveis
 * @author giovanicb
 *
 */
public enum TipoDeContrato {
	/*RESTAURACAO_1FACE("Restauração 1 face"), RESTAURACAO_2FACES("Restauração 2 faces"), 
	RESTAURACAO_3FACES("Restauração 3 faces"),  
	EXTRACAO_SIMPLES("Extração Simples"), EXTRACAO_MOLAR("Extração Molar"), 
	CLAREAMENTO_LASER("Clareamento a laser"), CLAREAMENTO_CASEIRO("Clareamento caseiro"), 
	TARTARECTOMIA_GRAU1("Tartarectomia grau 1"), TARTARECTOMIA_GRAU2("Tartarectomia grau 2"), 
	TARTARECTOMIA_GRAU3("Tartarectomia grau 3"), APLICACAO_DE_FLUOR("Aplicação de flúor");*/
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
