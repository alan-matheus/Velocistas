package com.example.apptreinadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterRemedio extends RecyclerView.Adapter<AdapterRemedio.ViewHolder> {

    private List<Remedio> remedioList;
    private OnItemClickListener listener;
    private AdapterRemedio.OnButtonOptionsClickListener onButtonOptionsClickListener;


    public AdapterRemedio(List<Remedio> remedioList, OnItemClickListener listener){
        this.remedioList = remedioList;
        this.listener = listener;
    }

    public interface OnButtonOptionsClickListener {
        void onOptionsClick(View view, Remedio remedio);
    }

    public void setOnButtonOptionsClickListener(AdapterRemedio.OnButtonOptionsClickListener listener) {
        this.onButtonOptionsClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_remedio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRemedio.ViewHolder holder, int position) {
        Remedio remedio = remedioList.get(position);
        holder.textViewRemedio.setText(remedio.getNome());
        holder.textViewValor.setText("Valor: UY$ "+Double.toString(remedio.getValor()));
        holder.textViewChegada.setText("Chegou: "+remedio.getDataChegada());
        holder.textViewVencimento.setText("Vence: "+remedio.getDataVencimento());
        holder.textViewQtdRemedio.setText("Quantidade: "+Double.toString(remedio.getQuantidade()) + " ml");

        holder.opcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onButtonOptionsClickListener != null) {
                    onButtonOptionsClickListener.onOptionsClick(v, remedio);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return remedioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewRemedio;
        private TextView textViewValor;
        private TextView textViewChegada;
        private TextView textViewVencimento;
        private TextView textViewQtdRemedio;
        private TextView textViewQtdAtualRemedio;
        private ImageButton opcoes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRemedio = itemView.findViewById(R.id.textViewRemedio);
            textViewValor = itemView.findViewById(R.id.textViewValor);
            textViewChegada = itemView.findViewById(R.id.textViewChegada);
            textViewVencimento = itemView.findViewById(R.id.textViewVencimento);
            textViewQtdRemedio = itemView.findViewById(R.id.textViewQtdRemedio);
            opcoes = itemView.findViewById(R.id.opcoes);
            textViewQtdAtualRemedio = itemView.findViewById(R.id.textViewQtdAtualRemedio);

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
