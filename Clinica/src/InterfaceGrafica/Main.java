/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceGrafica;

import facades.SistemaFacade;
import javax.swing.JOptionPane;

/**
 *
 * @author usuário
 */
public class Main {
    public static void main(String[] args) {
        new InterfaceGrafica.Login();
        SistemaFacade sistema = SistemaFacade.getInstance();
        try{
            sistema.initial();
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Problemas com a atualiza��o",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
