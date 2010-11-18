package classes;

import enums.Privilegio;
import interfaces.User;

/**
 * Classe que define um usuario que pode ser a dona por exemplo
 * 
 * @author Giovani
 * 
 */
public class UserImpl implements User {

	private Codigo codigo;
	private String nome;
	private Login login;
	private Senha senha;
	private Privilegio privilegio;

	public UserImpl(Login login, Senha senha) throws Exception {
		setLogin(login);
		setSenha(senha);
                codigo = null;
                nome = login.getLogin();
	}

	@Override
	public Codigo getCodigo() {
		return codigo;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) throws Exception {
		if (nome == null || nome.trim().isEmpty())
			throw new Exception("Nome inválido");
		this.nome = nome;
	}

	@Override
	public Login getLogin() {
		return login;
	}

	@Override
	public Senha getSenha() {
		return senha;
	}

	@Override
	public void setLogin(Login login) throws Exception {
		this.login = login;
	}

	@Override
	public void setSenha(Senha senha) throws Exception {
		this.senha = senha;
	}

	@Override
	public Privilegio getPrivilegio() {
		return privilegio;
	}

	@Override
	public void setPrivilegio(Privilegio privilegio) throws Exception {
		this.privilegio = privilegio;
	}
	
	@Override
	public String toString() {
		return getLogin().getLogin();
	}

	@Override
	public void setCodigo(Codigo codigo) throws Exception {
		this.codigo = codigo;
	}

}
