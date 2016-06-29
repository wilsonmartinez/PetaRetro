package com.wmartinez.devep.petaretro.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wmartinez.devep.petaretro.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public class BaseDatos extends SQLiteOpenHelper{

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstanteBaseDatos.DATABASE_NAME, null, ConstanteBaseDatos.DATA_BASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstanteBaseDatos.TABLE_PETS + "(" +
                ConstanteBaseDatos.TABLE_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstanteBaseDatos.TABLE_PETS_NOMBRE + " TEXT, " +
                ConstanteBaseDatos.TABLE_PETS_FOTO + " INTEGER" +
                ")";
        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstanteBaseDatos.TABLE_LIKES_PET + "(" +
                ConstanteBaseDatos.TABLE_LIKES_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstanteBaseDatos.TABLE_LIKES_PET_ID_MASCOTA + " INTEGER, " +
                ConstanteBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstanteBaseDatos.TABLE_LIKES_PET_ID_MASCOTA+ ") " +
                "REFERENCES " + ConstanteBaseDatos.TABLE_PETS  + "(" + ConstanteBaseDatos.TABLE_PETS_ID+ ")" +
                ")";
        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstanteBaseDatos.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXIST " + ConstanteBaseDatos.TABLE_LIKES_PET);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstanteBaseDatos.TABLE_PETS;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor registros = database.rawQuery(query, null);
        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getString(0));
            mascotaActual.setNombreCompleto(registros.getString(1));
            mascotaActual.setUrlFoto(registros.getString(2));
            mascotas.add(mascotaActual);

            String queryLikes = "SELECT COUNT(" + ConstanteBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES + ") as likes " +
                    " FROM " + ConstanteBaseDatos.TABLE_LIKES_PET +
                    " WHERE " + ConstanteBaseDatos.TABLE_LIKES_PET_ID_MASCOTA + "=" + mascotaActual.getId();
            Cursor registroLikes = database.rawQuery(queryLikes, null);
            if(registroLikes.moveToNext()){
                mascotaActual.setLikes(registroLikes.getInt(0));
            }
            else {
                mascotaActual.setLikes(0);
            }
        }
        database.close();
        return mascotas;
    }

    public ArrayList<Mascota> obtenerCincoMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        int i = 0;
        String query = "SELECT * FROM " + ConstanteBaseDatos.TABLE_PETS;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor registros = database.rawQuery(query, null);
        while(registros.moveToNext() && i < 5){
            i ++;
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getString(0));
            mascotaActual.setNombreCompleto(registros.getString(1));
            mascotaActual.setUrlFoto(registros.getString(2));
            String queryLikes = "SELECT COUNT(" + ConstanteBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES + ") as likes " +
                    " FROM " + ConstanteBaseDatos.TABLE_LIKES_PET +
                    " WHERE " + ConstanteBaseDatos.TABLE_LIKES_PET_ID_MASCOTA + "=" + mascotaActual.getId();
            Cursor registroLikes = database.rawQuery(queryLikes, null);
            if(registroLikes.moveToNext()){
                mascotaActual.setLikes(registroLikes.getInt(0));
            }
            else {
                mascotaActual.setLikes(0);
            }
            mascotas.add(mascotaActual);
        }
        database.close();
        return mascotas;
    }

    public void insertarMacota(ContentValues contentValues){
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(ConstanteBaseDatos.TABLE_PETS, null, contentValues);
        database.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(ConstanteBaseDatos.TABLE_LIKES_PET, null, contentValues);
        database.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;
        String query ="SELECT COUNT(" + ConstanteBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES + ") as likes " +
                " FROM " + ConstanteBaseDatos.TABLE_LIKES_PET +
                " WHERE " + ConstanteBaseDatos.TABLE_LIKES_PET_ID_MASCOTA + "=" + mascota.getId();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor registros = database.rawQuery(query, null);
        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        database.close();
        return likes;
    }
}
