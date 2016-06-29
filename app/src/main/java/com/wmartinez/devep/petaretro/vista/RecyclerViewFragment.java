package com.wmartinez.devep.petaretro.vista;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wmartinez.devep.petaretro.R;
import com.wmartinez.devep.petaretro.adapter.MascotaAdaptador;
import com.wmartinez.devep.petaretro.pojo.Mascota;
import com.wmartinez.devep.petaretro.presentador.IRecyclerViewFragmentPresenter;
import com.wmartinez.devep.petaretro.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{

    private RecyclerView rvMascotasFragment;
    private IRecyclerViewFragmentPresenter presenter;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view   = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        rvMascotasFragment  = (RecyclerView) view.findViewById(R.id.rvMascotasFragment);
        presenter           = new RecyclerViewFragmentPresenter(this, getContext());
        return view;
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rvMascotasFragment.setLayoutManager(gridLayoutManager);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador  = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvMascotasFragment.setAdapter(adaptador);
    }
}
