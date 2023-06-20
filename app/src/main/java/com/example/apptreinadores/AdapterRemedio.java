package com.example.apptreinadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterRemedio extends RecyclerView.Adapter<AdapterRemedio.ViewHolder> {

    private List<Remedio> remedioList;
    private OnItemClickListener listener;


    public AdapterRemedio(List<Remedio> remedioList, OnItemClickListener listener){
        this.remedioList = remedioList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_remedio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRemedio.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return remedioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewRemedio;
        private TextView textViewValor;
        private TextView textViewChegada;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRemedio = itemView.findViewById(R.id.textViewRemedio);
            textViewValor = itemView.findViewById(R.id.textViewValor);
            textViewChegada = itemView.findViewById(R.id.textViewChegada);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Remedio remedio = remedioList.get(position);
                listener.onItemClick(remedio);
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Remedio remedio);
    }
}
