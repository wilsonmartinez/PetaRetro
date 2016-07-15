package com.wmartinez.devep.petaretro.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wmartinez.devep.petaretro.R;
import com.wmartinez.devep.petaretro.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public class PerfilMascotaAdaptador extends RecyclerView.Adapter<PerfilMascotaAdaptador.PerfilMascotaViewHolder>{

    public int likes;
    ArrayList<Mascota> mascotas;
    Activity activity;

    public PerfilMascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public PerfilMascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil_mascota, parent, false);
        return new PerfilMascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PerfilMascotaViewHolder perfilMascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.pets)
                .into(perfilMascotaViewHolder.imgFoto);
        perfilMascotaViewHolder.tvLikes.setText(String.valueOf(mascota.getLikes()));
    }


    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public class PerfilMascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvLikes;
        public PerfilMascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFotoCVPerfil);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikesCVPerfil);
        }
    }
}
