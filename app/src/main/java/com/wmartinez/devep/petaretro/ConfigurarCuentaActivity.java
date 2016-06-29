package com.wmartinez.devep.petaretro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wmartinez.devep.petaretro.vista.PerfilFragment;

public class ConfigurarCuentaActivity extends AppCompatActivity {

    private EditText etNameCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        Toolbar miActionBar = (Toolbar)findViewById(R.id.cfgCuentaToolbar) ;
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNameCuenta = (EditText)findViewById(R.id.et_name_cuenta);

        final Button saveAccount = (Button)findViewById(R.id.btn_guardar_cuenta);
        saveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNameCuenta != null){
                   Fragment perfilFragmentPresenter = new PerfilFragment();
                    Bundle args = new Bundle();
                    args.putString("account", etNameCuenta.getText().toString());
                    perfilFragmentPresenter.setArguments(args);

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentPerfil, perfilFragmentPresenter);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    /*Intent iPerfil = new Intent(ConfigurarCuentaActivity.this, PerfilFragmentViewPresenter.class);
                    iPerfil.putExtra("account", etNameCuenta.getText());
                    startActivity(iPerfil);*/
                }
                else {
                    Fragment perfilFragmentPresenter = new PerfilFragment();
                    Bundle args = new Bundle();
                    args.putString("account", "petamaster");
                    perfilFragmentPresenter.setArguments(args);

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentPerfil, perfilFragmentPresenter);
                    transaction.addToBackStack(null);

                    /*transaction.commit();
                    Intent iPerfil = new Intent(ConfigurarCuentaActivity.this, PerfilFragmentViewPresenter.class);
                    iPerfil.putExtra("account", "petamaster");
                    startActivity(iPerfil);*/
                }
            }
        });
    }
}
