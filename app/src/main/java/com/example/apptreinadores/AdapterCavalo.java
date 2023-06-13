package com.example.apptreinadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class AdapterCavalo extends RecyclerView.Adapter<AdapterCavalo.ViewHolder> {

    private List<Cavalo> cavaloList;
    private OnItemClickListener listener;


    public AdapterCavalo(List<Cavalo> cavaloList, OnItemClickListener listener) {
        this.cavaloList = cavaloList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cavalo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cavalo cavalo = cavaloList.get(position);
        holder.textViewNome.setText(cavalo.getNome());
        holder.textViewRaca.setText( cavalo.getRaca());
        holder.textViewChegada.setText(cavalo.getDataChegada());
    }

    @Override
    public int getItemCount() {
        return cavaloList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewNome;
        private TextView textViewRaca;
        private TextView textViewChegada;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewRaca = itemView.findViewById(R.id.textViewRaca);
            textViewChegada = itemView.findViewById(R.id.textViewChegada);
        }

        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition();
            Cavalo cavalo = AdapterCavalo.this.cavaloList.get(position);
            AdapterCavalo.this.listener.onItemClick(cavalo);
        }

    }

    public interface OnItemClickListener{
        void onItemClick(Cavalo cavalo);
    }
}
