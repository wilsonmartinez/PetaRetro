package com.wmartinez.devep.petaretro.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wmartinez.devep.petaretro.db.ConstructorMascotas;
import com.wmartinez.devep.petaretro.pojo.Mascota;
import com.wmartinez.devep.petaretro.restApi.EndpointsApi;
import com.wmartinez.devep.petaretro.restApi.adapter.RestApiAdapter;
import com.wmartinez.devep.petaretro.restApi.model.FollowerMediaResponse;
import com.wmartinez.devep.petaretro.restApi.model.FollowersResponse;
import com.wmartinez.devep.petaretro.restApi.model.MascotaResponse;
import com.wmartinez.devep.petaretro.vista.IRecyclerViewFragmentView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private static ArrayList<Mascota> mascotas, followers, followersMedia, followersMediaVista;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;

        followersMediaVista = new ArrayList<>();
        //obtenerMascotasBaseDatos();
        //obtenerMediosRecientes();
        obtenerFollowers();

    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        //mostrarMascotasRV();
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
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso en la conexion! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void obtenerFollowers() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonFollowers = restApiAdapter.construyeGsonDeserializadorFollowers();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFollowers);
        Call<FollowersResponse> followersResponseCall = endpointsApi.getFollowedBy();
        followersResponseCall.enqueue(new Callback<FollowersResponse>() {
            @Override
            public void onResponse(Call<FollowersResponse> call, Response<FollowersResponse> response) {
                FollowersResponse followersResponse = response.body();
                followers = followersResponse.getFollowers();
                for (int i = 0; i < followers.size(); i++) {
                    RestApiAdapter restApiAdapterMediaFollowers = new RestApiAdapter();
                    Gson gsonMediaRecentFollowers = restApiAdapterMediaFollowers.construyeGsonDeserializadorFollowersMedia();
                    EndpointsApi endpointsApi = restApiAdapterMediaFollowers.establecerConexionRestApiInstagram(gsonMediaRecentFollowers);
                    Call<FollowerMediaResponse> followersResponseCallMediaFollowers = endpointsApi.getFollowerMediaBy(followers.get(i).getId());
                    followersResponseCallMediaFollowers.enqueue(new Callback<FollowerMediaResponse>() {
                        @Override
                        public void onResponse(Call<FollowerMediaResponse> call, Response<FollowerMediaResponse> response) {
                            FollowerMediaResponse followerMediaResponse = response.body();
                            followersMedia = followerMediaResponse.getFollowersMedia();
                            //Toast.makeText(context, "Carga de datos", Toast.LENGTH_LONG).show();
                            followersMediaVista.addAll(followersMedia);
                            mostrarFollowerMediaRV();
                        }

                        @Override
                        public void onFailure(Call<FollowerMediaResponse> call, Throwable t) {
                            Toast.makeText(context, "Algo paso en la conexion! Intenta de nuevo", Toast.LENGTH_LONG).show();
                            Log.e("FALLO LA CONEXION", t.toString());
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<FollowersResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso en la conexion! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarGridLayout();
    }

    @Override
    public void mostrarFollowersRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(followers));
        iRecyclerViewFragmentView.generarGridLayout();
    }

    @Override
    public void mostrarFollowerMediaRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(followersMediaVista));
        iRecyclerViewFragmentView.generarGridLayout();
    }
}
