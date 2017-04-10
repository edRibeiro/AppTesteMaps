package com.example.amd_fx.testeapptcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Principal extends AppCompatActivity {
    private static final String TAG = ">>>>>>>>>>";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    DatabaseReference myRef;
    DatabaseReference refDepositos;
    private ArrayList<Deposito> lista;
    // [END declare_database_ref]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // Write a message to the database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        refDepositos = database.getReference("depositos");
        lista = new ArrayList(3);
        myRef.setValue("Hello, World!");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });/*
        String[] desc = {"píscina", "sisterna", "caixa d´agua"};
        double[] lat = {-21.205488, -21.194948, -21.190662};
        double[] lon = {-41.8864761, -41.912335, -41.916364};
        for (int i = 0; i < 3; i++) {
            salvarNovoDeposito(desc[i], lat[i], lon[i]);
        }
*/
        //startActivity(new Intent(this, MostrarDepositos.class));
        carregaDados();
        Intent mfr = new Intent(this, MapaFocosRegistrados.class);
        mfr.putExtra("listaDepositos", lista);
        startActivity(mfr);
    }

    private void salvarNovoDeposito(String descricao, double latitude, double longitude) {
        Deposito dep = new Deposito(descricao, latitude, longitude);
        String key = mDatabase.child("depositos").push().getKey();
        Map<String, Object> depValues = dep.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/depositos/" + key, depValues);
        mDatabase.updateChildren(childUpdates);

    }

    private void carregaDados() {
        ValueEventListener depositosListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Deposito dep = new Deposito();
                    dep.setDescricao(ds.child("descricao").getValue(String.class));
                    dep.setLatitude(ds.child("latitude").getValue(Double.class));
                    dep.setLongitude(ds.child("longitude").getValue(Double.class));
                    Log.d("$LogPrincipal$", "Value is: " + dep.toString());
                    DepositoList.addDeposito(dep);
                    // ...
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("#################", "loadPost:onCancelled", databaseError.toException());
            }
        };
        refDepositos.addValueEventListener(depositosListener);

    }
}
