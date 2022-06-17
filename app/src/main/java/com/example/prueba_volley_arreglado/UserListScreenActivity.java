package com.example.prueba_volley_arreglado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserListScreenActivity extends AppCompatActivity {
    static final String URL_LIST = "http://192.168.0.4/api/PruebaVolley/volleyCRUD.php";

    Button cargar;
    RecyclerView listaDeUsuarios;

    List<UserModel> usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_screen);

        cargar = findViewById(R.id.btonCargarTodosLosUsuarios);
        listaDeUsuarios = findViewById(R.id.listaUsuarios);

        cargar.setOnClickListener(view -> usuarioLista2());
    }

    private void usuarioLista2() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL_LIST,
                response -> {
                    Log.e("anyText", response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("user");

                        for(int i = 0; i < jsonArray.length(); i++) {
                            JSONObject users = jsonArray.getJSONObject(i);

                            usuarios.add(new UserModel(users.getInt("id"), users.getString("nombreusuario"), users.getString("nombre"), users.getString("apellido")));
                        }

                        RecycleViewAdapterActivity adapter = new RecycleViewAdapterActivity(UserListScreenActivity.this, usuarios);
                        listaDeUsuarios.setLayoutManager(new LinearLayoutManager(UserListScreenActivity.this));
                        listaDeUsuarios.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> Toast.makeText(getApplicationContext(), "Error "+error.getMessage(), Toast.LENGTH_LONG).show())
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("methodCall", "readAll");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void usuariosLista() {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URL_LIST,
                null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("user");

                        for(int i = 0; i < jsonArray.length(); i++) {
                            JSONObject users = jsonArray.getJSONObject(i);

                            usuarios.add(new UserModel(users.getInt("id"), users.getString("nombreusuario"), users.getString("nombre"), users.getString("apellido")));
                        }

                        RecycleViewAdapterActivity adapter = new RecycleViewAdapterActivity(UserListScreenActivity.this, usuarios);
                        listaDeUsuarios.setLayoutManager(new LinearLayoutManager(UserListScreenActivity.this));
                        listaDeUsuarios.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Error "+error.getMessage(), Toast.LENGTH_LONG).show())
        {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("methodCall", "readAll");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}