package com.example.apptreinadores;

import java.io.Serializable;
import java.util.Date;

public class Remedio implements Serializable {
    private String nome;
    private double quantidade;
    private double valor;
    private String dataVencimento;
    private String dataChegada;

    public Remedio(String nome, double quantidade, double valor, String dataVencimento, String dataChegada) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.dataChegada = dataChegada;

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String  getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }


}
