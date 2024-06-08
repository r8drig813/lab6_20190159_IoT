package com.example.lab6_20190159;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class IngresoAdapter extends RecyclerView.Adapter<IngresoAdapter.IngresoViewHolder> {

    private List<Ingreso> ingresoList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");



    public IngresoAdapter(List<Ingreso> ingresoList) {
        this.ingresoList = ingresoList;
    }
    public void setIngresoList(List<Ingreso> ingresoList) {
        this.ingresoList = ingresoList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public IngresoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingreso, parent, false);
        return new IngresoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngresoViewHolder holder, int position) {
        Ingreso ingreso = ingresoList.get(position);
        holder.tituloTextView.setText(ingreso.getTitulo());
        holder.montoTextView.setText(String.valueOf(ingreso.getMonto()));
        holder.descripcionTextView.setText(ingreso.getDescripcion());
        holder.fechaTextView.setText(dateFormat.format(ingreso.getFecha()));
    }

    @Override
    public int getItemCount() {
        return ingresoList.size();
    }

    public static class IngresoViewHolder extends RecyclerView.ViewHolder {
        TextView tituloTextView;
        TextView montoTextView;
        TextView descripcionTextView;
        TextView fechaTextView;

        public IngresoViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.tituloTextView);
            montoTextView = itemView.findViewById(R.id.montoTextView);
            descripcionTextView = itemView.findViewById(R.id.descripcionTextView);
            fechaTextView = itemView.findViewById(R.id.fechaTextView);
        }
    }
}
