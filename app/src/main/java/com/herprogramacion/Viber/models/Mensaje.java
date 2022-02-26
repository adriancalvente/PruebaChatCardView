package com.herprogramacion.Viber.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalTime;

@Entity
public class Mensaje {
    @ColumnInfo
    private String mensaje;
    @ColumnInfo
    private String mensajeizq;
    @ColumnInfo
    private int posicion;
    @ColumnInfo
    private String id;
    @ColumnInfo
    private String usuario;
    @ColumnInfo
    private String tiempo;
    @ColumnInfo
    private String tiempoIzq;
    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String tiempoDeEnvio;

    public Mensaje() {
    }

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Mensaje(String mensaje, int posicion) {
        this.mensaje = mensaje;
        this.posicion = posicion;
    }

    public Mensaje(String usuario, String mensaje, int posicion, String id, String tiempo) {
        this.mensaje = mensaje;
        this.posicion = posicion;
        this.id = id;
        this.usuario = usuario;
        this.tiempo = tiempo;
        this.tiempoDeEnvio = String.valueOf(LocalTime.now());
    }

    @NonNull
    public String getTiempoDeEnvio() {
        return tiempoDeEnvio;
    }

    public void setTiempoDeEnvio(@NonNull String tiempoDeEnvio) {
        this.tiempoDeEnvio = tiempoDeEnvio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getTiempoIzq() {
        return tiempoIzq;
    }

    public void setTiempoIzq(String tiempoIzq) {
        this.tiempoIzq = tiempoIzq;
    }

    public String getMensajeizq() {
        return mensajeizq;
    }

    public void setMensajeizq(String mensajeizq) {
        this.mensajeizq = mensajeizq;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

