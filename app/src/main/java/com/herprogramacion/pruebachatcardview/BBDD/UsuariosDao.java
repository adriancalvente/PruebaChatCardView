package com.herprogramacion.pruebachatcardview.BBDD;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuariosDao {

    @Query("SELECT * FROM Usuarios")
    List<Usuarios> getAll();

    @Query("SELECT * FROM usuarios WHERE nombreUsuario=:nombreUsuario AND contrasena=:contrasena LIMIT 1")
    Usuarios findById(String nombreUsuario,String contrasena);

    @Query("SELECT * FROM usuarios WHERE contrasena LIKE :contrasena LIMIT 1")
    Usuarios findByName(String contrasena);

    @Insert
    Long insert(Usuarios usuarios);

    @Delete
    int delete(Usuarios usuarios);

    @Update
    int update(Usuarios usuarios);
}