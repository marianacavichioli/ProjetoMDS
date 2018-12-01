package com.example.mariana.projetomds.persist.model;

public class Memoria {
    private int id;
    private String nome;
    private String imagem;
    private String local; // pegar a cidade (Place)
    private double latitude;
    private double longitude;
    private String data;
    private String descricao;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome){this.nome = nome;}

    public String getImagem () {
        return imagem;
    }
    public void setImagem(String imagem){this.imagem = imagem;}


    public String getLocal() {
        return local;
    }
    public void setLocal(String local){this.local = local;}


    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude){this.latitude = latitude;}

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude){this.longitude = longitude;}


    public String getData() {
        return data;
    }
    public void setData(String data){this.data = data;}

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
