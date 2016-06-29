package com.wmartinez.devep.petaretro.db;

import android.content.ContentValues;
import android.content.Context;

import com.wmartinez.devep.petaretro.R;
import com.wmartinez.devep.petaretro.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public class ConstructorMascotas {
    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context){this.context = context;}

    public ArrayList<Mascota> obtenerDatos(){
        BaseDatos baseDatos = new BaseDatos(context);
        insertarListaMascotas(baseDatos);
        return  baseDatos.obtenerTodasLasMascotas();
    }

    public ArrayList<Mascota> obtenerCincoDatos(){
        BaseDatos baseDatos = new BaseDatos(context);
        return baseDatos.obtenerCincoMascotas();
    }

    public void insertarListaMascotas(BaseDatos baseDatos){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_NOMBRE, "Sack");
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_FOTO, R.drawable.perro);
        baseDatos.insertarMacota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_NOMBRE, "Ander");
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_FOTO, R.drawable.perro2);
        baseDatos.insertarMacota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_NOMBRE, "Aro");
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_FOTO, R.drawable.perro3);
        baseDatos.insertarMacota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_NOMBRE, "Bego");
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_FOTO, R.drawable.perro4);
        baseDatos.insertarMacota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_NOMBRE, "Barbie");
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_FOTO, R.drawable.gata);
        baseDatos.insertarMacota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_NOMBRE, "Ergo");
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_FOTO, R.drawable.gato);
        baseDatos.insertarMacota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_NOMBRE, "Bob");
        contentValues.put(ConstanteBaseDatos.TABLE_PETS_FOTO, R.drawable.pajaro);
        baseDatos.insertarMacota(contentValues);

        baseDatos.close();
    }

    public void darLikeMascota(Mascota mascota){
        BaseDatos baseDatos = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_LIKES_PET_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstanteBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES, LIKE);
        baseDatos.insertarLikeMascota(contentValues);
        baseDatos.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos baseDatos = new BaseDatos(context);
        return baseDatos.obtenerLikesMascota(mascota);
    }
}
