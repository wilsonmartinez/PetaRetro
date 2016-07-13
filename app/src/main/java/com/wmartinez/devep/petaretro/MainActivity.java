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

import com.google.firebase.iid.FirebaseInstanceId;
import com.wmartinez.devep.petaretro.adapter.PageAdapter;
import com.wmartinez.devep.petaretro.restApi.EndpointsApi;
import com.wmartinez.devep.petaretro.restApi.adapter.RestApiAdapter;
import com.wmartinez.devep.petaretro.restApi.model.RegistroResponse;
import com.wmartinez.devep.petaretro.vista.PerfilFragment;
import com.wmartinez.devep.petaretro.vista.RecyclerViewFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String USUARIO_INSTAGRAM = "petamaster";
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
        //String idDevice = userAccountPreferences.getString("idDevice", null);
        String userAccount = userAccountPreferences.getString("EditAccount", null);
        if (userAccount == null) {
            Intent intentConfigAccount = new Intent(MainActivity.this, ConfigurarCuentaActivity.class);
            startActivity(intentConfigAccount);
            Toast.makeText(MainActivity.this, "Por favor configure una cuenta", Toast.LENGTH_LONG).show();
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
            case R.id.action_recibir_notificacion:
                registroUsuario();
                //likeUser();
                return true;
            default:
                return true;
        }
    }

    private void registroUsuario() {
        SharedPreferences userAccountPreferences = getSharedPreferences("account", Context.MODE_PRIVATE);
        String idDevice = userAccountPreferences.getString("idDevice", null);
        String idUserInstagram = userAccountPreferences.getString("EditAccount", null);
        if (idDevice == null) {
            idDevice = FirebaseInstanceId.getInstance().getToken();
            enviarRegistro(idDevice, idUserInstagram);
        } else {
            Toast.makeText(MainActivity.this, "Dispositivo registrado", Toast.LENGTH_LONG).show();
        }
    }

    private void enviarRegistro(String idDevice, String idUserInstagram) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiHeroku();
        Call<RegistroResponse> registroResponseCall = endpointsApi.registrarUsuario(idDevice, idUserInstagram);
        registroResponseCall.enqueue(new Callback<RegistroResponse>() {
            @Override
            public void onResponse(Call<RegistroResponse> call, Response<RegistroResponse> response) {
                RegistroResponse registroResponse = response.body();
                SharedPreferences account = getSharedPreferences("account", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = account.edit();
                editor.putString("idDevice", registroResponse.getId_dispositivo());
                editor.commit();
                Toast.makeText(MainActivity.this, "Registro dispositivo exitoso", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<RegistroResponse> call, Throwable t) {
                SharedPreferences account = getSharedPreferences("MiCuenta", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = account.edit();
                editor.putString("idDevice", null);
                editor.commit();
                Toast.makeText(MainActivity.this, "Error en registro dispositivo", Toast.LENGTH_LONG).show();
            }
        });
    }
/*
    public void likeUser(){
        RegistroResponse registroResponse = new RegistroResponse("-KMQZft6xupgzp3lTQmp", "12345", USUARIO_INSTAGRAM);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiHeroku();
        Call<RegistroResponse> registroResponseCall = endpointsApi.likeUser(registroResponse.getId_dispositivo(), USUARIO_INSTAGRAM);
        registroResponseCall.enqueue(new Callback<RegistroResponse>() {
            @Override
            public void onResponse(Call<RegistroResponse> call, Response<RegistroResponse> response) {
                RegistroResponse registroResponse1 = response.body();
                Log.d("ID_FIREBASE", registroResponse1.getId());
                Log.d("TOKEN_FIREBASE", registroResponse1.getId_dispositivo());
                Log.d("USER_FIREBASE", registroResponse1.getId_usuario_instagram());
            }

            @Override
            public void onFailure(Call<RegistroResponse> call, Throwable t) {
            }
        });
    }*/
}
