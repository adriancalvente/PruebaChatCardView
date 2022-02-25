package com.herprogramacion.pruebachatcardview.BBDD;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.herprogramacion.pruebachatcardview.models.Mensaje;
import com.herprogramacion.pruebachatcardview.models.Usuarios;

@Database(entities  ={Usuarios.class, Mensaje.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UsuariosDao usuariosDao();
    public abstract MensajesDao mensajesDao();
}
