package com.example.apptreinadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterResultado extends RecyclerView.Adapter<AdapterResultado.ViewHolder>{


    private List<Resultado> resultadosList;
    private AdapterResultado.OnItemClickListener listener;


    public AdapterResultado(List<Resultado> resultadosList, AdapterResultado.OnItemClickListener listener) {
        this.resultadosList = resultadosList;
        this.listener = listener;

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
        holder.textViewTempo.setText(Double.toString(resultado.getTempo()));
        holder.textViewData.setText(resultado.getData());
        holder.textViewTerrenoResultado.setText(resultado.getTerreno());
        holder.textViewJockeyResultado.setText(resultado.getJockey());
        holder.textViewDistanciaResultado.setText(Integer.toString(resultado.getDistancia()));
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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewResultado = itemView.findViewById(R.id.textViewResultado);
            textViewTempo= itemView.findViewById(R.id.textViewTempo);
            textViewData = itemView.findViewById(R.id.textViewData);
            textViewTerrenoResultado = itemView.findViewById(R.id.textViewTerrenoResultado);
            textViewJockeyResultado = itemView.findViewById(R.id.textViewJockeyResultado);
            textViewDistanciaResultado = itemView.findViewById(R.id.textViewDistanciaResultado);
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
