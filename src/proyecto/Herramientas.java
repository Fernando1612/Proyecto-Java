package proyecto;
import java.io.IOException;
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
public class Herramientas {
    FrmLogin login = new FrmLogin(); //Creacion de un objeto de la clase FrmLogin
    private String res;
    private String ran = "Administrador"; 
    private String ran1 = "Vendedor";
    private String nombre;
    
    //Metodo para validar acceso a la aplicacion
    public void validarUsuario(String usuario[], String usu, String con, int intentos){ 
        boolean encontrado = false;
        for (int i = 0; i < usuario.length; i++) {  //for para recorrer el archivo donde estan los usuarios 
            //Condicional para entrar a la aplicacion si eres administrador
            if(usuario[i].equalsIgnoreCase(usu) && usuario[i+1].equals(con) && usuario[i+2].equals(ran)){
                res = "Bienvenido " + usu;
                encontrado = true;
                //Mensaje de inicio de sesion
                JOptionPane.showMessageDialog(null, res, " Inicio de sesion", JOptionPane.INFORMATION_MESSAGE);
                intentos = 0;
                //si son mas de 3 la aplicacion se cierra
                login.setIntentos(intentos);
                // una vez el usuiario entre, se abre el formulario administrador
                new FrmInicioAdm().setVisible(true);
                break;
            //Condicional para entrar a la aplicacion si eres vendedor
            }else if(usuario[i].equalsIgnoreCase(usu) && usuario[i+1].equals(con) && usuario[i+2].equals(ran1)){
                //Se utiliza el mismo codigo de arriba;
                res = "Bienvenido " + usu;
                encontrado = true;
                JOptionPane.showMessageDialog(null, res, " Inicio de sesion", JOptionPane.INFORMATION_MESSAGE);
                intentos = 0;
                login.setIntentos(intentos);
                // una vez el usuiario entre, se abre el formulario ventas
                new FrmInicioVen().setVisible(true);
            }
        }
        //Si el usuario y contraseña es incorrecto muestra un mensaje
        if(encontrado  == false){
            res = " Clave y/o usuario incorrecto " + intentos + " Intentos fallidos ";
            JOptionPane.showMessageDialog(null, res, " Inicio de sesion", JOptionPane.ERROR_MESSAGE);
        }
        //Si los intentos son igual a 3, la apliacacion se cierra
        if(intentos > 2){
            res = " 3 Intentos fallidos";
            JOptionPane.showMessageDialog(null, res, " Inicio de sesion", JOptionPane.ERROR_MESSAGE);
            //fin del proceso
            System.exit(0);
        }
    }
   public void mostrarLibros(String libros[]){
       int n = libros.length;
       //for que lea el archivo de libros y muestra uno por uno.
       for (int i = 0; i < n; i++) {
            JOptionPane.showMessageDialog(null, "Nombre del libro:" + "\n" + libros[i]);
       }
   }
   
   public String getNombre(){
       return nombre;
   }
   
   public void setNombre(String nombre){
       this.nombre = nombre;
   }
}
