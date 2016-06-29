package com.wmartinez.devep.petaretro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wmartinez.devep.petaretro.pojo.SendMailTask;

import java.util.Arrays;
import java.util.List;

public class FormularioActivity extends AppCompatActivity {

    String subject = "Mensaje Petagram";

    private EditText inpuComentario, toEmail, fromEmail, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Toolbar miActionBar = (Toolbar)findViewById(R.id.miActionBar) ;
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fromEmail = (EditText)findViewById(R.id.etEmailFrom);
        password = (EditText)findViewById(R.id.etPassword);
        toEmail = (EditText)findViewById(R.id.et_ToEmail);

        inpuComentario = (EditText)findViewById(R.id.et_comentario);

        final Button enviarEmail = (Button)findViewById(R.id.btn_enviar_email);
        enviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("SendMailActivity", "Presiono boton enviar.");
                String sFromEmail = (fromEmail.getText().toString());
                String fromPassword = (password.getText().toString());
                String toEmails = (toEmail.getText().toString());
                List<String> toEmailList = Arrays.asList(toEmails.split("\\s*,\\s*"));
                Log.i("SendMailActivity", "To List: " + toEmailList);
                String emailSubject = subject;
                String emailBody = inpuComentario.getText().toString();
                new SendMailTask(FormularioActivity.this).execute(sFromEmail,fromPassword, toEmailList, emailSubject, emailBody);
            }
        });
    }
}
