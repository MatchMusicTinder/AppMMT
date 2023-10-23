package com.example.mmt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class PrincipalActivity extends AppCompatActivity{
    private static final String VIDEO_SAMPLE = "pantera";
    private VideoView mVideoView;
    Button btn_exit;
    FirebaseAuth mAuth;


    private Uri getMedia() {
        return Uri.parse("android.resource://" + getPackageName() + "/raw/" + PrincipalActivity.VIDEO_SAMPLE);
    }

    private static final String PLAYBACK_TIME = "play_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mAuth = FirebaseAuth.getInstance();
        mVideoView = findViewById(R.id.videoview);
        //if (savedInstanceState != null) {
          //  int mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        //}
        MediaController controlador  = new MediaController(this) ;
        controlador.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(controlador);

        btn_exit = findViewById(R.id.btn_close);
        btn_exit.setOnClickListener(view -> {
            mAuth.signOut();
            finish();
            startActivity(new Intent(PrincipalActivity.this, LoginActivity.class));
        });



    }

    private void initializePlayer() {
//
        Uri videoUri = getMedia();
        mVideoView.setVideoURI(videoUri);
//        if (mCurrentPosition > 0) {
//            mVideoView.seekTo(mCurrentPosition);
//        } else {
//// Saltar a 1 muestra el primer cuadro del video.
//            mVideoView.seekTo(1);
//        }
//
       mVideoView.start();
//        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                Toast.makeText(PrincipalActivity.this, "Reproducción Completa",
//                        Toast.LENGTH_SHORT).show();
//                mVideoView.seekTo(1);
//            }
//        });
//
//
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

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
        }
    }

}