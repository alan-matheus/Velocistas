package com.example.apptreinadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class AdapterCavalo extends RecyclerView.Adapter<AdapterCavalo.ViewHolder> {

    private List<Cavalo> cavaloList;
    private OnItemClickListener listener;
    private OnButtonOptionsClickListener onButtonOptionsClickListener;

    public AdapterCavalo(List<Cavalo> cavaloList, OnItemClickListener listener) {
        this.cavaloList = cavaloList;
        this.listener = listener;

    }

    public interface OnButtonOptionsClickListener {
        void onOptionsClick(View view, Cavalo cavalo);
    }

    public void setOnButtonOptionsClickListener(OnButtonOptionsClickListener listener) {
        this.onButtonOptionsClickListener = listener;
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
        holder.textViewRaca.setText("Raça: "+ cavalo.getRaca());
        holder.textViewChegada.setText("Chegou: "+cavalo.getDataChegada());

        holder.opcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onButtonOptionsClickListener != null) {
                    onButtonOptionsClickListener.onOptionsClick(v, cavalo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cavaloList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewNome;
        private TextView textViewRaca;
        private TextView textViewChegada;
        private ImageButton opcoes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewRaca = itemView.findViewById(R.id.textViewRaca);
            textViewChegada = itemView.findViewById(R.id.textViewChegada);
            opcoes = itemView.findViewById(R.id.opcoes);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Cavalo cavalo = cavaloList.get(position);
                listener.onItemClick(cavalo);
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Cavalo cavalo);
    }
}
