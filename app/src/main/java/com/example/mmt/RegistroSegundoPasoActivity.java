package com.example.mmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistroSegundoPasoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_segundo_paso);
    }
    public void launchRegistroExitosoActivity(View view) {
        Intent intent = new Intent(this, RegistroExitoso.class);
        startActivity(intent);
    }
    public void launchRegistroActivity(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
}