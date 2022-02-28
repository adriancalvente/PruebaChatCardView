package com.herprogramacion.Viber.fragments;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.chibde.visualizer.BarVisualizer;
import com.chibde.visualizer.CircleBarVisualizer;
import com.chibde.visualizer.CircleVisualizer;
import com.chibde.visualizer.LineBarVisualizer;
import com.chibde.visualizer.LineVisualizer;
import com.chibde.visualizer.SquareBarVisualizer;
import com.herprogramacion.Viber.R;

import java.util.concurrent.TimeUnit;

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
    private LineVisualizer lineVisualizer;
    private BarVisualizer barVisualizer;
    private CircleBarVisualizer circleBarVisualizer;
    private CircleVisualizer circleVisualizer;
    private SquareBarVisualizer squareBarVisualizer;
    private LineBarVisualizer lineBarVisualizer;
    private SeekBar seekBar;
    Animation myAnim, rotacion,rotacionIzquierda;
    private ImageView notaMusical;
    Runnable runnable;
    Handler handler;
    private TextView totalCancion;
    private TextView progresoCancion;
    private int cont = 0;
    private Toast customToast;


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
    @SuppressLint({"ResourceType", "SdCardPath"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_audio, container, false);
        declararObjetos(inflate);
        btnPlay_pause.setOnClickListener(view -> {
            btnPlay_pause.startAnimation(myAnim);
            if (vectormp[posicion].isPlaying()) {
                vectormp[posicion].pause();
                btnPlay_pause.setBackgroundResource(R.drawable.reproducir);
            } else {
                reproducirMusica();


            }
        });
        btnStop.setOnClickListener(view -> {
            if (vectormp[posicion] != null) {
                stopMusica();
            }
        });
        btnRepetir.setOnClickListener(view -> {
            btnRepetir.startAnimation(myAnim);
            if (repetir == 1) {
                btnRepetir.setBackgroundResource(R.drawable.no_repetir);
                btnRepetir.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                Toast.makeText(getContext(), "Repetición desactivada", Toast.LENGTH_SHORT).show();
                vectormp[posicion].setLooping(false);
                repetir = 2;
            } else {
                btnRepetir.setBackgroundResource(R.drawable.repetir);
                btnRepetir.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#BE0527")));
                Toast.makeText(getContext(), "Repetición Activada", Toast.LENGTH_SHORT).show();
                vectormp[posicion].setLooping(true);
                repetir = 1;
            }

        });

        btnSiguente.setOnClickListener(view -> {
            btnSiguente.startAnimation(myAnim);
            if (posicion < vectormp.length - 1) {
                if (vectormp[posicion].isPlaying()) {
                    reproducirSiguinteCancion();
                    objetosInvisibles();
                    localizarEspectro();
                } else {
                    posicion++;
                }
            } else {
                customToast.show();
            }
        });

        btnAnterior.setOnClickListener(view -> {
            btnAnterior.startAnimation(myAnim);
            if (posicion >= 1) {
                if (vectormp[posicion].isPlaying()) {
                    vectormp[posicion].stop();
                    setCanciones();
                    posicion--;
                    notaMusical.startAnimation(rotacionIzquierda);
                    vectormp[posicion].start();
                    objetosInvisibles();
                    localizarEspectro();
                } else {
                    posicion--;
                    setCanciones();
                }
            } else {
                customToast.show();
            }
        });

        cambioEspectro.setOnClickListener(view -> {
            if (cont == 5) {
                cont = 0;
            } else {
                cont++;
            }
            objetosInvisibles();
            localizarEspectro();

        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                vectormp[posicion].seekTo(seekBar.getProgress());
            }
        });
        return inflate;
    }

    private void stopMusica() {
        btnStop.startAnimation(myAnim);
        vectormp[posicion].stop();
        setCanciones();
        posicion = 0;
        btnPlay_pause.setBackgroundResource(R.drawable.reproducir);
    }

    private void updateSeekBar() {
        int currpos = vectormp[posicion].getCurrentPosition();
        String endTime = formatDuration(vectormp[posicion].getDuration());
        totalCancion.setText(endTime);
        seekBar.setMax(vectormp[posicion].getDuration());
        String progressSong = formatDuration(currpos);
        progresoCancion.setText(progressSong);
        seekBar.setProgress(currpos);
        if ((vectormp[posicion].getDuration()-200)<=currpos) {
            reproducirSiguinteCancion();
        }
        runnable=new Runnable() {
            @Override
            public void run() {

                    updateSeekBar();

            }
        };

        handler.postDelayed(runnable, 1000);
        }


    private void reproducirSiguinteCancion() {
        if (posicion < vectormp.length - 1) {
            vectormp[posicion].stop();
            notaMusical.startAnimation(rotacion);
            posicion++;

            reproducirMusica();

        }else{
           Toast.makeText(getContext(),"No hay mas canciones",Toast.LENGTH_SHORT).show();
            stopMusica();
        }
    }

    private void reproducirMusica() {
        btnPlay_pause.setBackgroundResource(R.drawable.pausa);
        vectormp[posicion].start();
        objetosInvisibles();
        localizarEspectro();
        updateSeekBar();
    }


    @SuppressLint("SdCardPath")
    private void declararObjetos(View inflate) {
        LayoutInflater inflaterCustom = getLayoutInflater();
        View toastView = inflaterCustom.inflate(R.layout.custom_toast, inflate.findViewById(R.id.toast_custom));
        TextView textToast = toastView.findViewById(R.id.tvCustomToast);
        myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.rebote);
        rotacion = AnimationUtils.loadAnimation(getContext(), R.anim.rotacion_derecha);
        rotacionIzquierda= AnimationUtils.loadAnimation(getContext(), R.anim.rotacion_izquierda);
        textToast.setText("No hay mas canciones");
        customToast = new Toast(getContext());
        customToast.setView(toastView);
        totalCancion = inflate.findViewById(R.id.TotalCancion);
        progresoCancion = inflate.findViewById(R.id.cambioTiempo);
        seekBar = inflate.findViewById(R.id.barraMusica);
        cambioEspectro = inflate.findViewById(R.id.cambioEspectro);
        lineVisualizer = inflate.findViewById(R.id.visualizerLine);
        barVisualizer = inflate.findViewById(R.id.visualizerBar);
        squareBarVisualizer = inflate.findViewById(R.id.visualizerSquareBar);
        circleBarVisualizer = inflate.findViewById(R.id.visualizerCircleBar);
        circleVisualizer = inflate.findViewById(R.id.visualizerCircle);
        lineBarVisualizer = inflate.findViewById(R.id.visualizerLineBar);
        vectormp = new MediaPlayer[5];
        handler = new Handler();
        notaMusical = inflate.findViewById(R.id.notaMusical);

        setCanciones();
        btnStop = inflate.findViewById(R.id.btnStop);
        btnPlay_pause = inflate.findViewById(R.id.play);
        btnRepetir = inflate.findViewById(R.id.noRepetir);
        btnSiguente = inflate.findViewById(R.id.siguiente);
        btnAnterior = inflate.findViewById(R.id.anterior);
    }

    @SuppressLint("DefaultLocale")
    private String formatDuration(long duration) {
        long minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS);
        long seconds = TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS) - minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES);
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void setCanciones() {
        vectormp[0] = MediaPlayer.create(getContext(), R.raw.estopa);
        vectormp[1] = MediaPlayer.create(getContext(), R.raw.quiereme);
        vectormp[2] = MediaPlayer.create(getContext(), R.raw.badbunny);
        vectormp[3] = MediaPlayer.create(getContext(), R.raw.joaquinsabina);
        vectormp[4] = MediaPlayer.create(getContext(), R.raw.chunguitos);
    }

    public void localizarEspectro() {
        switch (cont) {
            case 0:
                activarLineVisualicer();
                break;
            case 1:
                activarBarVisualizer();
                break;
            case 2:
                activarCircleVisualicer();
                break;
            case 3:
                activarCircleVisualizer();
                break;
            case 4:
                activarSquareBarVisualizer();
                break;
            case 5:
                activarLineBarVisualizer();
                break;
        }


    }

    private void activarLineBarVisualizer() {
        lineBarVisualizer.setVisibility(View.VISIBLE);
        lineBarVisualizer.setColor(ContextCompat.getColor(getContext(), R.color.espectro));
        lineBarVisualizer.setDensity(60);
        lineBarVisualizer.setPlayer(vectormp[posicion].getAudioSessionId());
    }

    private void activarSquareBarVisualizer() {
        squareBarVisualizer.setVisibility(View.VISIBLE);
        squareBarVisualizer.setColor(ContextCompat.getColor(getContext(), R.color.espectro));
        squareBarVisualizer.setDensity(65);
        squareBarVisualizer.setGap(2);
        squareBarVisualizer.setPlayer(vectormp[posicion].getAudioSessionId());
    }

    private void activarCircleVisualizer() {
        circleVisualizer.release();
        circleVisualizer.setVisibility(View.VISIBLE);
        circleVisualizer.setColor(ContextCompat.getColor(getContext(), R.color.espectro));
        circleVisualizer.setRadiusMultiplier(2.2f);
        circleVisualizer.setStrokeWidth(2);
        circleVisualizer.setPlayer(vectormp[posicion].getAudioSessionId());
    }

    private void activarCircleVisualicer() {
        circleBarVisualizer.release();
        circleBarVisualizer.setVisibility(View.VISIBLE);
        circleBarVisualizer.setColor(ContextCompat.getColor(getContext(), R.color.espectro));
        circleBarVisualizer.setPlayer(vectormp[posicion].getAudioSessionId());
    }

    private void activarBarVisualizer() {
        barVisualizer.release();
        barVisualizer.setVisibility(View.VISIBLE);
        barVisualizer.setColor(ContextCompat.getColor(getContext(), R.color.espectro));
        barVisualizer.setDensity(80);
        barVisualizer.setPlayer(vectormp[posicion].getAudioSessionId());
    }

    private void activarLineVisualicer() {
        lineVisualizer.release();
        lineVisualizer.setVisibility(View.VISIBLE);
        lineVisualizer.setColor(ContextCompat.getColor(getContext(), R.color.espectro));
        lineVisualizer.setStrokeWidth(1);
        lineVisualizer.setPlayer(vectormp[posicion].getAudioSessionId());
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