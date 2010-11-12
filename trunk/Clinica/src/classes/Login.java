package classes;

/**
 * Classe que representa um login de um usuário do sistema
 * 
 * @author Giovani
 * 
 */
public class Login {
	private String login;

	/**
	 * Construtor de um login
	 * 
	 * @param login
	 *            O login do usuário
	 * @throws Exception
	 */
	public Login(String login) throws Exception {
		setLogin(login);
	}

	/**
	 * Ajusta o login do usuario
	 * 
	 * @param login
	 *            o login a ser ajustado
	 * @throws Exception
	 */
	public void setLogin(String login) throws Exception {
		if (login == null || login.trim().isEmpty())
			throw new Exception("Login inválido");
		this.login = login;
	}

	/**
	 * Recupera o login
	 * 
	 * @return o login
	 */
	public String getLogin() {
		return login;
	}

}
