package com.wmartinez.devep.petaretro.vista;

import com.wmartinez.devep.petaretro.adapter.PerfilMascotaAdaptador;
import com.wmartinez.devep.petaretro.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 6/28/2016.
 */
public interface IPerfilFragmentView {
    void generarGridLayout();
    PerfilMascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    void inicializarAdaptadorRV(PerfilMascotaAdaptador adaptador);

    void userPerfilData(Mascota userPerfil);
}
