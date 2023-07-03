package com.example.apptreinadores;

import java.io.Serializable;

public class TempoTreino implements Serializable {

    private Integer id;
    private double tempo;
    private Integer distancia;
    private String terreno;
    private String data;
    private String jockey;

    public TempoTreino(Integer id, double tempo, Integer distancia, String terreno, String data, String jockey) {
        this.id = id;
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

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
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
