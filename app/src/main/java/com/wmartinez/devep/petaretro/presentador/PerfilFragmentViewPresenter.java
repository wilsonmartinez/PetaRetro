package com.wmartinez.devep.petaretro.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wmartinez.devep.petaretro.pojo.Mascota;
import com.wmartinez.devep.petaretro.restApi.EndpointsApi;
import com.wmartinez.devep.petaretro.restApi.adapter.RestApiAdapter;
import com.wmartinez.devep.petaretro.restApi.model.FollowerMediaResponse;
import com.wmartinez.devep.petaretro.restApi.model.MascotaResponse;
import com.wmartinez.devep.petaretro.vista.IPerfilFragmentView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WilsonMartinez on 6/28/2016.
 */
public class PerfilFragmentViewPresenter implements IPerfilFragmentViewPresenter{

    private IPerfilFragmentView iPerfilFragmentView;
    private Context context;
    private static ArrayList<Mascota> mascotas, followers, mediosCuenta;
    public static String account, id;

    public PerfilFragmentViewPresenter (IPerfilFragmentView iPerfilFragmentView, Context context, String account){
        this.iPerfilFragmentView = iPerfilFragmentView;
        this.context = context;
        this.account = account;
        /*Bundle bundle = new Bundle();

        account = bundle.getString("account");*/
        //getArguments() != null ? getArguments().getString("account"):"petamaster";
        mediosCuenta = new ArrayList<>();
        cargarMediosCuenta();
    }

    @Override
    public void cargarMediosCuenta() {
        switch (account){
            case "pato_856":
                id = "3436368772";
                break;
            case "micrcele":
                id = "2163701855";
                break;
            case "appsrsanchezcobian":
                id = "3437117661";
                break;
            case "devaldomora":
                id = "3439180779";
                break;
            default:
                id = "3435346040";
                break;
        }
        if (id == "3435346040"){
            obtenerMediosRecientes();
        }else {
            mostrarMediosCuenta(id);
        }
    }

    @Override
    public void mostrarMediosCuenta(String id) {
        RestApiAdapter restApiAdapterMediaFollowers = new RestApiAdapter();
        Gson gsonMediaRecentFollowers = restApiAdapterMediaFollowers.construyeGsonDeserializadorFollowersMedia();
        EndpointsApi endpointsApi = restApiAdapterMediaFollowers.establecerConexionRestApiInstagram(gsonMediaRecentFollowers);
        Call<FollowerMediaResponse> followersResponseCallMediaFollowers = endpointsApi.getFollowerMediaBy(id);
        followersResponseCallMediaFollowers.enqueue(new Callback<FollowerMediaResponse>() {
            @Override
            public void onResponse(Call<FollowerMediaResponse> call, Response<FollowerMediaResponse> response) {
                FollowerMediaResponse followerMediaResponse = response.body();
                followers = followerMediaResponse.getFollowersMedia();
                //Toast.makeText(context, "Carga de datos", Toast.LENGTH_LONG).show();
                mediosCuenta.addAll(followers);
                mostrarMediosRV();
            }

            @Override
            public void onFailure(Call<FollowerMediaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso en la conexion! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mediosCuenta.addAll(mascotas);
                mostrarMediosRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                //Toast.makeText(context, "Algo paso en la conexion! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void mostrarMediosRV() {
        iPerfilFragmentView.inicializarAdaptadorRV(iPerfilFragmentView.crearAdaptador(mediosCuenta));
        iPerfilFragmentView.generarGridLayout();
    }
}
