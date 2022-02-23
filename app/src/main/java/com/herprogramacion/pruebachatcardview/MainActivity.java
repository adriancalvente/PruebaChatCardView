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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
    int cont = 0;
    String id;
    private DateFormat dateFormat;

    private AdapterMensajes adapter;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @SuppressLint("HardwareIds")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mostrarAlertDialog();

        id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        try {
            declararObjetos();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        btnEnviar.setOnClickListener(view -> {
//            Map<String, Object> mensaje = new HashMap<>();

//            mensaje.put("Usuario", strUsuario);
//            mensaje.put("Mensaje", txtMensaje.getText().toString());
//            mensaje.put("posicion", 0);
//            mensaje.put("id", id);
//            mensaje.put("hora", dateFormat.format(date));
//            mensaje.put("msgLeidDrch", false);
//            mensaje.put("msgLeidIzq", false);
//
//
//            databaseReference.child(String.valueOf(cont)).updateChildren(mensaje);
            Date date = new Date();

            // adapter.addMensaje(new Mensaje(txtMensaje.getText().toString(), 0));
            System.out.println(adapter.getItemCount());
            databaseReference.child(String.valueOf(adapter.getItemCount())).setValue(new Mensaje(strUsuario, txtMensaje.getText().toString(), 0, id, dateFormat.format(date)));
            //databaseReference.child(String.valueOf(adapter.getItemCount())).setValue(new Receptor(false));
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
//                Receptor r = snapshot.getValue(Receptor.class);
                assert m != null;
//                assert r != null;
                databaseReference.child(String.valueOf(adapter.getItemCount())).child("mensajeLeidoDrch").removeValue();
                databaseReference.child(String.valueOf(adapter.getItemCount())).child("mensajeLeidoIzq").removeValue();
                m.setMensajeLeidoDrch(false);
                m.setMensajeLeidoIzq(false);
                if (m.getEmisor().equals(id) && !m.isMensajeLeidoDrch()) {
                    databaseReference.child(String.valueOf(adapter.getItemCount())).child("mensajeLeidoDrch").setValue(true);
                    databaseReference.child(String.valueOf(adapter.getItemCount())).child("mensajeLeidoIzq").setValue(true);
                    adapter.addMensaje(m);
                } else if (!m.getEmisor().equals(id) && !m.isMensajeLeidoIzq()) {
                    m.setMensaje(m.getUsuario() + ": " + m.getMensaje());
                    databaseReference.child(String.valueOf(adapter.getItemCount())).child("mensajeLeidoDrch").setValue(true);
                    databaseReference.child(String.valueOf(adapter.getItemCount())).child("mensajeLeidoIzq").setValue(true);
                    m.setPosicion(1);
                    Date date = new Date();
                    m.setTiempoIzq(dateFormat.format(date));
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

    private void mostrarAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        builder.setView(customLayout);
        builder.setPositiveButton("OK", (dialog, which) -> {
            EditText editText = customLayout.findViewById(R.id.editText);
            EditText editText1 = customLayout.findViewById(R.id.servidor);
            strServidor = editText1.getText().toString();
            strUsuario = editText.getText().toString();


        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @SuppressLint("SimpleDateFormat")
    private void declararObjetos() {
        txtMensaje = (EditText) findViewById(R.id.txtMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        rvMensajes = (RecyclerView) findViewById(R.id.rvMensajes);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");
        dateFormat = new SimpleDateFormat("HH:mm");
        adapter = new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);
    }

    private void setScrollbar() {
        rvMensajes.scrollToPosition(adapter.getItemCount() - 1);
    }


}
