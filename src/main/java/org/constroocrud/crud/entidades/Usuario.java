package org.constroocrud.crud.entidades;

import org.constroocrud.crud.conexao.Conexao;

public class Usuario{

    //Atributos do Usuário
    private int id;
    private String nomeCompleto;
    private String email;

    private String senha;
    private String telefoneCelular;
    private String CEP;
    private String UF;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;

    //Gets do Usuario
    public int getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public String getCEP() {
        return CEP;
    }

    public String getUF() {
        return UF;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    //Construtores do Usuario

    //Construtor com todos os parametros

    public Usuario(int id, String nomeCompleto, String email, String senha, String telefoneCelular, String UF, String cidade, String bairro, String rua, int numero, String complemento, String cep) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.telefoneCelular = telefoneCelular;
        this.UF = UF;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.CEP = cep;
    }
    //Construtor apenas sem o ID
    public Usuario(String nomeCompleto, String email, String senha, String telefoneCelular, String UF, String cidade, String bairro, String rua, int numero, String complemento, String cep) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.telefoneCelular = telefoneCelular;
        this.UF = UF;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.CEP = cep;
    }







    //Construtor vazio
    public Usuario(){

    }
    //To String
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", email='" + email + '\'' +
                ", telefoneCelular='" + telefoneCelular + '\'' +
                ", UF='" + UF + '\'' +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", CEP='" + CEP + '\'' +
                '}';
    }


}
