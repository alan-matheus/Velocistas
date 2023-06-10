package com.example.apptreinadores;


public class Cavalo {

    private String nome;
    private String raca;
    private String dataChegada;

    public Cavalo(String nome, String raca, String dataChegada) {

        this.nome = nome;
        this.raca = raca;
        this.dataChegada = dataChegada;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }
}