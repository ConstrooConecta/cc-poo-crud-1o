package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.CategoriaProduto;

import java.sql.*;

public class CategoriaProdutoDAO {

    private Connection getConnection() throws SQLException {
        Conexao conexao = new Conexao();
        conexao.conectar();
        return conexao.getConn();
    }

    // Método para inserir uma categoria de produto
    public int inserirCategoriaProduto(CategoriaProduto categoriaProduto) {
        String sql = "INSERT INTO categoria_produto(nome, descricao) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, categoriaProduto.getNome());
            pstmt.setString(2, categoriaProduto.getDescricao());

            return pstmt.executeUpdate() > 0 ? 1 : 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        }
    }

    // Método para buscar todas as categorias de produtos
    public ResultSet buscarCategoriaProduto() {
        String query = "SELECT * FROM categoria_produto ORDER BY nome";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }
    }

    // Método para buscar uma categoria de produto pelo ID
    public ResultSet buscarCategoriaProdutoPeloID(int id) {
        String query = "SELECT * FROM categoria_produto WHERE id = ? ORDER BY nome";
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

    // Método para buscar uma categoria de produto pelo nome
    public ResultSet buscarCategoriaProdutoPeloNome(String nome) {
        String query = "SELECT * FROM categoria_produto WHERE nome = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nome);
            return pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }
    }

    // Método para buscar categorias de produto com nome similar
    public ResultSet buscarSimilarCategoriaProdutoPeloNome(String nome) {
        String query = "SELECT * FROM categoria_produto WHERE nome LIKE ? ORDER BY nome";
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

    // Método para remover uma categoria de produto pelo ID
    public int removerCategoriaProduto(int id) {
        if (buscarCategoriaProdutoPeloID(id) == null) {
            return 0; // Não encontrado
        }

        String remover = "DELETE FROM categoria_produto WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(remover)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0 ? 1 : 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        }
    }

    // Método para alterar uma categoria de produto
    public int alterarCategoriaProduto(int id, CategoriaProduto categoriaProduto) {
        String sql = "UPDATE categoria_produto SET nome = ?, descricao = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, categoriaProduto.getNome());
            pstmt.setString(2, categoriaProduto.getDescricao());
            pstmt.setInt(3, id);

            return pstmt.executeUpdate() > 0 ? 1 : 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        }
    }
}
