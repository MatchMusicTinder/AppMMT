package com.example.mmt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.PopupMenu;


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
    FirebaseAuth mAuth;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private final List<User> resultsList = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        textViewdatosPublicante = findViewById(R.id.datosPublicante);
        searchView = findViewById(R.id.search);
        ImageButton menu = findViewById(R.id.menu);

        adapter = new UserAdapter(this, resultsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        searhView();

        if (user != null){
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

        }
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(PrincipalActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_profile1:  //Mi Perfil
                                Intent editarPerfilIntent = new Intent(PrincipalActivity.this, PerfilActivity.class);
                                startActivity(editarPerfilIntent);
                                return true;
                            case R.id.action_profile2:  //Perfil Banda
                                return true;
                            case R.id.action_profile3:  //Compartir
                                return true;
                            case R.id.action_profile4:  //Ajustes
                                return true;
                            case R.id.action_profile5:  //Cerrar Sesion
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(PrincipalActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });

    }

    public void searhView(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
           public boolean onQueryTextSubmit(String query) {
                textSearch(query);
                return true;
           }

                @Override
           public boolean onQueryTextChange(String newText) {
                textSearch(newText);
                return false;
           }
        });
    }


    private void textSearch(String query){
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

    public void launchPublicacionAudio(View view) {
        Intent intent = new Intent(this, PublicacionAudio.class);
        startActivity(intent);
    }


    public void botontienda(View view) {
        Intent intent = new Intent(this, TiendaActivity.class);
        startActivity(intent);
    }

    public void mp3(View view) {
        Intent intent = new Intent(this, VideosActivity.class);
        startActivity(intent);
    }

    public void mensajes(View view) {
        Intent intent = new Intent(this, MensajesActivity.class);
        startActivity(intent);
    }

}




