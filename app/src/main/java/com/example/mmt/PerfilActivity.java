package com.example.mmt;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PerfilActivity extends AppCompatActivity {

    private TextView textViewtextView7;
    private TextView textViewtextView8;
    private TextView textViewtextView9;
    private TextView textViewtextView10;
    private TextView textViewtextView11;
    private TextView textViewtextView5;
    FloatingActionButton floatingActionButton2;
    FloatingActionButton floatingActionButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        textViewtextView7 = findViewById(R.id.textView7);
        textViewtextView8 = findViewById(R.id.textView8);
        textViewtextView9 = findViewById(R.id.textView9);
        textViewtextView10 = findViewById(R.id.textView10);
        textViewtextView11 = findViewById(R.id.textView11);
        textViewtextView5 = findViewById(R.id.textView5);
        floatingActionButton2 = findViewById(R.id.floatingActionButton2);
        floatingActionButton3 = findViewById(R.id.floatingActionButton3);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        FirebaseUser currentUser = mAuth.getCurrentUser();

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

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerfilActivity.this, EditarPerfil.class));

            }
        });

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mostrarDialogo();
            }
        });
    }

        private void mostrarDialogo () {

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            FirebaseUser currentUser = mAuth.getCurrentUser();


            new AlertDialog.Builder(this)
                    .setTitle("Eliminar Perfil")
                    .setMessage("¿estas seguro deseas eliminar tu cuenta?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            currentUser.delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {


                                                db.collection("user").document(currentUser.getUid())
                                                        .delete()
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {

                                                                mAuth.signOut();
                                                                finish();
                                                                startActivity(new Intent(PerfilActivity.this, MainActivity.class));


                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {

                                                            }
                                                        });
                                            } else {
                                                // Error al eliminar el perfil de Firebase Authentication
                                            }
                                        }
                                    });
                        }


                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d("Mensaje", "Se cancelo la acción");
                        }
                    })
                    .show();
        }

}

