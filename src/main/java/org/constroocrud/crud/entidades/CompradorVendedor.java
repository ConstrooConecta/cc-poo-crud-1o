package org.constroocrud.crud.entidades;

public class CompradorVendedor {

    //Atributos do comprador/vendedor

    private int ID;
    private String CPF;
    private int IDUsuario;

    //Gets do comprador/vendedor

    public int getID() {
        return ID;
    }

    public String getCPF() {
        return CPF;
    }

    public int getIDUsuario() {
        return IDUsuario;
    }

    //Construtores do CompradorVendedor

    //Construtor com todos os parametros
    public CompradorVendedor(int ID, String CPF, int IDUsuario) {
        this.ID = ID;
        this.CPF = CPF;
        this.IDUsuario = IDUsuario;
    }

    //Construtor sem ID

    public CompradorVendedor(String CPF, int IDUsuario) {
        this.CPF = CPF;
        this.IDUsuario = IDUsuario;
    }

    //Construtor vazio

    public CompradorVendedor(){}

    //To String

    @Override
    public String toString() {
        return "CompradorVendedor{" +
                "ID=" + ID +
                ", CPF='" + CPF + '\'' +
                ", IDUsuario=" + IDUsuario +
                '}';
    }
}
