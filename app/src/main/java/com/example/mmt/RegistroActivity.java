package com.example.mmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

//    private EditText txtUsuario;
//    private EditText txtUbicacion;
//    private EditText fechaNacim;
//    private EditText txtEmail;
//    private EditText txtGeneroMus;
//
//    private UserService userService = new UserService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }
    public void launchRegistroSegundoPasoActivity(View view) {
        Intent intent = new Intent(this, RegistroSegundoPasoActivity.class);
        startActivity(intent);
    }

//    public void registrarUsuario (View view) throws InterruptedException {
//
//
//        String usuario = txtUsuario.getText().toString();
//        String ubicacion = txtUbicacion.getText().toString();
//        String fechaNacimiento = fechaNacim.getText().toString();
//        String email = txtEmail.getText().toString();
//        String generomusical = txtGeneroMus.getText().toString();
//        Message message = userService.register(usuario,ubicacion,fechaNacimiento,email,generomusical);
//
//        if (message.isOK()){
//            Toast.makeText(this, message.getMessage(), Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this,MainActivity.class);
//            Thread.sleep(3000);
//            startActivity(intent);
//        } else {
//            Toast.makeText(this, message.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//
//    }


}