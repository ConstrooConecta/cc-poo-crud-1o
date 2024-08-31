package org.constroocrud.crud.entidades;

public class Profissional {

    //Atributos do profissional
    private int ID;
    private String CPF;
    private int IDUsuario;

    //Gets do profissional
    public int getID() {
        return ID;
    }

    public String getCPF() {
        return CPF;
    }

    public int getIDUsuario() {
        return IDUsuario;
    }

    //Construtores do Profissional

    //Construtor com todos os parametros
    public Profissional(int ID, String CPF, int IDUsuario) {
        this.ID = ID;
        this.CPF = CPF;
        this.IDUsuario = IDUsuario;
    }

    //Construtor sem ID

    public Profissional(String CPF, int IDUsuario) {
        this.CPF = CPF;
        this.IDUsuario = IDUsuario;
    }

    //Construtor vazio

    public Profissional(){}



    @Override
    public String toString() {
        return "Profissional{" +
                "ID=" + ID +
                ", CPF='" + CPF + '\'' +
                ", IDUsuario=" + IDUsuario +
                '}';
    }
}
