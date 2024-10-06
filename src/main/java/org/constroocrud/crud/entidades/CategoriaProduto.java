package org.constroocrud.crud.entidades;

public class CategoriaProduto {

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

    public CategoriaProduto(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public CategoriaProduto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public CategoriaProduto() {}



    @Override
    public String toString() {
        return "CategoriaProduto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

}
