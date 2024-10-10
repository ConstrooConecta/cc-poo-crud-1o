package org.constroocrud.crud.models;

import java.util.Date;

public class PlanoAtivacao {

    //Atributos do comprador/vendedor

    private int id;
    private Date data_assinatura;
    private Date data_final;
    private char ativacao;

    //Gets do comprador/vendedor

    public int getID() {
        return id;
    }

    public Date getData_assinatura() {
        return data_assinatura;
    }

    public Date getData_final() {
        return data_final;
    }

    public char getAtivacao(){
        return this.ativacao;
    }

    //Construtores do CompradorVendedor

    //Construtor com todos os parametros

    public PlanoAtivacao(int id, Date data_assinatura, Date data_final, char ativacao) {
        this.id = id;
        this.data_assinatura = data_assinatura;
        this.data_final = data_final;
        this.ativacao = ativacao;
    }


    //Construtor sem ID

    public PlanoAtivacao(Date data_assinatura, Date data_final, char ativacao) {
        this.data_assinatura = data_assinatura;
        this.data_final = data_final;
        this.ativacao = ativacao;
    }

    //Construtor vazio

    public PlanoAtivacao(){}

    //To String
    @Override
    public String toString() {
        return "CompradorVendedor{" +
                "ID=" + id +
                ", data_assinatura='" + data_assinatura + '\'' +
                ", data_fim='" + data_final + '\'' +
                ", ativacao=" + ativacao +
                '}';
    }
}
