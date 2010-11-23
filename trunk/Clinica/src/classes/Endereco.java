package classes;

/**
 * Classe que representa um endereco contendo rua, numero, complemento, bairro,
 * cidade e cep, enxugando mais a classe Cliente
 * 
 * @author Giovani
 * 
 */
public class Endereco {
	private String rua, numero, complemento, bairro, cidade, cep;

	public Endereco(String rua, String numero, String complemento,
			String bairro, String cidade, String cep) {
		setRua(rua);
		setNumero(numero);
		setComplemento(complemento);
		setBairro(bairro);
		setCidade(cidade);
		setCep(cep);
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCep() {
		return cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		String rua = getRua();
		String bairro = getBairro();
		if(rua.length() > 20)
			rua = rua.substring(0, 21);
		if(bairro.length() > 20)
			bairro = bairro.substring(0, 21);
		return rua + ", " + getNumero()
				+ "\n"
				+ bairro + "\n" + getCidade();
	}
}
