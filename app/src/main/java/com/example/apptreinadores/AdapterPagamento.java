package com.example.apptreinadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPagamento extends RecyclerView.Adapter<AdapterPagamento.ViewHolder> {

    private List<Pagamento> pagamentosList;
    private OnItemClickListener listener;
    private AdapterPagamento.OnButtonOptionsClickListener onButtonOptionsClickListener;


    public AdapterPagamento(List<Pagamento> pagamentosList, OnItemClickListener listener){
        this.pagamentosList = pagamentosList;
        this.listener = listener;
    }

    public interface OnButtonOptionsClickListener {
        void onOptionsClick(View view, Pagamento pagamento);
    }

    public void setOnButtonOptionsClickListener(AdapterPagamento.OnButtonOptionsClickListener listener) {
        this.onButtonOptionsClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pagamento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPagamento.ViewHolder holder, int position) {
        Pagamento pagamento = pagamentosList.get(position);
        holder.textViewPagamento.setText(pagamento.getNome());
        holder.textViewValor.setText("Valor: UY$ "+Double.toString(pagamento.getValor()));
        holder.textViewData.setText("Data: "+pagamento.getData());

        holder.opcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onButtonOptionsClickListener != null) {
                    onButtonOptionsClickListener.onOptionsClick(v, pagamento);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pagamentosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewPagamento;
        private TextView textViewValor;
        private TextView textViewData;
        private ImageButton opcoes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPagamento = itemView.findViewById(R.id.textViewPagamento);
            textViewValor = itemView.findViewById(R.id.textViewValor);
            textViewData = itemView.findViewById(R.id.textViewData);
            opcoes = itemView.findViewById(R.id.opcoes);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Pagamento pagamento = pagamentosList.get(position);
                listener.onItemClick(pagamento);
            }
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Pagamento pagamento);
    }
}
