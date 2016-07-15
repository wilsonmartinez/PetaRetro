package com.wmartinez.devep.petaretro.pojo;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public class Mascota {
    private String id;
    private String nombreCompleto;
    private String urlFoto;
    private String userName;
    private String idImagen;
    private int likes = 0;

    public Mascota(String urlFoto, String nombreCompleto, int likes){
        this.urlFoto = urlFoto;
        this.nombreCompleto = nombreCompleto;
        this.likes = likes;
    }

    public Mascota(String id, String userName, String urlFoto, int likes) {
        this.id = id;
        this.userName = userName;
        this.urlFoto = urlFoto;
        this.likes = likes;
    }

    public Mascota(String id, String userName, String urlFoto, int likes, String idImagen) {
        this.id = id;
        this.userName = userName;
        this.urlFoto = urlFoto;
        this.likes = likes;
        this.idImagen = idImagen;
    }

    public Mascota() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void addLikes() {
        this.likes = this.likes + 1;
    }

    public String getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(String idImagen) {
        this.idImagen = idImagen;
    }
}
