package com.herprogramacion.pruebachatcardview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.herprogramacion.pruebachatcardview.adapter.AdapterMensajes;
import com.herprogramacion.pruebachatcardview.adapter.HolderMensaje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private EditText txtMensaje;
    private Button btnEnviar;

    RecyclerView rvMensajes;
    private Socket cliente;
    String strServidor, strUsuario;
    private ServerSocket server;
    private PrintWriter output;
    private BufferedReader input;

    private AdapterMensajes adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            System.out.println("gffgds");
            declararObjetos();
            strServidor = "localhost";
            strUsuario = "pepe";
            new Thread(new Servidor()).start();


        }catch (Exception e ){
            System.out.println(e.getLocalizedMessage());
            System.out.println("DSFfsdasfgad");
        }
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter.addMensaje(new Mensaje(txtMensaje.getText().toString(), 0));
                txtMensaje.setText("");
            }
        });

//        btnEnviarIzq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                adapter.addMensaje(new Mensaje(txtMensaje.getText().toString(),1));
//                txtMensaje.setText("");
//            }
//        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });
    }

    private void declararObjetos() {
        txtMensaje = (EditText) findViewById(R.id.txtMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        rvMensajes = (RecyclerView) findViewById(R.id.rvMensajes);

        adapter = new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);
    }

    private void setScrollbar() {
        rvMensajes.scrollToPosition(adapter.getItemCount() - 1);
    }


}
