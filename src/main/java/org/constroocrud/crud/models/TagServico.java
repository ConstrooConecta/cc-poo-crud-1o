package org.constroocrud.crud.models; // Pacote da classe TagServico

public class TagServico { // Classe principal

    // Atributos da tag de serviço
    private int id; // ID da tag de serviço
    private String nome; // Nome da tag
    private String descricao; // Descrição da tag

    // Getters para os atributos da tag de serviço
    public int getId() { // Getter para ID
        return id; // Retorna o ID da tag
    }

    public String getNome() { // Getter para nome
        return nome; // Retorna o nome da tag
    }

    public String getDescricao() { // Getter para descrição
        return descricao; // Retorna a descrição da tag
    }

    // Construtores da classe TagServico

    // Construtor com ID
    public TagServico(int id, String nome, String descricao) {
        this.id = id; // Inicializa o ID da tag
        this.nome = nome; // Inicializa o nome da tag
        this.descricao = descricao; // Inicializa a descrição da tag
    }

    // Construtor sem ID
    public TagServico(String nome, String descricao) {
        this.nome = nome; // Inicializa o nome da tag
        this.descricao = descricao; // Inicializa a descrição da tag
    }

    // Construtor padrão
    public TagServico() { } // Construtor vazio sem parâmetros

    // Método toString para representação da classe
    @Override
    public String toString() { // Retorna uma representação em string da tag de serviço
        return "TagServico{" +
                "id=" + id + // ID da tag de serviço
                ", nome='" + nome + '\'' + // Nome da tag
                '}'; // Retorna a representação do objeto
    }
}

