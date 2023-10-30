package com.example.mmt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistroExitoso extends AppCompatActivity {

    private TextView textViewtextView7;
    private TextView textViewtextView8;
    private TextView textViewtextView9;
    private TextView textViewtextView10;
    private TextView textViewtextView11;
    private TextView textViewtextView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_exitoso);


        
        textViewtextView7 = findViewById(R.id.textView7);
        textViewtextView8 = findViewById(R.id.textView8);
        textViewtextView9 = findViewById(R.id.textView9);
        textViewtextView10 = findViewById(R.id.textView10);
        textViewtextView11 = findViewById(R.id.textView11);
        textViewtextView5 = findViewById(R.id.textView5);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        String userId = user.getUid();

        DocumentReference docRef = db.collection("user").document(userId);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // El documento existe, puedes obtener los datos del usuario
                    String name = documentSnapshot.getString("name");
                    String email = documentSnapshot.getString("email");
                    String instru = documentSnapshot.getString("instru");
                    String ubic = documentSnapshot.getString("ubic");
                    String genero_mus = documentSnapshot.getString("genero_mus");
                    String fecha_nac = documentSnapshot.getString("fecha_nac");

                    // Muestra los datos del usuario en las TextView
                    textViewtextView7.setText("Nombre: " + name);
                    textViewtextView8.setText("Correo: " + email);
                    textViewtextView9.setText("Instrumento: " + instru);
                    textViewtextView10.setText("Ubicación: " + ubic);
                    textViewtextView11.setText("Genero Musical: " + genero_mus);
                    textViewtextView5.setText("Fecha de Nacimiento: " + fecha_nac);
                } else {
                    // El documento no existe, maneja este caso según tus necesidades
                }
            }
        });



    }

    public void launchLoginActivity(View view) {
        Intent intent = new Intent(RegistroExitoso.this, LoginActivity.class);
        startActivity(intent);
    }




}