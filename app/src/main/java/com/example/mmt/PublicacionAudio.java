package com.example.mmt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PublicacionAudio extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion_audio);
    }


    public void videos(View view) {
        Intent intent = new Intent(this, VideosActivity.class);
        startActivity(intent);
    }

    public void tienda(View view) {
        Intent intent = new Intent(this, TiendaActivity.class);
        startActivity(intent);
    }

    public void mensajes(View view) {
        Intent intent = new Intent(this, MensajesActivity.class);
        startActivity(intent);
    }

    public void almuro(View view) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }
}


