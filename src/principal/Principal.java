/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import chat.Servidor;
import java.net.ServerSocket;
import proyecto.FrmLogin;

/**
 *
 * @author chapa
 */
public class Principal extends Thread {
    public static void main(String[] args) {
        Principal p = new Principal(); //Se crea un objeto de la clase principal
        p.setDaemon(true); //Designamos que el hilo sea demonio (servicio en segundo plano)
        try{
            p.start(); //Iniciamos el hilo demonio, del metodo run modificado    
        }catch(Exception e){
            e.printStackTrace(); //Capturamos la excepcion
        }finally{
            new FrmLogin().setVisible(true); //Iniciamos la venta del login 
        }
       
    }
    
    @Override
    public void run() {
        Servidor s = new Servidor(); //Se crea un objeto de tipo servidor
        s.iniciarServ(); //se inicia el servidor con el metodo
        
    }
}
