package com.example.mmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;

public class PrincipalActivity extends AppCompatActivity {
//    SearchView buscar;
//    private VideoView vdo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

//        buscar = findViewById(R.id.buscar);
//        buscar.setOnQueryTextListener(this);
//
//        int orientacion=getResources().getConfiguration().orientation;
//        if (orientacion == Configuration.ORIENTATION_LANDSCAPE){
//            getSupportActionBar().hide();
//        }else
//            getSupportActionBar().show();
//
//        vdo = findViewById(R.id.vdo);
//
//        vdo.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.pantera));
//
//        MediaController mdactrlr = new MediaController(this);
//        vdo.setMediaController(mdactrlr);
//        mdactrlr.setAnchorView(vdo);
//
//
//        vdo.start();
//    }
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        return false;
//    }
//     @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_profile1:
//                Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.action_profile2:
//                Toast.makeText(this, "Perfil Banda", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.action_profile3:
//                Toast.makeText(this, "Compartir", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.action_profile4:
//                Toast.makeText(this, "Ajustes", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
    }
}