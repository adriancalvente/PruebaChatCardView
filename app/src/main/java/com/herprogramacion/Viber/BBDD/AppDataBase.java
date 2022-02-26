package com.herprogramacion.Viber.BBDD;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.herprogramacion.Viber.models.Mensaje;
import com.herprogramacion.Viber.models.Usuarios;

@Database(entities = {Usuarios.class, Mensaje.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UsuariosDao usuariosDao();

    public abstract MensajesDao mensajesDao();
}
