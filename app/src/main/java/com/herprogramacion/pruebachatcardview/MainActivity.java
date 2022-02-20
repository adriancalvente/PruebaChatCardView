package com.herprogramacion.pruebachatcardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.herprogramacion.pruebachatcardview.adapter.AdapterMensajes;
import com.herprogramacion.pruebachatcardview.adapter.HolderMensaje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private EditText txtMensaje;
    private Button btnEnviar;

    RecyclerView rvMensajes;
    //    private Socket cliente;
    String strServidor, strUsuario;
    private ServerSocket server;
    private PrintWriter output;
    private BufferedReader input;

    private AdapterMensajes adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mostrarAlertDialog();
        try {
            declararObjetos();



        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("DSFfsdasfgad");
        }
        btnEnviar.setOnClickListener(view -> {
            Cliente cliente = new Cliente(strServidor, strUsuario, txtMensaje.getText().toString());
            cliente.start();
            adapter.addMensaje(new Mensaje(txtMensaje.getText().toString(), 0));
            txtMensaje.setText("");

        });


        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });
    }

    private void mostrarAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        builder.setView(customLayout);
        builder.setPositiveButton("OK", (dialog, which) -> {
            EditText editText = customLayout.findViewById(R.id.editText);
            EditText editText1 = customLayout.findViewById(R.id.servidor);
            strServidor = editText1.getText().toString();
            strUsuario = editText.getText().toString();
            Cliente cliente = new Cliente(strServidor, strUsuario);
            cliente.start();
        }).setNegativeButton("crear", (dialogInterface, i) -> {

            new Thread(new Servidor()).start();
            Cliente cliente = new Cliente(strServidor, strUsuario);
            cliente.start();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
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

    class Servidor implements Runnable {

        @Override
        public void run() {
            try {
                server = new ServerSocket(1500);
            } catch (IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error al crear el servidor", Toast.LENGTH_LONG).show());
            }
            while (true) {
                try {
                    Socket cliente = server.accept();
                    input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    String mensaje1 = input.readLine();
                    if (mensaje1 != null && !mensaje1.isEmpty()) {
                        runOnUiThread(() -> adapter.addMensaje(new Mensaje(mensaje1, 1)));
                    }
                    cliente.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
