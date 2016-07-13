package com.wmartinez.devep.petaretro.restApi.model;

/**
 * Created by WilsonMartinez on 7/8/2016.
 */
public class UserResponse {
    private String id;
    private String token;

    public UserResponse(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public UserResponse() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
