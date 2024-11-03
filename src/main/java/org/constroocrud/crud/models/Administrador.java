package org.constroocrud.crud.models; // Pacote da classe Administrador

public class Administrador { // Classe principal para representar um administrador

    private int id; // ID do administrador
    private String nome; // Nome do administrador
    private String email; // Email do administrador
    private String senha; // Senha do administrador

    // Construtor com ID
    public Administrador(int id, String nome, String email, String senha) {
        this.id = id; // Inicializa o ID
        this.nome = nome; // Inicializa o nome
        this.email = email; // Inicializa o email
        this.senha = senha; // Inicializa a senha
    }

    // Construtor sem ID (para criação de novos administradores)
    public Administrador(String nome, String email, String senha) {
        this.nome = nome; // Inicializa o nome
        this.email = email; // Inicializa o email
        this.senha = senha; // Inicializa a senha
    }

    // Construtor padrão
    public Administrador() {
    }

    // Getter para ID
    public int getId() {
        return id; // Retorna o ID do administrador
    }

    // Getter para nome
    public String getNome() {
        return nome; // Retorna o nome do administrador
    }

    // Getter para email
    public String getEmail() {
        return email; // Retorna o email do administrador
    }

    // Getter para senha
    public String getSenha() {
        return senha; // Retorna a senha do administrador
    }
}
