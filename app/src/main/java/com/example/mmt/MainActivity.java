package com.example.mmt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
    }
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // El usuario ya ha iniciado sesión, redirige a PrincipalActivity
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            finish(); // Cierra esta actividad para que el usuario no pueda volver atrás
        }
    }

    public void launchLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void launchRegisterActivity(View view) {
        Intent intent = new Intent(this, RegistroSegundoPasoActivity.class);
        startActivity(intent);
    }

}
