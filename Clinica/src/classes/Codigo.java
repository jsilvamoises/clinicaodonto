package classes;

public class Codigo {
	private String codigo;

	public Codigo(String codigo) throws Exception {
		setCodigo(codigo);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) throws Exception {
		if (codigo == null || codigo.trim().isEmpty() || !isNumber(codigo))
			throw new Exception("O campo CÓDIGO deve ser preenchido");
		int cod = Integer.parseInt(codigo.trim());
		this.codigo = String.valueOf(cod);
	}

	private boolean isNumber(String codigo) throws Exception {
		if (!codigo.trim().matches("[0-9]+"))
			throw new Exception("O codigo deve ser um número");
		return true;
	}

    @Override
    public String toString() {
        return getCodigo();
    }


}
