package com.wmartinez.devep.petaretro.restApi.model;

import com.wmartinez.devep.petaretro.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 6/27/2016.
 */
public class FollowerMediaResponse {
    ArrayList<Mascota> followersMedia;
    public ArrayList<Mascota> getFollowersMedia(){
        return followersMedia;
    }

    public void setFollowersMedia(ArrayList<Mascota> followersMedia) {
        this.followersMedia = followersMedia;
    }
}
