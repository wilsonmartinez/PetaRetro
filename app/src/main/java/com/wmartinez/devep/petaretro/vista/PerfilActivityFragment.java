package com.wmartinez.devep.petaretro.vista;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.wmartinez.devep.petaretro.R;
import com.wmartinez.devep.petaretro.adapter.MascotaAdaptador;
import com.wmartinez.devep.petaretro.pojo.Mascota;
import com.wmartinez.devep.petaretro.presentador.IPerfilActivityFragmentPresenter;
import com.wmartinez.devep.petaretro.presentador.PerfilActivityFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 8/12/2016.
 */
public class PerfilActivityFragment extends Fragment implements IPerfilActivityFragment {

    private RecyclerView rvPerfilUsuario;
    private IPerfilActivityFragmentPresenter presenter;
    private CircularImageView civUserPhoto;
    private TextView tvUserName;

    public PerfilActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_perfil_activity, container, false);

        civUserPhoto = (CircularImageView) view.findViewById(R.id.civUsuarioPerfil);
        tvUserName = (TextView) view.findViewById(R.id.tvNombreUsuarioPerfil);

        rvPerfilUsuario = (RecyclerView) view.findViewById(R.id.rvPerfilUsuarioPerfil);
        presenter = new PerfilActivityFragmentPresenter(this, getContext()/*, account*/);

        return view;
    }

    @Override
    public void generarLinearLayout() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPerfilUsuario.setLayoutManager(manager);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvPerfilUsuario.setAdapter(adaptador);
    }

    @Override
    public void userPerfilData(Mascota userPerfil) {
        Picasso.with(getContext())
                .load(userPerfil.getUrlFoto())
                .placeholder(R.drawable.pets)
                .into(civUserPhoto);
        tvUserName.setText(userPerfil.getUserName());
    }
}
