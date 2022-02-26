package com.herprogramacion.Viber.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.herprogramacion.Viber.R;

public class HolderMensaje extends RecyclerView.ViewHolder {
    private ConstraintLayout msg;
    private ConstraintLayout msgIzq;
    private TextView mensaje;
    private TextView mensajeizq;
    private TextView tiempoDrch;
    private TextView tiempoIzq;

    public HolderMensaje(View itemView) {
        super(itemView);
        try {
            mensaje = itemView.findViewById(R.id.mensaje);
            mensajeizq = itemView.findViewById(R.id.mensajeizq);
            msg = itemView.findViewById(R.id.msgDrch);
            msgIzq = itemView.findViewById(R.id.msgIzq);
            tiempoDrch = itemView.findViewById(R.id.tiempoDrch);
            tiempoIzq = itemView.findViewById(R.id.tiempoIzq);
        } catch (Exception e) {
            Log.e("unexpected", "Unexpected error");
        }
    }

    public ConstraintLayout getMsg() {
        return msg;
    }

    public void setMsg(ConstraintLayout msg) {
        this.msg = msg;
    }

    public ConstraintLayout getMsgIzq() {
        return msgIzq;
    }

    public void setMsgIzq(ConstraintLayout msgIzq) {
        this.msgIzq = msgIzq;
    }

    public TextView getTiempoDrch() {
        return tiempoDrch;
    }

    public void setTiempoDrch(TextView tiempoDrch) {
        this.tiempoDrch = tiempoDrch;
    }

    public TextView getTiempoIzq() {
        return tiempoIzq;
    }

    public void setTiempoIzq(TextView tiempoIzq) {
        this.tiempoIzq = tiempoIzq;
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
