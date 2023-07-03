package com.example.apptreinadores;

import android.text.style.IconMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterTempo extends RecyclerView.Adapter<AdapterTempo.ViewHolder> {
    private List<TempoTreino> temposList;
    private AdapterTempo.OnItemClickListener listener;
    private AdapterTempo.OnButtonOptionsClickListener onButtonOptionsClickListener;

    public AdapterTempo(List<TempoTreino> temposList, AdapterTempo.OnItemClickListener listener) {
        this.temposList = temposList;
        this.listener = listener;

    }

    public interface OnButtonOptionsClickListener {
        void onOptionsClick(View view, TempoTreino tempoTreino);
    }

    public void setOnButtonOptionsClickListener(AdapterTempo.OnButtonOptionsClickListener listener) {
        this.onButtonOptionsClickListener = listener;
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
        holder.textViewTempoTreino.setText(Double.toString(tempoTreino.getTempo())+" s");
        holder.textViewDistanciaTempo.setText("Distância: "+Integer.toString(tempoTreino.getDistancia())+" mts");
        holder.textViewDataTempo.setText("Data: "+tempoTreino.getData());
        holder.textViewTerrenoTempo.setText("Terreno: "+tempoTreino.getTerreno());
        holder.textViewJockeyTempo.setText("Jockey: "+ tempoTreino.getJockey());

        holder.opcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onButtonOptionsClickListener != null) {
                    onButtonOptionsClickListener.onOptionsClick(v, tempoTreino);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return temposList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewTempoTreino;
        private TextView textViewDistanciaTempo;
        private TextView textViewDataTempo;
        private TextView textViewJockeyTempo;
        private TextView textViewTerrenoTempo;
        private ImageButton opcoes;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTempoTreino = itemView.findViewById(R.id.textViewTempoTreino);
            textViewDistanciaTempo= itemView.findViewById(R.id.textViewDistanciaTempo);
            textViewDataTempo = itemView.findViewById(R.id.textViewDataTempo);
            textViewJockeyTempo = itemView.findViewById(R.id.textViewJockeyTempo);
            textViewTerrenoTempo = itemView.findViewById(R.id.textViewTerrenoTempo);
            opcoes = itemView.findViewById(R.id.opcoes);
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
