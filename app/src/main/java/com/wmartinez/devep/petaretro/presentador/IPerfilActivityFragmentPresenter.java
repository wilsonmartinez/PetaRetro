package com.wmartinez.devep.petaretro.presentador;

/**
 * Created by WilsonMartinez on 8/12/2016.
 */
public interface IPerfilActivityFragmentPresenter {
    void buscarUsuario(String userQ);

    void mostrarMediosCuenta(final String userId);

    void mostrarMediosRV();

    void getSharedPreferences();
}
