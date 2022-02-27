package com.herprogramacion.Viber.threads;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MediaThread extends Thread {
    private final boolean exit = false;
    private VideoView videoView;
    private MediaPlayer mediaPlayer;
    private MediaController mediaController;

    public MediaThread(Context context, int resource, VideoView videoView) {
        try {

            this.videoView = videoView;
            this.videoView.setVideoPath("android.resource://" + context.getPackageName() + "/" + resource);
            mediaController = new MediaController(context);
            this.videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);

        } catch (Exception e) {
            Log.e("unexpected", "Unexpected error");
        }
    }

    public MediaThread(Context context, int resource) {
        mediaPlayer = MediaPlayer.create(context, resource);
    }

    @Override
    public void run() {

        try {
            if (videoView == null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            } else {
                videoView.setOnPreparedListener(mediaPlayer1 -> videoView.start());
            }
        } catch (Exception e) {
            Log.e("unexpected", "Unexpected error");
        }
    }


}
