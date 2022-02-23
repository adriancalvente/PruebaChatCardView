package com.herprogramacion.pruebachatcardview;

public class Receptor {
    private String id;
    private boolean leido;

    public Receptor(boolean leido) {
        this.leido = leido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}
