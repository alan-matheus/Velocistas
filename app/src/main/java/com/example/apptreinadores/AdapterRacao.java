package com.example.apptreinadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterRacao extends RecyclerView.Adapter<AdapterRacao.ViewHolder> {

    private List<Racao> racaoList;
    private OnItemClickListener listener;


    public AdapterRacao(List<Racao> racaoList, OnItemClickListener listener){
        this.racaoList = racaoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_racao, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRacao.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return racaoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewRacao;
        private TextView textViewValor;
        private TextView textViewChegada;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRacao = itemView.findViewById(R.id.textViewRemedio);
            textViewValor = itemView.findViewById(R.id.textViewValor);
            textViewChegada = itemView.findViewById(R.id.textViewChegada);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Racao racao = racaoList.get(position);
                listener.onItemClick(racao);
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Racao racao);
    }
}