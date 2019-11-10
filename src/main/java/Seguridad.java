
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chapa
 */
public class Seguridad {
    FrmLogin login = new FrmLogin();
    String res;
    public void validarUsuario(String usuario[], String usu, String con,int intentos){
        boolean encontrado = false;
        for (int i = 0; i < usuario.length; i++) {
            if(usuario[i].equalsIgnoreCase(usu) && usuario[i+1].equals(con)){
                res = "Bienvenido " + usu;
                encontrado = true;
                JOptionPane.showMessageDialog(null, res, " Inicio de sesion", JOptionPane.INFORMATION_MESSAGE);
                intentos = 0;
                login.setIntentos(intentos);
                break;
            }
        }
        if(encontrado  == false){
            res = " Clave y/o usuario incorrecto " + intentos + " Intentos fallidos ";
            JOptionPane.showMessageDialog(null, res, " Inicio de sesion", JOptionPane.ERROR_MESSAGE);
        }
        if(intentos > 2){
            res = " 3 Intentos fallidos";
            JOptionPane.showMessageDialog(null, res, " Inicio de sesion", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
