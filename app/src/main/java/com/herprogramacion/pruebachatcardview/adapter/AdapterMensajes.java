package com.herprogramacion.pruebachatcardview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.herprogramacion.pruebachatcardview.Mensaje;
import com.herprogramacion.pruebachatcardview.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMensajes extends RecyclerView.Adapter<HolderMensaje> {

    List<Mensaje>listMensaje=new ArrayList<>();
    private Context c;

    public AdapterMensajes(Context c) {
        this.c = c;
    }
public void addMensaje(Mensaje m){
        if (m.getPosicion()==0){
            listMensaje.add(m);
        }else if(m.getPosicion()==1){
            listMensaje.add(m);
        }

    notifyItemInserted(listMensaje.size());
}

    @Override
    public HolderMensaje onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_mensaje,parent,false);
        return new HolderMensaje(v);
    }

    @Override
    public void onBindViewHolder(HolderMensaje holder, int position) {
        if (listMensaje.get(position).getPosicion()==0){
            holder.getMensaje().setText(listMensaje.get(position).getMensaje());
        }else if(listMensaje.get(position).getPosicion()==1){
            holder.getMensajeizq().setText(listMensaje.get(position).getMensaje());

        }


    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }
}
