package com.wmartinez.devep.petaretro.presentador;

import android.content.Context;

import com.wmartinez.devep.petaretro.db.ConstructorMascotas;
import com.wmartinez.devep.petaretro.pojo.Mascota;
import com.wmartinez.devep.petaretro.vista.ILikeMascotaView;

import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public class LikeMascotaPresenter implements ILikeMascotaPresenter {

    private ILikeMascotaView iLikeMascotaView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public LikeMascotaPresenter(ILikeMascotaView iLikeMascotaView, Context context) {
        this.iLikeMascotaView = iLikeMascotaView;
        this.context = context;
        obtenerCincoMascotas();
    }

    @Override
    public void obtenerCincoMascotas() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerCincoDatos();
        mostarCincoMascotasRV();
    }

    @Override
    public void mostarCincoMascotasRV() {
        iLikeMascotaView.inicializarAdaptadorRV(iLikeMascotaView.crearAdaptador(mascotas));
        iLikeMascotaView.generarLinearLayouVertical();
    }
}
