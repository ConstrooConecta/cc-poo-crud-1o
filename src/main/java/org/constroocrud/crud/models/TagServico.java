package org.constroocrud.crud.models; // Pacote da classe TagServico

public class TagServico { // Classe principal

    private int id; // ID da tag de serviço
    private String nome; // Nome da tag
    private String descricao; // Descrição da tag

    public int getId() { // Getter para ID
        return id;
    }

    public String getNome() { // Getter para nome
        return nome;
    }

    public String getDescricao() { // Getter para descrição
        return descricao;
    }

    public TagServico(int id, String nome, String descricao) { // Construtor com ID
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public TagServico(String nome, String descricao) { // Construtor sem ID
        this.nome = nome;
        this.descricao = descricao;
    }

    public TagServico() { } // Construtor padrão

    @Override
    public String toString() { // Método toString para representação da classe
        return "TagServico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}'; // Retorna a representação da tag de serviço
    }
}
