package com.wmartinez.devep.petaretro.presentador;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public interface IRecyclerViewFragmentPresenter {

    void obtenerMascotasBaseDatos();
    void obtenerMediosRecientes();
    void obtenerFollowers();
    void mostrarMascotasRV();
    void mostrarFollowersRV();
    void mostrarFollowerMediaRV();
}
