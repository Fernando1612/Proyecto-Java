/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author chapa
 */
public class Servidor {
    //se implementa un socket del servidor, que espera una peticion desde la red del puerto
    private ServerSocket servidor;
    //Puerto donde se va establecer la conexion
    private final int PUERTO = 1000;
    
    public void iniciarServ(){
        try{
            //Socket con el puerto
            setServidor(new ServerSocket(PUERTO));
            System.out.println("*.:Servidor con conexion:.*\n");
            //Ciclo infinito para la recepcion de un cliente que se conecta en el mismo puerto
            while(true){
                //Espera la conexion para aceptar la solicitud entrante al socket
                Socket cliente = getServidor().accept();
                //Se muestra la conexion ip del cliente conectado
                System.out.println("Cliente conectado desde la direccion: " + cliente.getInetAddress().getHostAddress());
                
                //Perimite la lectura de lineas de texto
                DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                
                //Se crea objeto del tipo HiloAdministrador
                HiloAdministrador hilo = new HiloAdministrador(cliente, entrada.readUTF(), this );
                //Se inicia el hilo
                hilo.start();
            }

        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }
}

