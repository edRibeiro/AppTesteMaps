package com.example.amd_fx.testeapptcc;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AMD-FX on 04/04/2017.
 */
@IgnoreExtraProperties
public class Deposito implements Parcelable {
    public String descricao;
    public double latitude;
    public double longitude;

    public Deposito(String descricao, double latitude, double longitude) {
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Deposito() {
    }


    protected Deposito(Parcel in) {
        descricao = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Deposito> CREATOR = new Creator<Deposito>() {
        @Override
        public Deposito createFromParcel(Parcel in) {
            return new Deposito(in);
        }

        @Override
        public Deposito[] newArray(int size) {
            return new Deposito[size];
        }
    };

    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public double getLatitude() {
        return latitude;
    }


    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public double getLongitude() {
        return longitude;
    }


    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("descricao", descricao);
        result.put("latitude", latitude);
        result.put("longitude", longitude);

        return result;
    }
    @Exclude

    @Override
    public String toString() {
        return "Deposito{" + descricao + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descricao);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
