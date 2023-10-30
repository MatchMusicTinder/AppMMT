package com.example.mmt;

<<<<<<< HEAD:app/src/main/java/com/example/mmt/PublicacionAudio.java
=======
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

>>>>>>> Osvaldo-Diaz:app/src/main/java/com/example/mmt/VideosActivity.java
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PublicacionAudio {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion_audio);
    }

    public void miVideos(View view) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }
    public void BtnTienda(View view) {
        Intent intent = new Intent(this, TiendaActivity.class);
        startActivity(intent);
    }
}


