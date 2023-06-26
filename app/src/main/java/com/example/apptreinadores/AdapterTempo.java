package com.example.apptreinadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterTempo extends RecyclerView.Adapter<AdapterTempo.ViewHolder> {
    private List<TempoTreino> temposList;
    private AdapterTempo.OnItemClickListener listener;


    public AdapterTempo(List<TempoTreino> temposList, AdapterTempo.OnItemClickListener listener) {
        this.temposList = temposList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public AdapterTempo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tempo, parent, false);
        return new AdapterTempo.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTempo.ViewHolder holder, int position) {
        TempoTreino tempoTreino = temposList.get(position);
        holder.textViewTempoTreino.setText(Double.toString(tempoTreino.getTempo()));
        holder.textViewDistanciaTempo.setText(Integer.toString(tempoTreino.getDistancia()));
        holder.textViewDataTempo.setText(tempoTreino.getData());
    }

    @Override
    public int getItemCount() {
        return temposList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewTempoTreino;
        private TextView textViewDistanciaTempo;
        private TextView textViewDataTempo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTempoTreino = itemView.findViewById(R.id.textViewTempoTreino);
            textViewDistanciaTempo= itemView.findViewById(R.id.textViewDistanciaTempo);
            textViewDataTempo = itemView.findViewById(R.id.textViewDataTempo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                TempoTreino tempoTreino = temposList.get(position);
                listener.onItemClick(tempoTreino);
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(TempoTreino tempoTreino);
    }
}
