package com.wmartinez.devep.petaretro.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wmartinez.devep.petaretro.R;
import com.wmartinez.devep.petaretro.pojo.Mascota;
import com.wmartinez.devep.petaretro.restApi.ConstantesRestApi;
import com.wmartinez.devep.petaretro.restApi.EndpointsApi;
import com.wmartinez.devep.petaretro.restApi.adapter.RestApiAdapter;
import com.wmartinez.devep.petaretro.restApi.model.LikeNotificationResponse;
import com.wmartinez.devep.petaretro.restApi.model.LikesResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    //private static final String USER_RECEPTOR = "petamaster";
    private static final String USER_RECEPTOR = "petmaster2016";
    private static final String ID_PETAMASTER = "-KOzTmeHHX0JYlO4vK4q";
    private static final String ID_PETMASTER2016 = "-KOzTodh25zTDJcV5_n_";
    private static final String ID_RECEPTOR = ID_PETAMASTER;
    ArrayList<Mascota> mascotas;
    Activity activity;
    private String id_usuario_instagram;
    private String id_dispositivo;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_mascota, parent, false);
        obtenerSharedPreferences();
        return new MascotaViewHolder(view);
    }

    private void obtenerSharedPreferences() {
        SharedPreferences userAccountPreferences = activity.getSharedPreferences("account", Context.MODE_PRIVATE);
        id_dispositivo = userAccountPreferences.getString("idDevice", null);
        id_usuario_instagram = userAccountPreferences.getString("EditAccount", null);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.pets)
                .into(mascotaViewHolder.imgFoto);
        mascotaViewHolder.tvUser.setText(String.valueOf(mascota.getUserName()));
        mascotaViewHolder.tvLikes.setText(String.valueOf(mascota.getLikes()));
        mascotaViewHolder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(activity, DetalleMascotaActivity.class);
                intent.putExtra("url", mascota.getUrlFoto());
                intent.putExtra("like", mascota.getLikes());
                activity.startActivity(intent);*/
                RestApiAdapter restApiAdapter = new RestApiAdapter();
                Gson gsonLikes = restApiAdapter.construyeGsonDeserializadorFollowersMedia();
                EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonLikes);
                Call<LikesResponse> likesResponseCall = endpointsApi.setLikes(mascota.getIdImagen(), ConstantesRestApi.ACCESS_TOKEN);
                likesResponseCall.enqueue(new Callback<LikesResponse>() {
                    @Override
                    public void onResponse(Call<LikesResponse> call, Response<LikesResponse> response) {
                        LikesResponse likesResponse = response.body();
                        if (likesResponse.getLikes() >= 0) {
                            mascota.addLikes();
                            if (id_dispositivo == null) {
                                Toast.makeText(activity, "Por favor activa la recepcion de notificaciones", Toast.LENGTH_LONG).show();
                            } else {
                                loadlikesDB(mascota.getIdImagen(), mascota.getUserName(), mascota.getLikes());
                                //sendNotificationLike(mascota.getIdImagen(), mascota.getUserName());
                                sendNotificationUser();
                            }
                            mascotaViewHolder.tvLikes.setText(String.valueOf(mascota.getLikes()));
                            Toast.makeText(activity, "Diste Like a " + String.valueOf(mascota.getUserName()), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity, "Error, intentalo mas tarde", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LikesResponse> call, Throwable t) {
                        Toast.makeText(activity, "Error, intentalo mas tarde por favor", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void loadlikesDB(String idImagen, String userName, int likes) {
        Log.d("Mascota Adapter", "loadlikesDB");
        LikeNotificationResponse response = new LikeNotificationResponse(idImagen, userName, id_dispositivo, likes);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiHeroku();
        Call<LikeNotificationResponse> responseCall = endpointsApi.regsitrarLikes(response.getId_foto_instagram(),
                response.getId_usuario_instagram(), response.getId_dispositivo(), String.valueOf(response.getLikes()));
        responseCall.enqueue(new Callback<LikeNotificationResponse>() {
            @Override
            public void onResponse(Call<LikeNotificationResponse> call, Response<LikeNotificationResponse> response) {
                Toast.makeText(activity, "Like guardado en FIREBASE", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LikeNotificationResponse> call, Throwable t) {
                Toast.makeText(activity, "No se pudo guarfar en FIREBASE", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendNotificationUser() {
        //LikeNotificationResponse likeNotificationResponse = new LikeNotificationResponse()
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiHeroku();
        Call<LikeNotificationResponse> likeNotificationResponseCall = endpointsApi.sendNotificationUser(ID_RECEPTOR, USER_RECEPTOR);
        likeNotificationResponseCall.enqueue(new Callback<LikeNotificationResponse>() {
            @Override
            public void onResponse(Call<LikeNotificationResponse> call, Response<LikeNotificationResponse> response) {
                Toast.makeText(activity, "Notifiacacion Enviada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LikeNotificationResponse> call, Throwable t) {
                Toast.makeText(activity, "Fallo en envio de notifiacacion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    private void sendNotificationLike(String id_foto_instagram, String id_usuario_instagram) {
        Log.d("Mascota Adapter", "sendNotificationLike...");
        LikeNotificationResponse likesResponse = new LikeNotificationResponse(id_foto_instagram, id_usuario_instagram, id_dispositivo);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiHeroku();
        Call<LikeNotificationResponse> likeNotificationResponseCall = endpointsApi.sendLikeNotification(likesResponse.getId_foto_instagram(),
                likesResponse.getId_usuario_instagram(), likesResponse.getId_dispositivo());
        likeNotificationResponseCall.enqueue(new Callback<LikeNotificationResponse>() {
            @Override
            public void onResponse(Call<LikeNotificationResponse> call, Response<LikeNotificationResponse> response) {
                LikeNotificationResponse likeNotificationResponse = response.body();
                Toast.makeText(activity, "Notifiacacion Enviada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LikeNotificationResponse> call, Throwable t) {
                Toast.makeText(activity, "Fallo en envio de notifiacacion", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvUser;
        private TextView tvLikes;
        private ImageButton imgLike;
        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView)itemView.findViewById(R.id.imgFoto);
            tvUser = (TextView) itemView.findViewById(R.id.tvUser);
            tvLikes = (TextView)itemView.findViewById(R.id.tvLikes);
            imgLike = (ImageButton) itemView.findViewById(R.id.imgLike);
        }
    }
}
