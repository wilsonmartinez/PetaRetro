package com.wmartinez.devep.petaretro.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wmartinez.devep.petaretro.restApi.ConstantesRestApi;
import com.wmartinez.devep.petaretro.restApi.EndpointsApi;
import com.wmartinez.devep.petaretro.restApi.deserializador.FollowersDeserializador;
import com.wmartinez.devep.petaretro.restApi.deserializador.FollowersMediaDeserializador;
import com.wmartinez.devep.petaretro.restApi.deserializador.MascotaDeserializador;
import com.wmartinez.devep.petaretro.restApi.model.FollowerMediaResponse;
import com.wmartinez.devep.petaretro.restApi.model.FollowersResponse;
import com.wmartinez.devep.petaretro.restApi.model.MascotaResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorFollowers(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FollowersResponse.class, new FollowersDeserializador());
        return  gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorFollowersMedia(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FollowerMediaResponse.class, new FollowersMediaDeserializador());
        return  gsonBuilder.create();
    }

    public EndpointsApi establecerConexionRestApiHeroku() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_HEROKU)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);
    }
}
