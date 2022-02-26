package com.herprogramacion.Viber.BBDD;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.herprogramacion.Viber.models.Mensaje;

import java.util.List;

@Dao
public interface MensajesDao {
    @Query("SELECT * FROM Mensaje")
    List<Mensaje> getAll();

    @Query("SELECT * FROM Mensaje WHERE id=:id LIMIT 1")
    Mensaje findById(String id);

    @Insert
    Long insert(Mensaje mensaje);

    @Delete
    int delete(Mensaje mensaje);

    @Update
    int update(Mensaje mensaje);
}
