package com.wmartinez.devep.petaretro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.wmartinez.devep.petaretro.adapter.PageAdapter;
import com.wmartinez.devep.petaretro.vista.PerfilFragment;
import com.wmartinez.devep.petaretro.vista.RecyclerViewFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.mainToolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        tabLayout = (TabLayout) findViewById(R.id.mainTabLayout);
        viewPager = (ViewPager) findViewById(R.id.mainViewPager);
        Log.e("MainActivity", "onCreate");

        if (validateAccount()) {
            setUpViewPager();
        }

    }

    private boolean validateAccount() {
        SharedPreferences userAccountPreferences = getSharedPreferences("account", Context.MODE_PRIVATE);
        String userAccount = userAccountPreferences.getString("EditAccount", null);
        if (userAccount == null) {
            Intent intentConfigAccount = new Intent(MainActivity.this, ConfigurarCuentaActivity.class);
            startActivity(intentConfigAccount);
            Toast.makeText(MainActivity.this, "Por favor configure una cuanta", Toast.LENGTH_LONG).show();
            finish();
            return false;
        } else
            return true;
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_contacts);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
    }

    private ArrayList<Fragment> agregarFragments(){
        Log.e("MainActivity", "agregarFragments");
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionLike:
                Toast.makeText(this, "Los cinco preferidos", Toast.LENGTH_SHORT).show();
                Intent iLike = new Intent(this, LikeMascotaActivity.class);
                startActivity(iLike);
                return true;

            case R.id.action_contacto:
                Toast.makeText(this, "Informacion de contacto", Toast.LENGTH_SHORT).show();
                Intent iContacto = new Intent(this, FormularioActivity.class);
                startActivity(iContacto);
                return true;

            case R.id.action_acerca_de:
                Toast.makeText(this, "Acerca del desarrollador.", Toast.LENGTH_SHORT).show();
                Intent iAcercaDe = new Intent(this, AcercaDeActivity.class);
                startActivity(iAcercaDe);
                return true;
            case R.id.action_configurar_cuenta:
                Toast.makeText(this, "Cofigurar Cuenta.", Toast.LENGTH_SHORT).show();
                Intent iCfgCuenta = new Intent(this, ConfigurarCuentaActivity.class);
                startActivity(iCfgCuenta);
                return true;
            default:
                return true;
        }
    }
}
