package com.example.mmt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

//  Button ingresarAlMuro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

/*      ingresarAlMuro = findViewById(R.id.ingresarAlMuro);
        ingresarAlMuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                startActivity(intent);
            }
      });*/
    }
    /*public void launchLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }*/
    public void launchPrincipalActivity (View view) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }


}