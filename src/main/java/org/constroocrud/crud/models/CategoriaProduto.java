package org.constroocrud.crud.models; // Pacote da classe CategoriaProduto

public class CategoriaProduto { // Classe principal

    private int id; // ID da categoria do produto
    private String nome; // Nome da categoria
    private String descricao; // Descrição da categoria

    // Getter para ID
    public int getId() {
        return id;
    }

    // Getter para nome
    public String getNome() {
        return nome;
    }

    // Getter para descrição
    public String getDescricao() {
        return descricao;
    }

    // Construtor com ID
    public CategoriaProduto(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    // Construtor sem ID
    public CategoriaProduto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Construtor padrão
    public CategoriaProduto() { }

    // Método toString para representação da classe
    @Override
    public String toString() {
        return "CategoriaProduto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}'; // Retorna a representação da categoria
    }
}
