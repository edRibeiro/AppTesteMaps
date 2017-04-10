package com.example.amd_fx.testeapptcc;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by AMD-FX on 06/04/2017.
 */

public class DepositoList extends AppCompatActivity {
    private static ArrayList<Deposito> depositos = new ArrayList<>(3);

    public static ArrayList<Deposito> getDepositos() {
        return depositos;
    }

    public static void setDepositos(ArrayList<Deposito> depositos) {
        DepositoList.depositos = depositos;
    }

    public static void addDeposito(Deposito deposito){
        DepositoList.depositos.add(deposito);
        Log.d("$LogDepositoList$", deposito.toString()+" Status: Adicionado!");
    }
}
