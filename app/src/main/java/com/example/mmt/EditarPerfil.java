package com.example.mmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditarPerfil extends AppCompatActivity {


    private EditText textViewtextView8;
    private EditText textViewtextView9;
    private EditText textViewtextView10;
    private EditText textViewtextView11;


    Button btn_guardar;
    DocumentReference docRef;
    FirebaseFirestore db;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);


        textViewtextView8 = findViewById(R.id.textView8);
        textViewtextView9 = findViewById(R.id.textView9);
        textViewtextView10 = findViewById(R.id.textView10);
        textViewtextView11 = findViewById(R.id.textView11);

        btn_guardar = findViewById(R.id.guardarDatos);


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

                    textViewtextView8.setText(email);
                    textViewtextView9.setText(instru);
                    textViewtextView10.setText(ubic);
                    textViewtextView11.setText(genero_mus);

                } else {
                    Toast.makeText(EditarPerfil.this, "Error al mostrar datos del perfil", Toast.LENGTH_SHORT).show();
                }
            }


        });

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nuevogenMus = textViewtextView11.getText().toString().trim();
                String nuevaUbic = textViewtextView10.getText().toString().trim();
                String nuevoInstru = textViewtextView9.getText().toString().trim();
                String nuevoCorreo = textViewtextView8.getText().toString().trim();

                if (nuevogenMus.isEmpty() && nuevaUbic.isEmpty() && nuevoInstru.isEmpty() && nuevoCorreo.isEmpty()) {
                    Toast.makeText(EditarPerfil.this, "Complete los datos", Toast.LENGTH_SHORT).show();
                } else {
                    editarUser(nuevoCorreo, nuevogenMus, nuevoInstru, nuevaUbic);
                }
            }
        });
    }


    private void editarUser(String nuevoCorreo, String nuevogenMus, String nuevoInstru, String nuevaUbic) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userId = user.getUid();
        DocumentReference docRef = db.collection("user").document(userId);



//                        String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("email", nuevoCorreo);
                map.put("genero_mus", nuevogenMus);
                map.put("instru", nuevoInstru);
                map.put("ubic", nuevaUbic);

                docRef.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(EditarPerfil.this, PerfilActivity.class));
                        Toast.makeText(EditarPerfil.this, "Usuario actualizado con éxito", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditarPerfil.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });
            }

}





