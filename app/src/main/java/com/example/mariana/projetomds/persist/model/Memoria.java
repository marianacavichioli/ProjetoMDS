package com.example.mariana.projetomds.persist.model;

public class Memoria {
    private long id;
    private String nome;
    private String imagem;
    private String local;
    private String data;

    public long getId() {
        return id;
    }
    public void setId(long id) {
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


    public String getData() {
        return data;
    }
    public void setData(String data){this.data = data;}
}
