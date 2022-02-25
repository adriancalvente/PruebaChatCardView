package com.herprogramacion.pruebachatcardview;

public class Receptor {
    private String mensaje;
    private int posicion;
    private String emisor;


    public Receptor() {
    }

    public Receptor(String mensaje) {
        this.mensaje = mensaje;
    }

    public Receptor(String mensaje, int posicion, String emisor) {
        this.mensaje = mensaje;
        this.posicion = posicion;
        this.emisor = emisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
