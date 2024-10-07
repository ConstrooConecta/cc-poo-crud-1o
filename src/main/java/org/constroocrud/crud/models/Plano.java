package org.constroocrud.crud.models;

public class Plano {
    private int id;
    private String tipo_plano;
    private double valor;
    private String descricao;

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

    public Plano(int id, String tipo_plano, double valor, String descricao) {
        this.id = id;
        this.tipo_plano = tipo_plano;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Plano(String tipo_plano, double valor, String descricao) {
        this.tipo_plano = tipo_plano;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Plano() {
    }
}
