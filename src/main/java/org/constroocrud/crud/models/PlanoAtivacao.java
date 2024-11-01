package org.constroocrud.crud.models; // Pacote da classe PlanoAtivacao

import java.util.Date; // Importação da classe Date

public class PlanoAtivacao { // Classe principal

    // Atributos do plano de ativação
    private int id; // ID do plano de ativação
    private Date data_assinatura; // Data de assinatura
    private Date data_final; // Data final do plano
    private char ativacao; // Estado de ativação (ex.: 'A' para ativo, 'I' para inativo)

    // Gets do plano de ativação
    public int getID() { // Getter para ID
        return id; // Retorna o ID do plano de ativação
    }

    public Date getData_assinatura() { // Getter para data de assinatura
        return data_assinatura; // Retorna a data de assinatura
    }

    public Date getData_final() { // Getter para data final
        return data_final; // Retorna a data final do plano
    }

    public char getAtivacao() { // Getter para ativação
        return this.ativacao; // Retorna o estado de ativação do plano
    }

    // Construtores da classe PlanoAtivacao

    // Construtor com todos os parâmetros
    public PlanoAtivacao(int id, Date data_assinatura, Date data_final, char ativacao) {
        this.id = id; // Inicializa o ID
        this.data_assinatura = data_assinatura; // Inicializa a data de assinatura
        this.data_final = data_final; // Inicializa a data final
        this.ativacao = ativacao; // Inicializa o estado de ativação
    }

    // Construtor sem ID
    public PlanoAtivacao(Date data_assinatura, Date data_final, char ativacao) {
        this.data_assinatura = data_assinatura; // Inicializa a data de assinatura
        this.data_final = data_final; // Inicializa a data final
        this.ativacao = ativacao; // Inicializa o estado de ativação
    }

    // Construtor vazio
    public PlanoAtivacao() { } // Construtor padrão sem parâmetros

    // To String
    @Override
    public String toString() { // Método para representação da classe
        return "PlanoAtivacao{" +
                "ID=" + id + // ID do plano de ativação
                ", data_assinatura='" + data_assinatura + '\'' + // Data de assinatura
                ", data_fim='" + data_final + '\'' + // Data final do plano
                ", ativacao=" + ativacao + // Estado de ativação
                '}'; // Retorna a representação do objeto como string
    }
}
