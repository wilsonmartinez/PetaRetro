package com.wmartinez.devep.petaretro.db;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public class ConstanteBaseDatos {
    public static final String DATABASE_NAME = "mascotas";
    public static final int DATA_BASE_VERSION = 1;

    public static final String TABLE_PETS           = "mascota";
    public static final String TABLE_PETS_ID        = "id";
    public static final String TABLE_PETS_NOMBRE    = "nombre";
    public static final String TABLE_PETS_FOTO      = "foto";


    public static final String TABLE_LIKES_PET = "mascota_likes";
    public static final String TABLE_LIKES_PET_ID = "id";
    public static final String TABLE_LIKES_PET_ID_MASCOTA = "id_mascota";
    public static final String TABLE_LIKES_PET_NUMERO_LIKES = "numero_likes";
}
