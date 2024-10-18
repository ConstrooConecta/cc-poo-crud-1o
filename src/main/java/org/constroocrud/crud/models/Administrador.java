package org.constroocrud.crud.models; // Pacote da classe Administrador

public class Administrador { // Classe principal

    private int id; // ID do administrador
    private String nome; // Nome do administrador
    private String email; // Email do administrador
    private String senha; // Senha do administrador

    public int getId() { // Getter para ID
        return id;
    }

    public String getNome() { // Getter para nome
        return nome;
    }

    public String getEmail() { // Getter para email
        return email;
    }

    public String getSenha() { // Getter para senha
        return senha;
    }

    public Administrador(int id, String nome, String email, String senha) { // Construtor com ID
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Administrador(String nome, String email, String senha) { // Construtor sem ID
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Administrador() { // Construtor padrão
    }
}
