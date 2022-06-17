package com.example.prueba_volley_arreglado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapterActivity extends RecyclerView.Adapter<RecycleViewAdapterActivity.MyViewHolder> {
    Context context;
    List<UserModel> usuarios;

    public RecycleViewAdapterActivity(Context context, List<UserModel> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public RecycleViewAdapterActivity.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row, parent, false);
        return new RecycleViewAdapterActivity.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapterActivity.MyViewHolder holder, int position) {
        holder.tvID.setText(context.getString(R.string.SalidaID, usuarios.get(position).getUserID()));
        holder.tvUsername.setText(context.getString(R.string.SalidaUsername, usuarios.get(position).getUsername()));
        holder.tvName.setText(context.getString(R.string.SalidaName, usuarios.get(position).getName()));
        holder.tvSurname.setText(context.getString(R.string.SalidaSurname, usuarios.get(position).getSurname()));
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvID;
        TextView tvUsername;
        TextView tvName;
        TextView tvSurname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvID = itemView.findViewById(R.id.textViewDataID);
            tvUsername = itemView.findViewById(R.id.textViewDataUsername);
            tvName = itemView.findViewById(R.id.textViewDataNombre);
            tvSurname = itemView.findViewById(R.id.textViewDataApellido);
        }
    }
}
