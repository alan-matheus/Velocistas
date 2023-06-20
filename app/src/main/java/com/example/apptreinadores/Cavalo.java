package com.example.apptreinadores;


import java.io.Serializable;

public class Cavalo implements Serializable {

    private Integer id;
    private String nome;
    private String raca;
    private String dataChegada;

    public Cavalo(Integer id, String nome, String raca, String dataChegada) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.dataChegada = dataChegada;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
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
