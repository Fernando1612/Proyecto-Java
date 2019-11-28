/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.DefaultListModel;

/**
 *
 * @author chapa
 */
public class HiloVendedor extends Thread{
    
    //Atributos
    //se crea un socket cliente != de ServerSocket (Socket del servidor)
    private Socket socketVendedor;
    private DataInputStream entrada;
    private FrmVendedor vendedor;
    private ObjectInputStream entradaObjeto;
    
    //Metedo constructor
    public HiloVendedor(Socket socketVendedor, FrmVendedor vendedor){
        this.socketVendedor = socketVendedor;
        this.vendedor = vendedor;
    }
    
    public void run(){
        while(true){
            try{
                //Creamos objeto para leer datos
                entrada = new DataInputStream(socketVendedor.getInputStream());
                //Leer mensaje en codificacion UTf-8
                vendedor.mensajeria(entrada.readUTF());
                
                entradaObjeto = new ObjectInputStream(socketVendedor.getInputStream());
                //Actuliza Jlist
                vendedor.actualizarLista((DefaultListModel) entradaObjeto.readObject());
            }catch(ClassNotFoundException ex){
                ex.printStackTrace();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
            
        }
    }
  
}
