package com.wmartinez.devep.petaretro.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.wmartinez.devep.petaretro.pojo.Mascota;
import com.wmartinez.devep.petaretro.restApi.EndpointsApi;
import com.wmartinez.devep.petaretro.restApi.adapter.RestApiAdapter;
import com.wmartinez.devep.petaretro.restApi.model.FollowerMediaResponse;
import com.wmartinez.devep.petaretro.restApi.model.FollowersResponse;
import com.wmartinez.devep.petaretro.vista.IPerfilActivityFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WilsonMartinez on 8/12/2016.
 */
public class PerfilActivityFragmentPresenter implements IPerfilActivityFragmentPresenter {

    private static ArrayList<Mascota> followersMediaVista;
    private IPerfilActivityFragment iPerfilActivityFragment;
    private Context context;
    private String account = "";
    private Mascota userData;

    public PerfilActivityFragmentPresenter(IPerfilActivityFragment iPerfilActivityFragment, Context context/*, String account*/) {
        this.iPerfilActivityFragment = iPerfilActivityFragment;
        this.context = context;

        followersMediaVista = new ArrayList<>();

        getSharedPreferences();
        buscarUsuario(account);
    }

    @Override
    public void buscarUsuario(String userQ) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonSearchUsers = restApiAdapter.construyeGsonDeserializadorFollowers();
        EndpointsApi endpointsApiSearchUsers = restApiAdapter.establecerConexionRestApiInstagram(gsonSearchUsers);
        Call<FollowersResponse> responseCallSearchUsers = endpointsApiSearchUsers.getUsersSearch(userQ);
        responseCallSearchUsers.enqueue(new Callback<FollowersResponse>() {
            @Override
            public void onResponse(Call<FollowersResponse> call, Response<FollowersResponse> response) {
                FollowersResponse followersResponse = response.body();
                if (followersResponse.getFollowers().size() > 0) {
                    String id = followersResponse.getFollowers().get(0).getId();
                    String nombre = followersResponse.getFollowers().get(0).getUserName();
                    String profilePic = followersResponse.getFollowers().get(0).getUrlFoto();
                    userData = new Mascota(id, nombre, profilePic, 0);
                    iPerfilActivityFragment.userPerfilData(userData);
                    mostrarMediosCuenta(id);
                }
            }

            @Override
            public void onFailure(Call<FollowersResponse> call, Throwable t) {
                //Toast.makeText(this , "Algo paso en la conexion! Intenta de nuevo", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void mostrarMediosCuenta(String userId) {
        RestApiAdapter restApiAdapterMediaFollowers = new RestApiAdapter();
        Gson gsonMediaRecentFollowers = restApiAdapterMediaFollowers.construyeGsonDeserializadorFollowersMedia();
        EndpointsApi endpointsApi = restApiAdapterMediaFollowers.establecerConexionRestApiInstagram(gsonMediaRecentFollowers);
        Call<FollowerMediaResponse> followersResponseCallMediaFollowers = endpointsApi.getFollowerMediaBy(userId);
        followersResponseCallMediaFollowers.enqueue(new Callback<FollowerMediaResponse>() {
            @Override
            public void onResponse(Call<FollowerMediaResponse> call, Response<FollowerMediaResponse> response) {
                FollowerMediaResponse followerMediaResponse = response.body();
                followersMediaVista = followerMediaResponse.getFollowersMedia();
                mostrarMediosRV();
            }

            @Override
            public void onFailure(Call<FollowerMediaResponse> call, Throwable t) {
                //Toast.makeText(context, "Usuario sin permiso SandBox", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void mostrarMediosRV() {
        iPerfilActivityFragment.inicializarAdaptadorRV(iPerfilActivityFragment.crearAdaptador(followersMediaVista));
        //iRecyclerViewFragmentView.generarGridLayout();
        iPerfilActivityFragment.generarLinearLayout();
    }

    @Override
    public void getSharedPreferences() {
        SharedPreferences accountPreferences = context.getSharedPreferences("account", Context.MODE_PRIVATE);
        account = accountPreferences.getString("EditAccount", null);
    }
}
