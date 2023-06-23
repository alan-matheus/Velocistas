package com.example.apptreinadores;

public class Pagamento {

    private Integer id;
    private String nome;
    private double valor;
    private String data;

    public Pagamento(Integer id, String nome, double valor, String data) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
