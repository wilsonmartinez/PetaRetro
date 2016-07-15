package com.wmartinez.devep.petaretro.restApi.model;

/**
 * Created by WilsonMartinez on 7/14/2016.
 */
public class LikesResponse {
    private String id_foto_instagram;
    private int likes;

    public LikesResponse() {
    }

    public LikesResponse(String id_foto_instagram, int likes) {
        this.id_foto_instagram = id_foto_instagram;
        this.likes = likes;
    }

    public String getId_foto_instagram() {
        return id_foto_instagram;
    }

    public void setId_foto_instagram(String id_foto_instagram) {
        this.id_foto_instagram = id_foto_instagram;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
