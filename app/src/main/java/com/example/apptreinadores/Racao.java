package com.example.apptreinadores;

import java.io.Serializable;

public class Racao implements Serializable {
    private Integer id;
    private String nome;
    private double quantidade;
    //private double qtdAtual;
    private double valor;
    private String dataChegada;

    public Racao(Integer id, String nome, double quantidade, double valor, String dataChegada) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataChegada = dataChegada;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
/*
    public double getQtdAtual() {
        return qtdAtual;
    }

    public void setQtdAtual(double qtdAtual) {
        this.qtdAtual = qtdAtual;
    }*/

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }
}
