package com.herprogramacion.pruebachatcardview.fragments;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.chibde.visualizer.BarVisualizer;
import com.chibde.visualizer.CircleBarVisualizer;
import com.chibde.visualizer.CircleVisualizer;
import com.chibde.visualizer.LineBarVisualizer;
import com.chibde.visualizer.LineVisualizer;
import com.chibde.visualizer.SquareBarVisualizer;
import com.herprogramacion.pruebachatcardview.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Audio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Audio extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int repetir = 2, posicion = 0;
    MediaPlayer[] vectormp;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnPlay_pause, btnRepetir, cambioEspectro, btnStop, btnSiguente, btnAnterior;
    private MediaPlayer mp;
    private LineVisualizer lineVisualizer;
    private BarVisualizer barVisualizer;
    private CircleBarVisualizer circleBarVisualizer;
    private CircleVisualizer circleVisualizer;
    private SquareBarVisualizer squareBarVisualizer;
    private LineBarVisualizer lineBarVisualizer;
    private int cont = 0;

    public Audio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Audio.
     */
    // TODO: Rename and change types and number of parameters
    public static Audio newInstance(String param1, String param2) {
        Audio fragment = new Audio();
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
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_audio, container, false);
        mp = MediaPlayer.create(getActivity(), R.raw.video);

        cambioEspectro = inflate.findViewById(R.id.cambioEspectro);
        lineVisualizer = inflate.findViewById(R.id.visualizerLine);
        lineVisualizer.setColor(ContextCompat.getColor(getContext(), R.color.espectro));
        lineVisualizer.setStrokeWidth(1);

        barVisualizer = inflate.findViewById(R.id.visualizerBar);
        squareBarVisualizer = inflate.findViewById(R.id.visualizerSquareBar);
        circleBarVisualizer = inflate.findViewById(R.id.visualizerCircleBar);
        circleVisualizer = inflate.findViewById(R.id.visualizerCircle);
        lineBarVisualizer = inflate.findViewById(R.id.visualizerLineBar);

        vectormp = new MediaPlayer[5];
        vectormp[0] = MediaPlayer.create(getContext(), R.raw.estopa);
        vectormp[1] = MediaPlayer.create(getContext(), R.raw.quiereme);
        vectormp[2] = MediaPlayer.create(getContext(), R.raw.badbunny);
        vectormp[3] = MediaPlayer.create(getContext(), R.raw.joaquinsabina);
        vectormp[4] = MediaPlayer.create(getContext(), R.raw.chunguitos);

        btnStop = inflate.findViewById(R.id.btnStop);
        btnPlay_pause = inflate.findViewById(R.id.play);
        btnRepetir = inflate.findViewById(R.id.noRepetir);
        btnSiguente = inflate.findViewById(R.id.siguiente);
        btnAnterior = inflate.findViewById(R.id.anterior);


        btnPlay_pause.setOnClickListener(view -> {
            if (vectormp[posicion].isPlaying()) {
                vectormp[posicion].pause();
                btnPlay_pause.setBackgroundResource(R.drawable.reproducir);
            } else {
                btnPlay_pause.setBackgroundResource(R.drawable.pausa);
                objetosInvisibles();
                localizarEspectro(inflate);
                vectormp[posicion].start();
            }
        });

        btnStop.setOnClickListener(view -> {
            if (vectormp[posicion] != null) {
                vectormp[posicion].stop();
                vectormp[0] = MediaPlayer.create(getContext(), R.raw.estopa);
                vectormp[1] = MediaPlayer.create(getContext(), R.raw.quiereme);
                vectormp[2] = MediaPlayer.create(getContext(), R.raw.badbunny);
                vectormp[3] = MediaPlayer.create(getContext(), R.raw.joaquinsabina);
                vectormp[4] = MediaPlayer.create(getContext(), R.raw.chunguitos);
                posicion = 0;
                btnPlay_pause.setBackgroundResource(R.drawable.reproducir);
            }
        });

        btnRepetir.setOnClickListener(view -> {
            if (repetir == 1) {
                btnRepetir.setBackgroundResource(R.drawable.no_repetir);
                Toast.makeText(getContext(), "Repetición desactivada", Toast.LENGTH_SHORT).show();
                vectormp[posicion].setLooping(false);
                repetir = 2;
            } else {
                btnRepetir.setBackgroundResource(R.drawable.repetir);
                Toast.makeText(getContext(), "Repetición Activada", Toast.LENGTH_SHORT).show();
                vectormp[posicion].setLooping(true);
                repetir = 1;
            }

        });

        btnSiguente.setOnClickListener(view -> {
            if (posicion < vectormp.length - 1) {
                if (vectormp[posicion].isPlaying()) {
                    vectormp[posicion].stop();
                    posicion++;
                    vectormp[posicion].start();
                    objetosInvisibles();
                    localizarEspectro(inflate);
                } else {
                    posicion++;
                }

            } else {
                Toast.makeText(getContext(), "No hay mas canciones", Toast.LENGTH_SHORT).show();
            }
        });

        btnAnterior.setOnClickListener(view -> {
            if (posicion >= 1) {
                if (vectormp[posicion].isPlaying()) {
                    vectormp[posicion].stop();
                    posicion--;
                    vectormp[posicion].start();
                    objetosInvisibles();
                    localizarEspectro(inflate);
                } else {
                    posicion--;
                }
            } else {
                Toast.makeText(getContext(), "No hay mas canciones", Toast.LENGTH_SHORT).show();
            }
        });
        cambioEspectro.setOnClickListener(view -> {
            if (cont == 5) {
                cont = 0;
            } else {
                cont++;
            }
            objetosInvisibles();
            localizarEspectro(inflate);

        });

        return inflate;
    }

    private void localizarEspectro(View inflate) {
        switch (cont) {
            case 0:
                lineVisualizer.release();
                lineVisualizer.setVisibility(View.VISIBLE);
                // set a custom color to the line.
                lineVisualizer.setPlayer(vectormp[posicion].getAudioSessionId());
                break;
            case 1:
                barVisualizer.release();
                barVisualizer.setVisibility(View.VISIBLE);
                // set the custom color to the line.
                barVisualizer.setColor(ContextCompat.getColor(getContext(), R.color.espectro));
                // define a custom number of bars we want in the visualizer it is between (10 - 256).
                barVisualizer.setDensity(80);

                // Set your media player to the visualizer.
                barVisualizer.setPlayer(vectormp[posicion].getAudioSessionId());
                break;
            case 2:
                circleBarVisualizer.release();
                circleBarVisualizer.setVisibility(View.VISIBLE);

                // set the custom color to the line.
                circleBarVisualizer.setColor(ContextCompat.getColor(getContext(), R.color.espectro));

                // Set the media player to the visualizer.
                circleBarVisualizer.setPlayer(vectormp[posicion].getAudioSessionId());
                break;
            case 3:
                circleVisualizer.release();
                circleVisualizer.setVisibility(View.VISIBLE);

                // set custom color to the line.
                circleVisualizer.setColor(ContextCompat.getColor(getContext(), R.color.espectro));

                // Customize the size of the circle. by default, the multipliers are 1.
                circleVisualizer.setRadiusMultiplier(2.2f);

                // set the line with for the visualizer between 1-10 default 1.
                circleVisualizer.setStrokeWidth(2);

                // Set the media player to the visualizer.
                circleVisualizer.setPlayer(vectormp[posicion].getAudioSessionId());
                break;
            case 4:
                squareBarVisualizer.setVisibility(View.VISIBLE);

                // set custom color to the line.
                squareBarVisualizer.setColor(ContextCompat.getColor(inflate.getContext(), R.color.espectro));

                // define a custom number of bars you want in the visualizer between (10 - 256).
                squareBarVisualizer.setDensity(65);

                // Set Spacing
                squareBarVisualizer.setGap(2);

                // Set the media player to the visualizer.
                squareBarVisualizer.setPlayer(vectormp[posicion].getAudioSessionId());
                break;
            case 5:
                lineBarVisualizer.setVisibility(View.VISIBLE);

                // setting the custom color to the line.
                lineBarVisualizer.setColor(ContextCompat.getColor(inflate.getContext(), R.color.espectro));

                // define the custom number of bars we want in the visualizer between (10 - 256).
                lineBarVisualizer.setDensity(60);

                // Setting the media player to the visualizer.
                lineBarVisualizer.setPlayer(vectormp[posicion].getAudioSessionId());
                break;
        }


    }

    private void objetosInvisibles() {
        lineVisualizer.setVisibility(View.INVISIBLE);
        barVisualizer.setVisibility(View.INVISIBLE);
        circleBarVisualizer.setVisibility(View.INVISIBLE);
        circleVisualizer.setVisibility(View.INVISIBLE);
        squareBarVisualizer.setVisibility(View.INVISIBLE);
        lineBarVisualizer.setVisibility(View.INVISIBLE);
    }

}