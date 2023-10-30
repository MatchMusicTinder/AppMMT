package com.example.mmt;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

<<<<<<< HEAD
public class ReproductorDeVideo {
=======
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ReproductorDeVideo extends AppCompatActivity {
>>>>>>> 5b22c352ba11ed49852fe3cf5ecb4ef45c6963fd
    private static final String VIDEO_SAMPLE = "pantera";
    private VideoView mVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_de_video);
        mVideoView = findViewById(R.id.videoview);
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }
        MediaController controlador  = new MediaController(this) ;
        controlador.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(controlador);
    }
    private void initializePlayer() {

        Uri videoUri = getMedia(VIDEO_SAMPLE);
        mVideoView.setVideoURI(videoUri);
        mVideoView.start();

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PLAYBACK_TIME, mVideoView.getCurrentPosition());
    }

    private void releasePlayer() {
        mVideoView.stopPlayback();
    }
    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer();
    }
    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
        }
    }
    private Uri getMedia(String mediaName) {
        return Uri.parse("android.resource://" + getPackageName() +
                "/raw/" + mediaName);
    }
    private int mCurrentPosition = 0;
    private static final String PLAYBACK_TIME = "play_time";

}