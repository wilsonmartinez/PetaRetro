package com.wmartinez.devep.petaretro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.wmartinez.devep.petaretro.adapter.MascotaAdaptador;
import com.wmartinez.devep.petaretro.pojo.Mascota;
import com.wmartinez.devep.petaretro.presentador.ILikeMascotaPresenter;
import com.wmartinez.devep.petaretro.presentador.LikeMascotaPresenter;
import com.wmartinez.devep.petaretro.vista.ILikeMascotaView;

import java.util.ArrayList;

public class LikeMascotaActivity extends AppCompatActivity implements ILikeMascotaView {

    private RecyclerView rvMascotas1;
    public MascotaAdaptador adaptador;
    private ILikeMascotaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_mascota);

        Toolbar miActionBar = (Toolbar)findViewById(R.id.likeToolbar) ;
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvMascotas1      = (RecyclerView)findViewById(R.id.rvLikeMascotas);
        presenter = new LikeMascotaPresenter(this, getApplicationContext());
    }

    @Override
    public void generarLinearLayouVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas1.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvMascotas1.setAdapter(adaptador);
    }
}
