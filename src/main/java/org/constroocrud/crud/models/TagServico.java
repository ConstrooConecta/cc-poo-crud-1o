package org.constroocrud.crud.models;

public class TagServico {

    private int id;
    private String nome;
    private String descricao;


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public TagServico(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public TagServico(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public TagServico() {
    }

    @Override
    public String toString() {
        return "TagServico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

}
