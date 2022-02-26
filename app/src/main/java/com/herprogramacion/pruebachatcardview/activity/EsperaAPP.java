package com.herprogramacion.pruebachatcardview.activity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.os.AsyncTask;
import android.view.Window;

public class EsperaAPP extends AsyncTask<Void, Integer, Integer> {

    @SuppressLint("StaticFieldLeak")
    private final ActividadRegistro actividadRegistro;

    public EsperaAPP(ActividadRegistro actividadRegistro) {
        this.actividadRegistro = actividadRegistro;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        actividadRegistro.pb.setMax(100);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        actividadRegistro.pb.setProgress(values[0]);

    }

    @Override
    protected Integer doInBackground(Void... arg0) {


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Integer result) {
        actividadRegistro.startActivity(actividadRegistro.intent,ActivityOptions.makeSceneTransitionAnimation(actividadRegistro).toBundle());
    }
}
