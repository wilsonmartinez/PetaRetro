package com.wmartinez.devep.petaretro.presentador;

/**
 * Created by WilsonMartinez on 6/28/2016.
 */
public interface IPerfilFragmentViewPresenter {
    void cargarMediosCuenta();
    void mostrarMediosCuenta(String id);
    void obtenerMediosRecientes();
    void mostrarMediosRV();
}
