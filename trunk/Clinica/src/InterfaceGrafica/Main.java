/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceGrafica;

import java.io.IOException;

import classes.Login;
import classes.Senha;
import classes.UserImpl;
import persistencia.LogaveisDAO;

/**
 *
 * @author usuário
 */
public class Main {
    public static void main(String[] args) {
        new InterfaceGrafica.Login();

        
        try {
			LogaveisDAO.getInstance().criar(new UserImpl(new Login("aluana"), new Senha("123456")));
			LogaveisDAO.getInstance().criar(new UserImpl(new Login("raquel"), new Senha("123456")));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
