package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.CategoriaProduto;

import java.sql.*;

public class CategoriaProdutoDAO {

    // Método para inserir uma categoria de produto
    public int inserirCategoriaProduto(CategoriaProduto categoriaProduto) {
        // Bloco para conexão e inserção de dados
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Prepara a instrução SQL para inserção
            pstmt = conn.prepareStatement("INSERT INTO categoria_produto(nome, descricao) VALUES (?, ?)");
            // Define os parâmetros da consulta
            pstmt.setString(1, categoriaProduto.getNome());
            pstmt.setString(2, categoriaProduto.getDescricao());
            // Executa a inserção e retorna 1 se bem-sucedida, 0 caso contrário
            return pstmt.executeUpdate() > 0 ? 1 : 0;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }

    // Método para buscar todas as categorias de produtos
    public ResultSet buscarCategoriaProduto() {
        // Bloco para conexão e busca de dados
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Prepara a instrução SQL para buscar todas as categorias
            pstmt = conn.prepareStatement("SELECT * FROM categoria_produto ORDER BY nome");
            rs = pstmt.executeQuery(); // Executa a consulta e armazena o resultado em rs
            return rs; // Retorna o ResultSet com os dados
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Retorna null em caso de erro
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }

    // Método para buscar uma categoria de produto pelo ID
    public ResultSet buscarCategoriaProdutoPeloID(int id) {
        // Bloco para conexão e busca por ID
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Prepara a instrução SQL para buscar a categoria pelo ID
            pstmt = conn.prepareStatement("SELECT * FROM categoria_produto WHERE id = ? ORDER BY nome");
            pstmt.setInt(1, id); // Define o parâmetro ID
            rs = pstmt.executeQuery(); // Executa a consulta
            return rs; // Retorna o ResultSet com os dados
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Retorna null em caso de erro
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }

    // Método para buscar uma categoria de produto pelo nome
    public ResultSet buscarCategoriaProdutoPeloNome(String nome) {
        // Bloco para conexão e busca pelo nome exato
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Prepara a instrução SQL para buscar a categoria pelo nome
            pstmt = conn.prepareStatement("SELECT * FROM categoria_produto WHERE nome = ?");
            pstmt.setString(1, nome); // Define o parâmetro nome
            rs = pstmt.executeQuery(); // Executa a consulta
            return rs; // Retorna o ResultSet com os dados
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Retorna null em caso de erro
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }

    // Método para buscar categorias de produto com nome similar
    public ResultSet buscarSimilarCategoriaProdutoPeloNome(String nome) {
        // Bloco para conexão e busca por nome similar usando LIKE
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Prepara a instrução SQL para busca com LIKE
            pstmt = conn.prepareStatement("SELECT * FROM categoria_produto WHERE nome LIKE ? ORDER BY nome");
            pstmt.setString(1, "%" + nome + "%"); // Define o parâmetro nome com coringa
            rs = pstmt.executeQuery(); // Executa a consulta
            return rs; // Retorna o ResultSet com os dados
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Retorna null em caso de erro
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }

    // Método para remover uma categoria de produto pelo ID
    public int removerCategoriaProduto(int id) {
        // Bloco para conexão e remoção de categoria por ID
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Prepara a instrução SQL para remoção de categoria
            String remover = "DELETE FROM categoria_produto WHERE id = ?"; // Query para deletar tag_servico pelo ID
            pstmt = conn.prepareStatement(remover); // Prepara a instrução SQL
            pstmt.setInt(1, id); // Define o parâmetro ID
            int rows = pstmt.executeUpdate(); // Executa a remoção e retorna a quantidade de linhas afetadas
            return rows > 0 ? 1 : 0; // Retorna 1 se foi bem-sucedido, 0 caso contrário
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Exibe erro
            return -1; // Retorna -1 em caso de erro
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }

    // Método para alterar uma categoria de produto
    public int alterarCategoriaProduto(int id, CategoriaProduto categoriaProduto) {
        // Bloco para conexão e atualização de dados de categoria
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Prepara a instrução SQL para atualização
            pstmt = conn.prepareStatement("UPDATE categoria_produto SET nome = ?, descricao = ? WHERE id = ?");
            // Define os parâmetros da consulta de atualização
            pstmt.setString(1, categoriaProduto.getNome());
            pstmt.setString(2, categoriaProduto.getDescricao());
            pstmt.setInt(3, id);
            // Executa a atualização e retorna 1 se bem-sucedido, 0 caso contrário
            return pstmt.executeUpdate() > 0 ? 1 : 0;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        } finally {
            conexao.desconectar(); // Encerra a conexão
        }
    }
}
