package classes;

public class Senha {
	private String senha;

	public Senha(String senha) throws Exception {
		setSenha(senha);
	}

	public void setSenha(String senha) throws Exception {
		if (senha == null || senha.trim().isEmpty())
			throw new Exception("Senha inválida");
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

}
