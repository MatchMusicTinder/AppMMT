package com.example.mmt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class PrincipalActivity extends AppCompatActivity{

    private TextView textViewdatosPublicante;
    Button btn_exit;
    private static final String VIDEO_SAMPLE = "pantera";

    FirebaseAuth mAuth;

    SearchView search_view;
    RecyclerView recyclerView;
    private UserAdapter adapter;
    private List<User> resultsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mAuth = FirebaseAuth.getInstance();

        textViewdatosPublicante = findViewById(R.id.datosPublicante);
        search_view = findViewById(R.id.serach);

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new UserAdapter(this, resultsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //FirebaseUser user = mAuth.getCurrentUser();

        assert user != null;
        String userId = user.getUid();

        DocumentReference docRef = db.collection("user").document(userId);

        searchView();
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
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

    private void searchView() {
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                textSearch(query);
                //recyclerView.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                textSearch(newText);
                return false;
            }
        });
    }

    private void textSearch(String query) {
        CollectionReference userCollection = FirebaseFirestore.getInstance().collection("user");

        Query userQuery = userCollection.whereEqualTo("name", query);

        userQuery.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                resultsList.clear();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots){
                    User user = document.toObject(User.class);
                    resultsList.add(user);
                }

                adapter.notifyDataSetChanged();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

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

    public void BtnTienda(View view) {
        Intent intent = new Intent(this, TiendaActivity.class);
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