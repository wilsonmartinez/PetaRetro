package com.wmartinez.devep.petaretro.restApi.model;

import com.wmartinez.devep.petaretro.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 6/24/2016.
 */
public class FollowersResponse {

    ArrayList<Mascota>  followers;
    public ArrayList<Mascota> getFollowers(){
        return followers;
    }

    public void setFollowers(ArrayList<Mascota> followers){
        this.followers = followers;
    }
}
