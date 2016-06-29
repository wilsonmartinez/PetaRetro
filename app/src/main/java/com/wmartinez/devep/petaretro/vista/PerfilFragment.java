package com.wmartinez.devep.petaretro.vista;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.wmartinez.devep.petaretro.R;
import com.wmartinez.devep.petaretro.adapter.PerfilMascotaAdaptador;
import com.wmartinez.devep.petaretro.pojo.Mascota;
import com.wmartinez.devep.petaretro.presentador.IPerfilFragmentViewPresenter;
import com.wmartinez.devep.petaretro.presentador.PerfilFragmentViewPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements IPerfilFragmentView {

    private RecyclerView rvPerfilUsuario;
    private IPerfilFragmentViewPresenter presenter;
    private CircularImageView circularImageView;
    private static String account;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) {
            account = "petamaster";
        } else {
            account = bundle.getString(getArguments() != null ? getArguments().getString("account") : "petamaster");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Circular Image Configuration
        circularImageView = (CircularImageView) view.findViewById(R.id.civUsuario);
        circularImageView.setBorderWidth(getResources().getDimension(R.dimen.perfilCircleImageBorderSize) * (int) getResources().getDisplayMetrics().density);
        circularImageView.setShadowRadius(getResources().getDimension(R.dimen.perfilCircleImageShadowSize));
        circularImageView.setBorderColor(getResources().getColor(R.color.colorAccent));
        circularImageView.setShadowColor(getResources().getColor(R.color.colorPrimary));

        rvPerfilUsuario = (RecyclerView) view.findViewById(R.id.rvPerfilUsuario);
        presenter = new PerfilFragmentViewPresenter(this, getContext(), account);

        return view;
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rvPerfilUsuario.setLayoutManager(gridLayoutManager);
    }

    @Override
    public PerfilMascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        PerfilMascotaAdaptador adaptador = new PerfilMascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(PerfilMascotaAdaptador adaptador) {
        rvPerfilUsuario.setAdapter(adaptador);
    }
}
