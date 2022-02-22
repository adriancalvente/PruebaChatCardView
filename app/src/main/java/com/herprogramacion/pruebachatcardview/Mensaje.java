package com.herprogramacion.pruebachatcardview;

public class Mensaje {
    private String mensaje;
    private String mensajeizq;
    private int posicion;
    private String emisor;
    private String usuario;
    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public Mensaje() {
    }

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Mensaje(String mensaje, int posicion) {
        this.mensaje = mensaje;
        this.posicion = posicion;
    }

    public Mensaje(String usuario,String mensaje, int posicion, String emisor) {
        this.mensaje = mensaje;
        this.posicion = posicion;
        this.emisor=emisor;
        this.usuario = usuario;
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

