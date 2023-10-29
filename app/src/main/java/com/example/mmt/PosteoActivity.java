package com.example.mmt;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class PosteoActivity extends AppCompatActivity {


    Button crear_posteo;
    EditText campo_titulo, campo_descripcion;
    LinearLayout linearLayout_image_btn;
    private FirebaseFirestore mfirestore;
    private FirebaseAuth mAuth;

    StorageReference storageReference;
    String storage_path = "posteo/*";

    private static final int COD_SEL_STORAGE = 200;
    private static final int COD_SEL_VIDEO = 300;

    String video;
    String idd;
    private Uri video_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posteo2);
        mfirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        DocumentReference id = mfirestore.collection("posteo").document();

        linearLayout_image_btn = findViewById(R.id.images_btn);
        campo_titulo = findViewById(R.id.titulo);
        campo_descripcion = findViewById(R.id.descripcion);

        crear_posteo = findViewById(R.id.btn_subir);

        crear_posteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloPosteo = campo_titulo.getText().toString().trim();
                String descrpcionPosteo = campo_descripcion.getText().toString().trim();
                String idUser = mAuth.getCurrentUser().getUid();
                DocumentReference id = mfirestore.collection("posteo").document();
                if (tituloPosteo.isEmpty() && descrpcionPosteo.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                } else {

                    uploadVideo();

                }
            }
        });
    }

    private void uploadVideo() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("video/*");

        startActivityForResult(i, COD_SEL_VIDEO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if (requestCode == COD_SEL_VIDEO){
                assert data != null;
                Uri video_url = data.getData();
                subirVideo(video_url);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void subirVideo(Uri video_url) {
        DocumentReference id = mfirestore.collection("posteo").document();
        String rute_storage_video = storage_path + "" + video + "" + mAuth.getUid() + "" + id.getId() ;
        StorageReference reference = storageReference.child(rute_storage_video);

        reference.putFile(video_url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String download_uri = uri.toString();
                        crearPosteo(download_uri);
                        Toast.makeText(PosteoActivity.this, "Video actualizado", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PosteoActivity.this, "Error al cargar foto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void crearPosteo(String video_url) {
        String idUser = mAuth.getCurrentUser().getUid();
        DocumentReference id = mfirestore.collection("posteo").document();
        String tituloPosteo = campo_titulo.getText().toString().trim();
        String descrpcionPosteo = campo_descripcion.getText().toString().trim();


        Map<String, Object> map = new HashMap<>();
        map.put("id_user", idUser);
        map.put("id", id.getId());
        map.put("titulo", tituloPosteo);
        map.put("descripcion", descrpcionPosteo);
        map.put("video", video_url);

        mfirestore.collection("posteo").document(id.getId()).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
            }
        });

    }

}