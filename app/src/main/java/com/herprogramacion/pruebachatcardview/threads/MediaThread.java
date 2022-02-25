package com.herprogramacion.pruebachatcardview.threads;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.MediaController;
import android.widget.VideoView;

import com.herprogramacion.pruebachatcardview.R;

public class MediaThread extends Thread {
    private VideoView videoView;
    private MediaPlayer mediaPlayer;
    private MediaController mediaController;
    private boolean exit = false;

    public MediaThread(Context context, int resource, VideoView videoView) {
        this.videoView = videoView;
        this.videoView.setVideoPath("android.resource://" + context.getPackageName() + "/" + resource);
        mediaController = new MediaController(context);
        this.videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    public MediaThread(Context context, int resource) {
        mediaPlayer = MediaPlayer.create(context, resource);
    }

    @Override
    public void run() {
        if (videoView == null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        } else {
            videoView.setOnPreparedListener(mediaPlayer1 -> videoView.start());
        }
    }


}
