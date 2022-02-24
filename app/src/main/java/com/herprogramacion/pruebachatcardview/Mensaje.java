package com.herprogramacion.pruebachatcardview;

public class Mensaje {
    private String mensaje;
    private String mensajeizq;
    private int posicion;
    private String emisor;
    private String usuario;
    private String tiempo;
    private String tiempoIzq;
    private boolean mensajeLeidoDrch;
    private boolean mensajeLeidoIzq;

    public Mensaje() {
    }

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Mensaje(String mensaje, int posicion) {
        this.mensaje = mensaje;
        this.posicion = posicion;
    }

    public Mensaje(String usuario, String mensaje, int posicion, String emisor, String tiempo) {
        this.mensaje = mensaje;
        this.posicion = posicion;
        this.emisor = emisor;
        this.usuario = usuario;
        this.tiempo = tiempo;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
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

    public boolean isMensajeLeidoDrch() {
        return mensajeLeidoDrch;
    }

    public void setMensajeLeidoDrch(boolean mensajeLeidoDrch) {
        this.mensajeLeidoDrch = mensajeLeidoDrch;
    }

    public boolean isMensajeLeidoIzq() {
        return mensajeLeidoIzq;
    }

    public void setMensajeLeidoIzq(boolean mensajeLeidoIzq) {
        this.mensajeLeidoIzq = mensajeLeidoIzq;
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

