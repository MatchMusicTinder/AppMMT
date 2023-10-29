package com.example.mmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class PrincipalActivity extends AppCompatActivity{

    private TextView textViewdatosPublicante;
    private static final String VIDEO_SAMPLE = "pantera";

    Button btn_exit;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mAuth = FirebaseAuth.getInstance();

        textViewdatosPublicante = findViewById(R.id.datosPublicante);

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
                    // Muestra los datos del usuario en las TextView
                    textViewdatosPublicante.setText("Nombre: " + name);
                } else {
                    // El documento no existe, maneja este caso según tus necesidades
                }
            }
        });


        btn_exit = findViewById(R.id.btn_close);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(PrincipalActivity.this, LoginActivity.class));
            }
        });



    }


    public void perfil(View view) {
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);
    }


    public void lauchReproducirvideo(View view) {
        Intent intent = new Intent(this, ReproductorDeVideo.class);
        startActivity(intent);
    }

    public void launchPublicacionSimple(View view) {
        Intent intent = new Intent(this, PublicacionSimple.class);
        startActivity(intent);
    }

    public void launchPublicacionAudio(View view) {
        Intent intent = new Intent(this, PublicacionAudio.class);
        startActivity(intent);
    }

}