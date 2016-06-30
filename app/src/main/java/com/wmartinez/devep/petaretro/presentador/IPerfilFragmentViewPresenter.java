package com.wmartinez.devep.petaretro.presentador;

/**
 * Created by WilsonMartinez on 6/28/2016.
 */
public interface IPerfilFragmentViewPresenter {

    void buscarUsuario(String userQ);

    void mostrarMediosCuenta(String userId);
    void mostrarMediosRV();

    void getSharedPreferences();
}
