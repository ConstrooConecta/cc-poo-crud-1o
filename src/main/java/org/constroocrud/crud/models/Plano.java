package org.constroocrud.crud.models; // Pacote da classe Plano

public class Plano { // Classe principal

    private int id; // ID do plano
    private String tipo_plano; // Tipo do plano
    private double valor; // Valor do plano
    private String descricao; // Descrição do plano
    private String nome; // Nome do plano
    private int duracao; // Duração do plano

    // Getter para ID
    public int getId() {
        return id;
    }

    // Getter para tipo de plano
    public String getTipo_plano() {
        return tipo_plano;
    }

    // Getter para valor
    public double getValor() {
        return valor;
    }

    // Getter para descrição
    public String getDescricao() {
        return descricao;
    }

    // Getter para nome
    public String getNome() {
        return nome;
    }

    // Getter para duração
    public int getDuracao() {
        return duracao;
    }

    // Construtor padrão
    public Plano() { }

    // Construtor sem ID
    public Plano(String tipo_plano, double valor, String descricao, String nome, int duracao) {
        this.tipo_plano = tipo_plano;
        this.valor = valor;
        this.descricao = descricao;
        this.nome = nome;
        this.duracao = duracao;
    }

    // Construtor com ID
    public Plano(int id, String tipo_plano, double valor, String descricao, String nome, int duracao) {
        this.id = id;
        this.tipo_plano = tipo_plano;
        this.valor = valor;
        this.descricao = descricao;
        this.nome = nome;
        this.duracao = duracao;
    }
}
