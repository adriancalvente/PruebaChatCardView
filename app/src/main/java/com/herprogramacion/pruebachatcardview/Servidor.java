package com.herprogramacion.pruebachatcardview;


import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable{
    ServerSocket server;

    private BufferedReader input;
    private String mensaje;
    @Override
    public void run() {

        try {
            server = new ServerSocket(1500);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        while (true) {
            try {
                Socket cliente = server.accept();
                input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                 mensaje = input.readLine();

                if (mensaje != null && !mensaje.isEmpty()) {
                    System.out.println(mensaje);
                }
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
