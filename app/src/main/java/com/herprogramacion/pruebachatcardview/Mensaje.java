package com.herprogramacion.pruebachatcardview;

public class Mensaje {
    private String mensaje;
    private String mensajeizq;
private int posicion;

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Mensaje(String mensaje, int posicion) {
        this.mensaje = mensaje;
        this.posicion = posicion;
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
}

