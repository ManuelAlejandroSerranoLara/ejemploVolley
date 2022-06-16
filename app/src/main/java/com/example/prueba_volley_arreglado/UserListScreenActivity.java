package com.example.prueba_volley_arreglado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

        cargar.setOnClickListener(view -> {
            usuariosLista();

            RecycleViewAdapterActivity adapter = new RecycleViewAdapterActivity(this, usuarios);
            listaDeUsuarios.setAdapter(adapter);
            listaDeUsuarios.setLayoutManager(new LinearLayoutManager(this));
        });
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> error.printStackTrace());

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}