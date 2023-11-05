package com.example.mmt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TiendaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);


    }

    public void almuro(View view) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

    public void Btnvideo(View view) {
        Intent intent = new Intent(this, VideosActivity.class);
        startActivity(intent);
    }

    public void amp3(View view) {
        Intent intent = new Intent(this, PublicacionAudio.class);
        startActivity(intent);
    }

    public void mensajes(View view) {
        Intent intent = new Intent(this, MensajesActivity.class);
        startActivity(intent);
    }
}