package com.example.amd_fx.testeapptcc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MostrarDepositos extends AppCompatActivity {
    private static final String TAG = ">>>>>>>>>>";
    List<Deposito> lista;
    // [START declare_database_ref]
    private DatabaseReference mDatabase;

    // [END declare_database_ref]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_depositos);

        TextView r = (TextView) findViewById(R.id.resultado);
        mDatabase = FirebaseDatabase.getInstance().getReference("depositos");
        lista = new ArrayList(3);


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Deposito dep = new Deposito();
                    dep.setDescricao(ds.child("descricao").getValue(String.class));
                    dep.setLatitude(ds.child("latitude").getValue(Double.class));
                    dep.setLongitude(ds.child("longitude").getValue(Double.class));
                    Log.d(TAG, "Value is: " + dep.toString());
                    lista.add(dep);

                    // ...
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addValueEventListener(postListener);
        String texto="";
        for(Deposito p : lista){
            texto+=p.toString()+"\n";
        }
        r.setText(texto);
    }

}
