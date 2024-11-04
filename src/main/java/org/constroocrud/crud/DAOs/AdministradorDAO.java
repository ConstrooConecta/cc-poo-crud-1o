package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.Administrador;

import java.sql.*;

public class AdministradorDAO {

    // Método para inserir um administrador
    public int inserirAdministrador(Administrador administrador) {
        // Bloco para inicializar conexão e configurar o PreparedStatement
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Configura o comando SQL para inserir um administrador
            pstmt = conn.prepareStatement("INSERT INTO administrador(nome, email, senha) VALUES (?, ?, ?)");
            // Define os parâmetros da consulta
            pstmt.setString(1, administrador.getNome());
            pstmt.setString(2, administrador.getEmail());
            pstmt.setString(3, administrador.getSenha());
            // Executa a consulta e retorna 1 para sucesso, 0 para falha
            return pstmt.executeUpdate() > 0 ? 1 : 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }

    // Método para buscar todos os administradores
    public ResultSet buscarAdministrador() {
        // Bloco para inicializar a conexão e configurar a consulta SQL
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Configura o comando SQL para buscar todos os administradores
            pstmt = conn.prepareStatement("SELECT * FROM administrador ORDER BY nome");
            // Executa a consulta e retorna o ResultSet com os dados
            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }

    // Método para buscar um administrador pelo ID
    public ResultSet buscarAdministradorPeloID(int id) {
        // Bloco para inicializar a conexão e configurar a consulta SQL com filtro pelo ID
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Configura o comando SQL para buscar o administrador pelo ID
            pstmt = conn.prepareStatement("SELECT * FROM administrador WHERE id = ? ORDER BY nome");
            pstmt.setInt(1, id); // Define o parâmetro ID na consulta
            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }

    // Método para buscar um administrador pelo email
    public ResultSet buscarAdministradorPeloEmail(String email) {
        // Bloco para inicializar a conexão e configurar a consulta SQL com filtro pelo email
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Configura o comando SQL para buscar o administrador pelo email
            pstmt = conn.prepareStatement("SELECT * FROM administrador WHERE email = ? ORDER BY nome");
            pstmt.setString(1, email); // Define o parâmetro email na consulta
            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }

    // Método para buscar um administrador pelo nome
    public ResultSet buscarAdministradorPeloNome(String nome) {
        // Bloco para inicializar a conexão e configurar a consulta SQL com filtro pelo nome
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Configura o comando SQL para buscar administradores cujo nome contenha a string fornecida
            pstmt = conn.prepareStatement("SELECT * FROM administrador WHERE nome LIKE ? ORDER BY nome");
            pstmt.setString(1, "%" + nome + "%"); // Define o parâmetro nome com LIKE
            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }
    }

    // Método para remover um administrador pelo ID
    public int removerAdministrador(int id) {
        // Bloco para inicializar a conexão e configurar a consulta SQL para remoção pelo ID
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Configura o comando SQL para remover o administrador com o ID especificado
            pstmt = conn.prepareStatement("DELETE FROM administrador WHERE id = ?");
            pstmt.setInt(1, id); // Define o parâmetro ID na consulta
            return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 para sucesso, 0 para falha

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }

    // Método para buscar a senha de um administrador pelo email
    public String buscaSenhaPorEmail(String email) {
        // Obtém o ResultSet da busca por email
        ResultSet resultSet = buscarAdministradorPeloEmail(email);
        try {
            // Verifica se o administrador foi encontrado e retorna a senha
            if (resultSet != null && resultSet.next()) {
                return resultSet.getString("senha");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null; // Não encontrado ou erro
    }

    // Método para alterar um administrador
    public int alterarAdministrador(int id, Administrador administrador) {
        // Bloco para inicializar a conexão e configurar o PreparedStatement para alteração de dados
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Configura o comando SQL para atualizar os dados de um administrador pelo ID
            pstmt = conn.prepareStatement("UPDATE administrador SET nome = ?, email = ?, senha = ? WHERE id = ?");
            pstmt.setString(1, administrador.getNome()); // Define o parâmetro nome
            pstmt.setString(2, administrador.getEmail()); // Define o parâmetro email
            pstmt.setString(3, administrador.getSenha()); // Define o parâmetro senha
            pstmt.setInt(4, id); // Define o ID para atualizar
            return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 para sucesso, 0 para falha

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }
}
