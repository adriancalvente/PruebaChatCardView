package com.herprogramacion.Viber.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.herprogramacion.Viber.BBDD.AppDataBase;
import com.herprogramacion.Viber.R;
import com.herprogramacion.Viber.models.Usuarios;

public class ActividadRegistro extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CODE = 200;
    EditText usuario, contrasena;
    Button registrar, iniciar;
    ProgressBar pb;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            int permisoAudio = checkSelfPermission(Manifest.permission.RECORD_AUDIO);
            if (permisoAudio == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE);
            }
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_actividad_registro);
            usuario = findViewById(R.id.nombreUsuario);
            contrasena = findViewById(R.id.contrasena);
            registrar = findViewById(R.id.btnRegistrar);
            iniciar = findViewById(R.id.btnIniciar);
            pb = findViewById(R.id.progressBar);
        } catch (Exception e) {
            Log.e("unexpected", "Unexpected error");
        }
    }

    @Override
    public void onClick(View view) {
        try {

            switch (view.getId()) {
                case R.id.btnIniciar:
                    AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class,
                            "database-name").allowMainThreadQueries().build();
                    Usuarios usuarioRegistrado = db.usuariosDao().findById(usuario.getText().toString(), contrasena.getText().toString());
                    if (usuarioRegistrado != null) {
                        //Iniciar main Activity (enviar usuario)
                        pb.setVisibility(View.VISIBLE);
                        new EsperaAPP(this).execute();
                        intent = new Intent(this, MainActivity.class);
                        intent.putExtra("usuario", usuarioRegistrado.getNombreUsuario());
                        Log.i("debug", usuarioRegistrado.getNombreUsuario());
                    } else {
                        Toast.makeText(this, "Usuario o Contraseña incorrectos",
                                Toast.LENGTH_SHORT).show();
                        contrasena.setText("");
                    }
                    break;
                case R.id.btnRegistrar:
                    try {
                        if (!usuario.getText().toString().isEmpty() && !contrasena.getText().toString().isEmpty()) {
                            AppDataBase db1 = Room.databaseBuilder(getApplicationContext(), AppDataBase.class,
                                    "database-name").allowMainThreadQueries().build();
                            Usuarios usuarioRegistrar = new Usuarios(usuario.getText().toString(), contrasena.getText().toString());
                            db1.usuariosDao().insert(usuarioRegistrar);
                            Toast.makeText(this, "Usuario registrado",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Usuario o Contraseña incorrectos",
                                    Toast.LENGTH_SHORT).show();
                            contrasena.setText("");
                        }
                    } catch (SQLiteException e) {
                        Toast.makeText(this, "Ya existe un usuario con ese nombre",
                                Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        } catch (Exception e) {
            Log.e("unexpected", "Unexpected error");
        }
    }
}