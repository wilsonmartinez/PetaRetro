package com.wmartinez.devep.petaretro;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wmartinez.devep.petaretro.adapter.PageAdapter;
import com.wmartinez.devep.petaretro.vista.PerfilActivityFragment;

import java.util.ArrayList;

public class PerfilActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        toolbar = (Toolbar) findViewById(R.id.perfilToolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        tabLayout = (TabLayout) findViewById(R.id.perfilTabLayout);
        viewPager = (ViewPager) findViewById(R.id.perfilViewPager);

        setUpViewPager();
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
    }

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new PerfilActivityFragment());
        return fragments;
    }
}
