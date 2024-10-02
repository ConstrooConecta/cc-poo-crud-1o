package org.constroocrud.crud.entidades;

public class TagServico {

    int id;
    String nome;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TagServico(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TagServico(String nome) {
        this.nome = nome;
    }

    public TagServico() {}



    @Override
    public String toString() {
        return "TagServico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

}
