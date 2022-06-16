package com.example.prueba_volley_arreglado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginScreenActivity extends AppCompatActivity {
    static final String URL_LOGIN = "http://192.168.0.4/api/PruebaVolley/volleyCRUD.php";

    String loginUser, loginPassword;

    EditText nombreUsuario, claveUsuario;
    Button acceder, registrarNuevoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        nombreUsuario = findViewById(R.id.loginUserField);
        claveUsuario = findViewById(R.id.loginPasswordField);
        acceder = findViewById(R.id.botonInicioSesion);
        registrarNuevoUsuario = findViewById(R.id.botonNuevoUsuario);

        registrarNuevoUsuario.setOnClickListener(view -> {
            Intent registrarse = new Intent(LoginScreenActivity.this, RegisterScreenActivity.class);
            startActivity(registrarse);
        });

        acceder.setOnClickListener(view -> {
            if(TextUtils.isEmpty(nombreUsuario.getText().toString().trim()) || TextUtils.isEmpty(claveUsuario.getText().toString().trim())) {
                Toast.makeText(LoginScreenActivity.this, "Nombre de Usuario o Contraseña Esta Vacía", Toast.LENGTH_SHORT).show();
            } else {
                loginUser = nombreUsuario.getText().toString().trim();
                loginPassword = claveUsuario.getText().toString().trim();

                login();
            }
        });
    }

    private void login() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL_LOGIN,
                response -> {
                    Log.e("anyText", response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String evento = jsonObject.getString("evento");

                        if(evento.equals("Success")) {

                            Toast.makeText(getApplicationContext(), "Inicio de Sesión Correcta", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginScreenActivity.this, UserListScreenActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Nombre de Usuario o Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Error "+error.getMessage(), Toast.LENGTH_LONG).show())
        {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("methodCall", "read");
                params.put("nombre_usuario", loginUser);
                params.put("clave_usuario", loginPassword);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}