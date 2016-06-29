package com.wmartinez.devep.petaretro.vista;

import com.wmartinez.devep.petaretro.adapter.MascotaAdaptador;
import com.wmartinez.devep.petaretro.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public interface ILikeMascotaView {
    void generarLinearLayouVertical();
    MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
