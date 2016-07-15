package com.wmartinez.devep.petaretro.restApi.model;

/**
 * Created by WilsonMartinez on 7/14/2016.
 */
public class LikeNotificationResponse {
    private String id_foto_instagram;
    private String id_usuario_instagram;
    private String id_dispositivo;
    private int likes;

    public LikeNotificationResponse() {
    }

    public LikeNotificationResponse(String id_foto_instagram, String id_usuario_instagram, String id_dispositivo) {
        this.id_foto_instagram = id_foto_instagram;
        this.id_usuario_instagram = id_usuario_instagram;
        this.id_dispositivo = id_dispositivo;
    }

    public LikeNotificationResponse(String id_foto_instagram, String id_usuario_instagram, String id_dispositivo, int likes) {
        this.id_foto_instagram = id_foto_instagram;
        this.id_usuario_instagram = id_usuario_instagram;
        this.id_dispositivo = id_dispositivo;
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getId_foto_instagram() {
        return id_foto_instagram;
    }

    public void setId_foto_instagram(String id_foto_instagram) {
        this.id_foto_instagram = id_foto_instagram;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }
}
