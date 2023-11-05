package com.example.mmt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VideosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
    }


    public void BtnTienda(View view) {
        Intent intent = new Intent(this, TiendaActivity.class);
        startActivity(intent);
    }

    public void miVideos(View view) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

    public void mp3icon(View view) {
        Intent intent = new Intent(this, PublicacionAudio.class);
        startActivity(intent);
    }

    public void mensajes(View view) {
        Intent intent = new Intent(this, MensajesActivity.class);
        startActivity(intent);
    }
}


