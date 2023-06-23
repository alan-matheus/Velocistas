package com.example.apptreinadores;

public class Resultado {

    private Integer id;
    private String nome;
    private double tempo;
    private int distancia;
    private String terreno;
    private String data;
    private String jockey;

    public Resultado(Integer id, String nome, double tempo, int distancia, String terreno, String data, String jockey) {
        this.id = id;
        this.nome = nome;
        this.tempo = tempo;
        this.distancia = distancia;
        this.terreno = terreno;
        this.data = data;
        this.jockey = jockey;
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

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getJockey() {
        return jockey;
    }

    public void setJockey(String jockey) {
        this.jockey = jockey;
    }
}
