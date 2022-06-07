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

public class RegisterScreenActivity extends AppCompatActivity {
    static final String URL_REGISTER = "http://192.168.0.4/api/PruebaVolley/volleyCRUD.php";
    String uNombre, uApellido, uNombreDeUsuario, uClaveDeUsuario;

    EditText nombre, apellido, nombreDeUsuario, claveDeUsuario;
    Button registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        nombre = findViewById(R.id.nombreField);
        apellido = findViewById(R.id.apellidoField);
        nombreDeUsuario = findViewById(R.id.usuarioField);
        claveDeUsuario = findViewById(R.id.passwordField);
        registrarse = findViewById(R.id.botonRegistro);

        registrarse.setOnClickListener(view -> {
            if(TextUtils.isEmpty(nombre.getText().toString().trim()) || TextUtils.isEmpty(apellido.getText().toString().trim()) || TextUtils.isEmpty(nombreDeUsuario.getText().toString().trim()) || TextUtils.isEmpty(claveDeUsuario.getText().toString().trim())) {
                Toast.makeText(RegisterScreenActivity.this, "Todavia Hay Campos Vacios", Toast.LENGTH_SHORT).show();
            } else {
                uNombre = nombre.getText().toString().trim();
                uApellido = apellido.getText().toString().trim();
                uNombreDeUsuario = nombreDeUsuario.getText().toString().trim();
                uClaveDeUsuario = claveDeUsuario.getText().toString().trim();

                registro();
            }
        });
    }

    private void registro() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL_REGISTER,
                response -> {
                    Log.e("anyText", response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String message = jsonObject.getString("evento");

                        if(message.equals("Success")) {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            Intent login = new Intent(RegisterScreenActivity.this, LoginScreenActivity.class);
                            startActivity(login);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Error "+error.getMessage(), Toast.LENGTH_LONG).show())
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("methodCall", "create");
                params.put("nombre", uNombre);
                params.put("apellido", uApellido);
                params.put("nombre_usuario", uNombreDeUsuario);
                params.put("clave_usuario", uClaveDeUsuario);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}