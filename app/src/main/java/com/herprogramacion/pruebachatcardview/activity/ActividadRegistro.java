package com.herprogramacion.pruebachatcardview.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.herprogramacion.pruebachatcardview.BBDD.AppDataBase;
import com.herprogramacion.pruebachatcardview.BBDD.Usuarios;
import com.herprogramacion.pruebachatcardview.R;

public class ActividadRegistro extends AppCompatActivity implements View.OnClickListener {
    EditText usuario, contrasena;
    Button registrar, iniciar;
    ProgressBar pb;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_registro);
        usuario = findViewById(R.id.nombreUsuario);
        contrasena = findViewById(R.id.contrasena);
        registrar = findViewById(R.id.btnRegistrar);
        iniciar = findViewById(R.id.btnIniciar);
        pb = findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnIniciar:
                AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class,
                        "database-name").allowMainThreadQueries().build();
                Usuarios usuarioRegistrado = db.usuariosDao().findById(usuario.getText().toString(), contrasena.getText().toString());
                if (usuarioRegistrado != null) {
                    //Iniciar main Activity (enviar usuario)
                    pb.setVisibility(View.VISIBLE);
                    new EsperaAPP().execute();
                    intent = new Intent(this, MainActivity.class);
                    intent.putExtra("usuario", usuarioRegistrado.getNombreUsuario());
                    Log.i("debug", usuarioRegistrado.getNombreUsuario());
//                    startActivity(intent);
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
    }

    class EsperaAPP extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setMax(100);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]);

        }

        @Override
        protected Integer doInBackground(Void... arg0) {

            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {

            startActivity(intent);
        }
    }
}