package com.example.apptreinadores;

import java.io.Serializable;
import java.util.Date;

public class Remedio implements Serializable {
    private Integer id;
    private String nome;
    private double quantidade;
    private double qtdAtual;
    private double valor;
    private String dataVencimento;
    private String dataChegada;



    public Remedio(Integer id, String nome, double quantidade, double valor, String dataVencimento, String dataChegada) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.dataChegada = dataChegada;
        this.qtdAtual = quantidade;

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


    public double getQtdAtual() {
        return qtdAtual;
    }

    public void setQtdAtual(double qtdAtual) {
        this.qtdAtual = qtdAtual;
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

    public void retirarQuantidade(double quantidadeRetirada) {
        if (quantidadeRetirada <= 0) {
            throw new IllegalArgumentException("A quantidade a ser retirada deve ser maior que zero");
        }

        if (quantidadeRetirada > qtdAtual) {
            throw new IllegalArgumentException("Quantidade insuficiente de remedio");
        }

        qtdAtual -= quantidadeRetirada;
    }
}
