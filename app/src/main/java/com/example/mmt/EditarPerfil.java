package com.example.mmt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditarPerfil extends AppCompatActivity {


    private EditText textViewtextView8;
    private EditText textViewtextView9;
    private EditText textViewtextView10;
    private EditText textViewtextView11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);


        textViewtextView8 = findViewById(R.id.textView8);
        textViewtextView9 = findViewById(R.id.textView9);
        textViewtextView10 = findViewById(R.id.textView10);
        textViewtextView11 = findViewById(R.id.textView11);



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

                    String email = documentSnapshot.getString("email");
                    String instru = documentSnapshot.getString("instru");
                    String ubic = documentSnapshot.getString("ubic");
                    String genero_mus = documentSnapshot.getString("genero_mus");


                    // Muestra los datos del usuario en las TextView

                    textViewtextView8.setText("Correo: " + email);
                    textViewtextView9.setText("Instrumento: " + instru);
                    textViewtextView10.setText("Ubicación: " + ubic);
                    textViewtextView11.setText("Genero Musical: " + genero_mus);

                } else {
                    // El documento no existe, maneja este caso según tus necesidades
                }
            }


        });
    }
}