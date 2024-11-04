package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.Administrador;

import java.sql.*;

public class AdministradorDAO {

    // Método para inserir um administrador
    public int inserirAdministrador(Administrador administrador) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            pstmt = conn.prepareStatement("INSERT INTO administrador(nome, email, senha) VALUES (?, ?, ?)");

            pstmt.setString(1, administrador.getNome());
            pstmt.setString(2, administrador.getEmail());
            pstmt.setString(3, administrador.getSenha());

            return pstmt.executeUpdate() > 0 ? 1 : 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        }finally {
            conexao.desconectar();
        }
    }

    // Método para buscar todos os administradores
    public ResultSet buscarAdministrador() {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            pstmt = conn.prepareStatement("SELECT * FROM administrador ORDER BY nome");

            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }finally {
            conexao.desconectar();
        }
    }

    // Método para buscar um administrador pelo ID
    public ResultSet buscarAdministradorPeloID(int id) {
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            pstmt = conn.prepareStatement("SELECT * FROM administrador WHERE id = ? ORDER BY nome");
            pstmt.setInt(1, id);

            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }finally {
            conexao.desconectar();
        }
    }

    // Método para buscar um administrador pelo email
    public ResultSet buscarAdministradorPeloEmail(String email) {
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            pstmt = conn.prepareStatement("SELECT * FROM administrador WHERE email = ? ORDER BY nome");
            pstmt.setString(1, email);

            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }finally {
            conexao.desconectar();
        }
    }

    // Método para buscar um administrador pelo nome
    public ResultSet buscarAdministradorPeloNome(String nome) {
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            pstmt = conn.prepareStatement("SELECT * FROM administrador WHERE nome LIKE ? ORDER BY nome");
            pstmt.setString(1, "%" + nome + "%");
            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }
    }

    // Método para remover um administrador pelo ID
    public int removerAdministrador(int id) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            pstmt = conn.prepareStatement("DELETE FROM administrador WHERE id = ?");

            pstmt.setInt(1, id);

            return pstmt.executeUpdate() > 0 ? 1 : 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        }finally {
            conexao.desconectar();
        }
    }

    // Método para buscar a senha de um administrador pelo email
    public String buscaSenhaPorEmail(String email) {
        ResultSet resultSet = buscarAdministradorPeloEmail(email);
        try {
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
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            pstmt = conn.prepareStatement("UPDATE administrador SET nome = ?, email = ?, senha = ? WHERE id = ?");

            pstmt.setString(1, administrador.getNome());
            pstmt.setString(2, administrador.getEmail());
            pstmt.setString(3, administrador.getSenha());
            pstmt.setInt(4, id);

            return pstmt.executeUpdate() > 0 ? 1 : 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        }finally {
            conexao.desconectar();
        }
    }
}
