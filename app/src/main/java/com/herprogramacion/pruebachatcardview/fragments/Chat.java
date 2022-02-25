package com.herprogramacion.pruebachatcardview.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.herprogramacion.pruebachatcardview.R;
import com.herprogramacion.pruebachatcardview.activity.MainActivity;
import com.herprogramacion.pruebachatcardview.adapter.AdapterMensajes;
import com.herprogramacion.pruebachatcardview.adapter.Mensaje;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Chat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Chat extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView rvMensajes;
    String id;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View inflate;
    private EditText txtMensaje;
    private Button btnEnviar;
    private AdapterMensajes adapter;
    private DateFormat dateFormat;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public Chat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment chat.
     */
    // TODO: Rename and change types and number of parameters
    public static Chat newInstance(String param1, String param2) {
        Chat fragment = new Chat();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @SuppressLint("HardwareIds")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_chat, container, false);
        // Inflate the layout for this fragment
        id = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        try {
            declararObjetos();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        btnEnviar.setOnClickListener(view -> {
            System.out.println(MainActivity.strUsuario);
            Date date = new Date();
            databaseReference.child(String.valueOf(adapter.getItemCount())).setValue(new Mensaje(MainActivity.strUsuario, txtMensaje.getText().toString(), 0, id, dateFormat.format(date)));

            txtMensaje.setText("");

        });


        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, String previousChildName) {
                Mensaje m = snapshot.getValue(Mensaje.class);
                assert m != null;
                if (m.getId().equals(id)) {
                    adapter.addMensaje(m);
                } else if (!m.getId().equals(id)) {
                    m.setMensaje(m.getUsuario() + ": " + m.getMensaje());
                    m.setPosicion(1);
                    adapter.addMensaje(m);
                }


            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return inflate;
    }

    @SuppressLint("SimpleDateFormat")
    private void declararObjetos() {
        txtMensaje = (EditText) inflate.findViewById(R.id.txtMensaje);
        btnEnviar = (Button) inflate.findViewById(R.id.btnEnviar);
        rvMensajes = (RecyclerView) inflate.findViewById(R.id.rvMensajes);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");
        dateFormat = new SimpleDateFormat("HH:mm");
        adapter = new AdapterMensajes(getContext());
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);
    }

    private void setScrollbar() {
        rvMensajes.scrollToPosition(adapter.getItemCount() - 1);
    }
}