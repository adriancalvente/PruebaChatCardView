package com.herprogramacion.pruebachatcardview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
import java.net.SocketOption;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText txtMensaje;
    private Button btnEnviar;

    RecyclerView rvMensajes;
    //    private Socket cliente;
    String strServidor, strUsuario;
    private ServerSocket server;
    private PrintWriter output;
    private BufferedReader input;
    String msg;
    String id;


    private AdapterMensajes adapter;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;




    @SuppressLint("HardwareIds")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        strUsuario = intent.getStringExtra("usuario");

        id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        try {
            declararObjetos();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        btnEnviar.setOnClickListener(view -> {
            System.out.println(strUsuario);

//           adapter.addMensaje(new Mensaje(txtMensaje.getText().toString(), 0));
            databaseReference.push().setValue(new Mensaje(strUsuario,txtMensaje.getText().toString(), 0, id));
            //databaseReference.push().setValue(new Receptor(strUsuario+": "+txtMensaje.getText().toString(), 1,id));
//            databaseReference.push().setValue(new Receptor(strUsuario+":"+txtMensaje));
            txtMensaje.setText("");

        });


        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, String previousChildName) {
                Mensaje m = snapshot.getValue(Mensaje.class);
                assert m != null;
                if (m.getEmisor().equals(id)){

                    adapter.addMensaje(m);
                }else if(!m.getEmisor().equals(id)){
                m.setMensaje(m.getUsuario()+": "+m.getMensaje());
                    m.setPosicion(1);
                    adapter.addMensaje(m);
                }


            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void declararObjetos() {
        txtMensaje = (EditText) findViewById(R.id.txtMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        rvMensajes = (RecyclerView) findViewById(R.id.rvMensajes);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");
        adapter = new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);
    }

    private void setScrollbar() {
        rvMensajes.scrollToPosition(adapter.getItemCount() - 1);
    }


}
