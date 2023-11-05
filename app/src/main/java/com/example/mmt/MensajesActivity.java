package com.example.mmt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MensajesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);
    }

    public void tienda(View view) {
        Intent intent = new Intent(this, TiendaActivity.class);
        startActivity(intent);
    }

    public void videos(View view) {
        Intent intent = new Intent(this, VideosActivity.class);
        startActivity(intent);
    }

    public void mp3icon(View view) {
        Intent intent = new Intent(this, PublicacionAudio.class);
        startActivity(intent);
    }

    public void miVideos(View view) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }
}