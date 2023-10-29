package com.example.mmt;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PublicacionSimple extends AppCompatActivity {

    Button btn_publicar;
    EditText title,creationDate,genere,location,content;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        title = findViewById(R.id.title);
        creationDate = findViewById(R.id.creationDate);
        genere = findViewById(R.id.genere);
        location = findViewById(R.id.location);
        content = findViewById(R.id.content);
        btn_publicar = findViewById(R.id.btn_publicar);

        setContentView(R.layout.activity_publicacion_simple);
        Log.d(TAG, "PublicacionSimple.java: On Create");

    }

    public void publicar(View view){

        Log.d(TAG, "PublicacionSimple.java: publicar - inicio");


        String id = mAuth.getCurrentUser().getUid();
        Log.d(TAG, "PublicacionSimple.java: publicar - id user actual: "+id);

        Map<String, Object> map = new HashMap<>();
        map.put("publisher", id);
        map.put("postType", "8KH6adb6tYXAGpJNigjQ");
        Log.d("PublicacionSimple.java", "publicar - map: "+map.toString());

        title = findViewById(R.id.title);
        creationDate = findViewById(R.id.creationDate);
        genere = findViewById(R.id.genere);
        location = findViewById(R.id.location);
        content = findViewById(R.id.content);
        Log.d("PublicacionSimple.java", "publicar - map: "+map.toString());


        map.put("title", title.getText().toString().trim());
        map.put("creationDate", creationDate.getText().toString().trim());
        map.put("genere", genere.getText().toString().trim());
        map.put("location", location.getText().toString().trim());
        map.put("content", content.getText().toString().trim());


//    EditText editText = findViewById(R.id.editTextUserInput);

        mFirestore.collection("post").add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        finish();
                        Toast.makeText(PublicacionSimple.this, "Publicación realizada con éxito", Toast.LENGTH_SHORT).show();

                        //Log.d(TAG, "DocumentSnapshot successfully written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "PublicacionSimple.java: Error al registrar la publicación", e);
                        Toast.makeText(PublicacionSimple.this, "Error al registrar la publicación", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}