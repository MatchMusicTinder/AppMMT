package com.example.mmt;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button btn_login, btn_register;
    EditText email, password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.correo);
        password = findViewById(R.id.contrasena);
        btn_login = findViewById(R.id.btn_ingresar);
        btn_register = findViewById(R.id.btn_register);

        btn_login.setOnClickListener(view -> {
            String emailUser = email.getText().toString().trim();
            String passUser = password.getText().toString().trim();

            if (emailUser.isEmpty() && passUser.isEmpty()){
                Toast.makeText(LoginActivity.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
            }else{
                loginUser(emailUser, passUser);
            }
        });

        btn_register.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegistroSegundoPasoActivity.class)));

    }

    private void loginUser(String emailUser, String passUser) {
       mAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(task -> {
           if (task.isSuccessful()){
               finish();
               startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
               Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
           }
       }).addOnFailureListener(e -> Toast.makeText(LoginActivity.this, "Error al inciar sesión", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
            finish();
        }
    }

}