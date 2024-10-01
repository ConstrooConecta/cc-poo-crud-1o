package org.constroocrud.crud.entidades;

public class CategoriaProduto {

    int id;
    String nome;
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaProduto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaProduto(String nome) {
        this.nome = nome;
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
