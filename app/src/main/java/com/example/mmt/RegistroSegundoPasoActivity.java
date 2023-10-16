package com.example.mmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroSegundoPasoActivity extends AppCompatActivity {
    Button btn_register;
    EditText name, email, password,fecha_nac,genero_mus,ubic,instru;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_segundo_paso);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.nombre);
        fecha_nac = findViewById(R.id.fechanac);
        genero_mus = findViewById(R.id.generomus);
        ubic = findViewById(R.id.ubicacion);
        instru = findViewById(R.id.instrumentos);
        email = findViewById(R.id.correo);
        password = findViewById(R.id.contrasena);
        btn_register = findViewById(R.id.btn_registro);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameUser = name.getText().toString().trim();
                String nacUser = fecha_nac.getText().toString().trim();
                String generoUser = genero_mus.getText().toString().trim();
                String ubicUser = ubic.getText().toString().trim();
                String instruUser = instru.getText().toString().trim();
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if (nameUser.isEmpty() && nacUser.isEmpty() && generoUser.isEmpty() && ubicUser.isEmpty() && instruUser.isEmpty() && emailUser.isEmpty() && passUser.isEmpty()){
                    Toast.makeText(RegistroSegundoPasoActivity.this, "Complete los datos", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(nameUser,nacUser,generoUser,ubicUser,instruUser, emailUser, passUser);
                }
            }
        });

    }
    private void registerUser(String nameUser,String nacUser,String generoUser,String ubicUser,String instruUser, String emailUser, String passUser) {
        mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("name", nameUser);
                map.put("fecha_nac", nacUser);
                map.put("genero_mus", generoUser);
                map.put("ubic", ubicUser);
                map.put("instru", instruUser);
                map.put("email", emailUser);
                map.put("password", passUser);

                mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(RegistroSegundoPasoActivity.this, RegistroExitoso.class));
                        Toast.makeText(RegistroSegundoPasoActivity.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistroSegundoPasoActivity.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistroSegundoPasoActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }




    /*public void launchRegistroExitosoActivity(View view) {
        Intent intent = new Intent(this, RegistroExitoso.class);
        startActivity(intent);
    }
    public void launchRegistroActivity(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }*/
}