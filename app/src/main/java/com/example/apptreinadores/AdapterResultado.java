package com.example.apptreinadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterResultado extends RecyclerView.Adapter<AdapterResultado.ViewHolder>{


    private List<Resultado> resultadosList;
    private AdapterResultado.OnItemClickListener listener;
    private AdapterResultado.OnButtonOptionsClickListener onButtonOptionsClickListener;


    public AdapterResultado(List<Resultado> resultadosList, AdapterResultado.OnItemClickListener listener) {
        this.resultadosList = resultadosList;
        this.listener = listener;

    }

    public interface OnButtonOptionsClickListener {
        void onOptionsClick(View view, Resultado resultado);
    }

    public void setOnButtonOptionsClickListener(AdapterResultado.OnButtonOptionsClickListener listener) {
        this.onButtonOptionsClickListener = listener;
    }

    @NonNull
    @Override
    public AdapterResultado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultado, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterResultado.ViewHolder holder, int position) {
        Resultado resultado = resultadosList.get(position);
        holder.textViewResultado.setText(resultado.getNome());
    holder.textViewTempo.setText("Tempo: "+Double.toString(resultado.getTempo())+" s");
        holder.textViewData.setText("Data: "+resultado.getData());
        holder.textViewTerrenoResultado.setText("Terreno: "+resultado.getTerreno());
        holder.textViewJockeyResultado.setText("Jockey: "+resultado.getJockey());
        holder.textViewDistanciaResultado.setText("Distância: "+Integer.toString(resultado.getDistancia())+" mts");

        holder.opcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onButtonOptionsClickListener != null) {
                    onButtonOptionsClickListener.onOptionsClick(v, resultado);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultadosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewResultado;
        private TextView textViewTempo;
        private TextView textViewData;
        private TextView textViewTerrenoResultado;
        private TextView textViewJockeyResultado;
        private TextView textViewDistanciaResultado;
        private ImageButton opcoes;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewResultado = itemView.findViewById(R.id.textViewResultado);
            textViewTempo= itemView.findViewById(R.id.textViewTempo);
            textViewData = itemView.findViewById(R.id.textViewData);
            textViewTerrenoResultado = itemView.findViewById(R.id.textViewTerrenoResultado);
            textViewJockeyResultado = itemView.findViewById(R.id.textViewJockeyResultado);
            textViewDistanciaResultado = itemView.findViewById(R.id.textViewDistanciaResultado);
            opcoes = itemView.findViewById(R.id.opcoes);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Resultado resultado = resultadosList.get(position);
                listener.onItemClick(resultado);
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Resultado resultado);
    }
}
