package com.example.prueba_volley_arreglado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class UserScreenActivity extends AppCompatActivity {
    TextView userName, passWord, userNombre, userApellido;
    Button cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        userName = findViewById(R.id.textUsername);
        passWord = findViewById(R.id.textPassword);
        userNombre = findViewById(R.id.textName);
        userApellido = findViewById(R.id.textSurname);
        cerrar = findViewById(R.id.botonCerrarSesion);

        String nombre = getIntent().getStringExtra("nombre");
        String apellido = getIntent().getStringExtra("apellido");
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");

        userName.setText(getString(R.string.InformacionUsuario1, username));
        userNombre.setText(getString(R.string.InformacionUsuario2, nombre));
        userApellido.setText(getString(R.string.InformacionUsuario3, apellido));
        passWord.setText(getString(R.string.InformacionUsuario4, password));

        cerrar.setOnClickListener(view -> {
            Intent salir = new Intent(UserScreenActivity.this, LoginScreenActivity.class);
            startActivity(salir);
            finish();
        });
    }
}