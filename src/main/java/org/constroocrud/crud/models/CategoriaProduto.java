package org.constroocrud.crud.models; // Pacote da classe CategoriaProduto

public class CategoriaProduto { // Classe principal

    private int id; // ID da categoria do produto
    private String nome; // Nome da categoria
    private String descricao; // Descrição da categoria

    public int getId() { // Getter para ID
        return id;
    }

    public String getNome() { // Getter para nome
        return nome;
    }

    public String getDescricao() { // Getter para descrição
        return descricao;
    }

    public CategoriaProduto(int id, String nome, String descricao) { // Construtor com ID
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public CategoriaProduto(String nome, String descricao) { // Construtor sem ID
        this.nome = nome;
        this.descricao = descricao;
    }

    public CategoriaProduto() { } // Construtor padrão

    @Override
    public String toString() { // Método toString para representação da classe
        return "CategoriaProduto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}'; // Retorna a representação da categoria
    }
}
