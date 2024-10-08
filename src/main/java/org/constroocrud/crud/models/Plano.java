package org.constroocrud.crud.models;

public class Plano {
    private int id;
    private String tipo_plano;
    private double valor;
    private String descricao;
    private String nome;
    private int duracao;


    public int getId() {
        return id;
    }

    public String getTipo_plano() {
        return tipo_plano;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public Plano() {
    }

    public Plano(String tipo_plano, double valor, String descricao, String nome, int duracao) {
        this.tipo_plano = tipo_plano;
        this.valor = valor;
        this.descricao = descricao;
        this.nome = nome;
        this.duracao = duracao;
    }

    public Plano(int id, String tipo_plano, double valor, String descricao, String nome, int duracao) {
        this.id = id;
        this.tipo_plano = tipo_plano;
        this.valor = valor;
        this.descricao = descricao;
        this.nome = nome;
        this.duracao = duracao;
    }
}
