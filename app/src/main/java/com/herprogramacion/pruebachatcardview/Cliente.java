package com.herprogramacion.pruebachatcardview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Cliente extends Thread{
    public static ArrayList<Cliente> clientes = new ArrayList<>();
    private PrintWriter output;

    private Socket cliente;
    private String strServidor;
    private String txtMensaje;
    private String strUsuario;
    private String mensajeEnviar;
    public Cliente(String strServidor, String strUsuario){
        this.strServidor=strServidor;
        this.strUsuario = strUsuario;
        clientes.add(this);
    }
    public Cliente(String strServidor, String strUsuario,String  txtMensaje){
        this.strServidor=strServidor;
        this.txtMensaje=txtMensaje;
        this.strUsuario = strUsuario;
        clientes.add(this);
    }

    @Override
    public void run() {
        try {
            cliente = new Socket(strServidor,1500);
            output = new PrintWriter(cliente.getOutputStream());
            output = new PrintWriter(cliente.getOutputStream());
             mensajeEnviar=txtMensaje;
            output.write(strUsuario+": "+mensajeEnviar);
            output.flush();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getMensajeEnviar() {
        return mensajeEnviar;
    }
}
