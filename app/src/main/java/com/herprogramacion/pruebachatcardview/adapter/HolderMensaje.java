package com.herprogramacion.pruebachatcardview.adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.herprogramacion.pruebachatcardview.R;

public class HolderMensaje extends RecyclerView.ViewHolder {
    private TextView mensaje;
    private TextView mensajeizq;
    public HolderMensaje(View itemView) {
        super(itemView);
        mensaje = (TextView) itemView.findViewById(R.id.mensaje);
        mensajeizq=(TextView) itemView.findViewById(R.id.mensajeizq);
    }

    public TextView getMensaje() {
        mensaje.setVisibility(View.VISIBLE);
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getMensajeizq() {
        mensajeizq.setVisibility(View.VISIBLE);
        return mensajeizq;
    }

    public void setMensajeizq(TextView mensajeizq) {
        this.mensajeizq = mensajeizq;
    }

}
