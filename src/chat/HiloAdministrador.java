/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import javax.swing.DefaultListModel;

/**
 *
 * @author chapa
 */
public class HiloAdministrador extends Thread {
    
    //Atributos
    private DataInputStream entrada;
    private DataOutputStream salida;
    private Servidor administrador;
    private Socket vendedor;
    //creamos un vetor para que se guarden los clientes conectados
    private static Vector<HiloAdministrador> usuarioActivo = new Vector();
    private String nombre;
    private ObjectOutputStream salidaObjeto;
    
    //metodo constructor
    public HiloAdministrador(Socket vendedor, String nombre, Servidor administrador) throws Exception{
        this.vendedor = vendedor;
        this.administrador = administrador;
        this.nombre = nombre;
        //añadimos el uauario a nuestra vector
        usuarioActivo.add(this);
        
        //mensaje conexion establecida
        for (int i = 0; i < usuarioActivo.size(); i++) {
            usuarioActivo.get(i).envioMensajes(nombre + " Se ah conectado.");
        }
    }
    
    public void run(){
        String mensaje = " ";
        //cliclo infinito que espera los mensajes
        while(true){
            try{
                //se lee el mensaje escrito en el cliente
                entrada = new DataInputStream(vendedor.getInputStream());
                mensaje = entrada.readUTF();
                
                //se envia el mensaje con el nombre del cliente
                for (int i = 0; i < usuarioActivo.size(); i++) {
                    usuarioActivo.get(i).envioMensajes(mensaje);
                }
            }catch(Exception e){
                //e.printStackTrace();
                System.out.println("Usuario desconectado:");
                break;
            }
        }
        
        //al cerrar el mensaje, se remueve el cliente
        usuarioActivo.removeElement(this);
                
        try{
            vendedor.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
        
    private void envioMensajes(String msj) throws Exception{
        //creamos un objeto para escribir, devolviendo la secuencia de salida
        salida = new DataOutputStream(vendedor.getOutputStream());
        //Escribie una cadena utilizando la codificacion UTF-8
        salida.writeUTF(msj);
        //Creamos un objeto para poder utilizar la Jlist de swing
        DefaultListModel modelo = new DefaultListModel();
        
        //Añadimos el cliente en la parte de JList de jframe, los almacenamos
        for (int i = 0; i < usuarioActivo.size(); i++) {
            modelo.addElement(usuarioActivo.get(i).nombre);
        }
        
        //creamos un objeto para escribir
        salidaObjeto = new ObjectOutputStream(vendedor.getOutputStream());
        //Escribimos el objeto
        salidaObjeto.writeObject(modelo);
    }
}
