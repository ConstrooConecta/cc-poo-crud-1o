package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.models.CategoriaProduto;

import java.sql.*;

public class CategoriaProdutoDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public Connection getConn() {
        return conn;
    }

    public PreparedStatement getPstmt() {
        return pstmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    // Método que faz a conexão com o banco de dados
    public boolean conectar() {
        try {
            Class.forName("org.postgresql.Driver");

            String dbUrl = System.getenv("CC_URL");
            String dbUser = System.getenv("CC_USER");
            String dbPassword = System.getenv("CC_PASSWORD");

            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
            return false;
        }
    }

    public boolean inserirCategoriaProduto(CategoriaProduto categoriaProduto) {
        conectar();
        try {
            // Faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO categoria_produto(nome, descricao) VALUES (?, ?)");

            // Coloca como parâmetro cada atributo do objeto CategoriaProduto por meio dos getters
            pstmt.setString(1, categoriaProduto.getNome());
            pstmt.setString(2, categoriaProduto.getDescricao());

            pstmt.execute();

            // Retorna true caso a execução da query seja bem-sucedida
            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            // Retorna false caso a execução da query seja mal-sucedida
            return false;
        }
    }

    public ResultSet buscarCategoriaProduto() {
        try {
            conectar();
            String query = "SELECT * FROM categoria_produto";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return rs;

        }
    }

    public ResultSet buscarCategoriaProdutoPeloID(int id) {
        try {
            conectar();
            String query = "SELECT * FROM categoria_produto WHERE id = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            return rs;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return rs;

        }
    }

    public boolean removerCategoriaProduto(int id) {
        boolean possuiRegistros = true;

        try {
            conectar();

            // Verifica se existe uma categoria de produto com o ID fornecido
            ResultSet resultSet = buscarCategoriaProdutoPeloID(id);
            if (!resultSet.next()) {
                possuiRegistros = false;
            } else {
                possuiRegistros = true;
            }

            // Executa a query
            String remover = "DELETE FROM categoria_produto WHERE id = ?";
            pstmt = conn.prepareStatement(remover);
            pstmt.setInt(1, id);
            pstmt.execute();

            return possuiRegistros;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    public boolean alterarCategoriaProduto(int id, CategoriaProduto categoriaProduto) {
        conectar();
        try {
            // Prepara uma única instrução com marcadores para todas as colunas
            pstmt = conn.prepareStatement("UPDATE categoria_produto SET nome = ?, descricao = ? WHERE id = ?");

            // Define os parâmetros
            pstmt.setString(1, categoriaProduto.getNome());
            pstmt.setString(2, categoriaProduto.getDescricao());
            pstmt.setInt(3, id);

            pstmt.execute();
            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
}
