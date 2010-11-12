package interfaces;

import classes.Codigo;

/**
 * Interface que idealiza um usuario que é um tipo de Logavel.
 * @author Tiago
 *
 */
public interface User extends Logavel {
	public String getNome();
	
	public void setNome(String nome) throws Exception;
	
	public Codigo getCodigo();
	
	public void setCodigo(Codigo codigo) throws Exception;
}
