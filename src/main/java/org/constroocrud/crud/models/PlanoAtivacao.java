package org.constroocrud.crud.models; // Pacote da classe PlanoAtivacao

import java.util.Date; // Importação da classe Date

public class PlanoAtivacao { // Classe principal

    // Atributos do comprador/vendedor
    private int id; // ID do plano de ativação
    private Date data_assinatura; // Data de assinatura
    private Date data_final; // Data final do plano
    private char ativacao; // Estado de ativação

    // Gets do comprador/vendedor
    public int getID() { // Getter para ID
        return id;
    }

    public Date getData_assinatura() { // Getter para data de assinatura
        return data_assinatura;
    }

    public Date getData_final() { // Getter para data final
        return data_final;
    }

    public char getAtivacao() { // Getter para ativação
        return this.ativacao;
    }

    // Construtores do CompradorVendedor

    // Construtor com todos os parâmetros
    public PlanoAtivacao(int id, Date data_assinatura, Date data_final, char ativacao) {
        this.id = id;
        this.data_assinatura = data_assinatura;
        this.data_final = data_final;
        this.ativacao = ativacao;
    }

    // Construtor sem ID
    public PlanoAtivacao(Date data_assinatura, Date data_final, char ativacao) {
        this.data_assinatura = data_assinatura;
        this.data_final = data_final;
        this.ativacao = ativacao;
    }

    // Construtor vazio
    public PlanoAtivacao() { }

    // To String
    @Override
    public String toString() { // Método para representação da classe
        return "CompradorVendedor{" +
                "ID=" + id +
                ", data_assinatura='" + data_assinatura + '\'' +
                ", data_fim='" + data_final + '\'' +
                ", ativacao=" + ativacao +
                '}'; // Retorna a representação do objeto
    }
}
