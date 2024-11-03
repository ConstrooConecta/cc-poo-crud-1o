package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.Administrador;

import java.sql.*;

public class AdministradorDAO {

    private Connection getConnection() throws SQLException {
        Conexao conexao = new Conexao();
        conexao.conectar();
        return conexao.getConn();
    }

    // Método para inserir um administrador
    public int inserirAdministrador(Administrador administrador) {
        String sql = "INSERT INTO administrador(nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, administrador.getNome());
            pstmt.setString(2, administrador.getEmail());
            pstmt.setString(3, administrador.getSenha());

            return pstmt.executeUpdate() > 0 ? 1 : 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        }
    }

    // Método para buscar todos os administradores
    public ResultSet buscarAdministrador() {
        String query = "SELECT * FROM administrador ORDER BY nome";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }
    }

    // Método para buscar um administrador pelo ID
    public ResultSet buscarAdministradorPeloID(int id) {
        String query = "SELECT * FROM administrador WHERE id = ? ORDER BY nome";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }
    }

    // Método para buscar um administrador pelo email
    public ResultSet buscarAdministradorPeloEmail(String email) {
        String query = "SELECT * FROM administrador WHERE email = ? ORDER BY nome";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }
    }

    // Método para buscar um administrador pelo nome
    public ResultSet buscarAdministradorPeloNome(String nome) {
        String query = "SELECT * FROM administrador WHERE nome LIKE ? ORDER BY nome";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + nome + "%");
            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }
    }

    // Método para remover um administrador pelo ID
    public int removerAdministrador(int id) {
        if (buscarAdministradorPeloID(id) == null) {
            return 0; // Não encontrado
        }

        String remover = "DELETE FROM administrador WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(remover)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0 ? 1 : 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
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
        String sql = "UPDATE administrador SET nome = ?, email = ?, senha = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, administrador.getNome());
            pstmt.setString(2, administrador.getEmail());
            pstmt.setString(3, administrador.getSenha());
            pstmt.setInt(4, id);

            return pstmt.executeUpdate() > 0 ? 1 : 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        }
    }
}
