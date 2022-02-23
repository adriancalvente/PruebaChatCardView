package com.herprogramacion.pruebachatcardview.adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.herprogramacion.pruebachatcardview.R;

public class HolderMensaje extends RecyclerView.ViewHolder {
    ConstraintLayout msg;
    ConstraintLayout msgIzq;
    private TextView mensaje;
    private TextView mensajeizq;
    private TextView tiempoDrch;
    private TextView tiempoIzq;
    public HolderMensaje(View itemView) {
        super(itemView);
        mensaje = (TextView) itemView.findViewById(R.id.mensaje);
        mensajeizq=(TextView) itemView.findViewById(R.id.mensajeizq);
        msg = (ConstraintLayout) itemView.findViewById(R.id.msgDrch);
        msgIzq = (ConstraintLayout) itemView.findViewById(R.id.msgIzq);
        tiempoDrch = (TextView) itemView.findViewById(R.id.tiempoDrch);
        tiempoIzq = (TextView) itemView.findViewById(R.id.tiempoIzq);
    }

    public ConstraintLayout getMsg() {
        msg.setVisibility(View.VISIBLE);
        return msg;
    }

    public void setMsg(ConstraintLayout msg) {
        this.msg = msg;
    }

    public TextView getTiempoDrch() {
        return tiempoDrch;
    }

    public TextView getTiempoIzq() {
        return tiempoIzq;
    }

    public void setTiempoIzq(TextView tiempoIzq) {
        this.tiempoIzq = tiempoIzq;
    }

    public void setTiempoDrch(TextView tiempoDrch) {
        this.tiempoDrch = tiempoDrch;
    }

    public ConstraintLayout getMsgIzq() {
        msgIzq.setVisibility(View.VISIBLE);
        return msgIzq;
    }

    public void setMsgIzq(ConstraintLayout msgIzq) {
        this.msgIzq = msgIzq;
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
