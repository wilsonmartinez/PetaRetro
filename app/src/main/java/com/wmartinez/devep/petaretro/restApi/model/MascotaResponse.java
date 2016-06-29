package com.wmartinez.devep.petaretro.restApi.model;

import com.wmartinez.devep.petaretro.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public class MascotaResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
