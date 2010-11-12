package interfaces;

import classes.Login;
import classes.Senha;
import enums.Privilegio;

/**
 * Interface que representa um logavel no sistema
 * @author Giovani
 *
 */
public interface Logavel {
	public Login getLogin();
	
	public void setLogin(Login login) throws Exception;
	
	public Senha getSenha();
	
	public void setSenha(Senha senha) throws Exception;
	
	public Privilegio getPrivilegio();
	
	public void setPrivilegio(Privilegio privilegio) throws Exception;
}
