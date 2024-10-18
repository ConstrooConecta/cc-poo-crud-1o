package org.constroocrud.crud.models; // Pacote da classe Plano

public class Plano { // Classe principal

    private int id; // ID do plano
    private String tipo_plano; // Tipo do plano
    private double valor; // Valor do plano
    private String descricao; // Descrição do plano
    private String nome; // Nome do plano
    private int duracao; // Duração do plano

    public int getId() { // Getter para ID
        return id;
    }

    public String getTipo_plano() { // Getter para tipo de plano
        return tipo_plano;
    }

    public double getValor() { // Getter para valor
        return valor;
    }

    public String getDescricao() { // Getter para descrição
        return descricao;
    }

    public String getNome() { // Getter para nome
        return nome;
    }

    public int getDuracao() { // Getter para duração
        return duracao;
    }

    public Plano() { } // Construtor padrão

    public Plano(String tipo_plano, double valor, String descricao, String nome, int duracao) { // Construtor sem ID
        this.tipo_plano = tipo_plano;
        this.valor = valor;
        this.descricao = descricao;
        this.nome = nome;
        this.duracao = duracao;
    }

    public Plano(int id, String tipo_plano, double valor, String descricao, String nome, int duracao) { // Construtor com ID
        this.id = id;
        this.tipo_plano = tipo_plano;
        this.valor = valor;
        this.descricao = descricao;
        this.nome = nome;
        this.duracao = duracao;
    }
}
