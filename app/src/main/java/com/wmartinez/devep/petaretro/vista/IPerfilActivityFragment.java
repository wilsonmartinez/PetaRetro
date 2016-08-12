package com.wmartinez.devep.petaretro.vista;

import com.wmartinez.devep.petaretro.adapter.MascotaAdaptador;
import com.wmartinez.devep.petaretro.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 8/12/2016.
 */
public interface IPerfilActivityFragment {
    void generarLinearLayout();

    MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    void inicializarAdaptadorRV(MascotaAdaptador adaptador);

    void userPerfilData(Mascota userPerfil);
}
