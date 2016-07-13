package com.wmartinez.devep.petaretro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfigurarCuentaActivity extends AppCompatActivity {

    private EditText etNameCuenta;
    private Button saveAccount;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        Toolbar miActionBar = (Toolbar)findViewById(R.id.cfgCuentaToolbar) ;
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNameCuenta = (EditText)findViewById(R.id.et_name_cuenta);
        saveAccount = (Button) findViewById(R.id.btn_guardar_cuenta);
        saveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAccount(v);
            }
        });
    }

    private void saveAccount(View view) {
        String userAccount = etNameCuenta.getText().toString().trim();
        SharedPreferences userAccountPreferences = getSharedPreferences("account", Context.MODE_PRIVATE);
        SharedPreferences.Editor userAccountEdit = userAccountPreferences.edit();
        userAccountEdit.putString("EditAccount", userAccount);
        if (userAccountEdit.commit()) {
            Snackbar.make(view, "Cuenta guardada!!!", Snackbar.LENGTH_LONG)
                    .setAction("EditAction", null).show();
            Intent intentEdit = new Intent(ConfigurarCuentaActivity.this, MainActivity.class);
            startActivity(intentEdit);
            finish();
        } else {
            Snackbar.make(view, "Fallo al guardar, no es usuario SandBox", Snackbar.LENGTH_LONG)
                    .setAction("EditAction", null).show();
        }
    }
}
