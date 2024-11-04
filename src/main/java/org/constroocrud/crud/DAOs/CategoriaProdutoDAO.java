package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.CategoriaProduto;

import java.sql.*;

public class CategoriaProdutoDAO {

    // Método para inserir uma categoria de produto
    public int inserirCategoriaProduto(CategoriaProduto categoriaProduto) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            pstmt = conn.prepareStatement("INSERT INTO categoria_produto(nome, descricao) VALUES (?, ?)");

            pstmt.setString(1, categoriaProduto.getNome());
            pstmt.setString(2, categoriaProduto.getDescricao());

            return pstmt.executeUpdate() > 0 ? 1 : 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        }finally{
            conexao.desconectar();
        }
    }

    // Método para buscar todas as categorias de produtos
    public ResultSet buscarCategoriaProduto() {
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            pstmt = conn.prepareStatement("SELECT * FROM categoria_produto ORDER BY nome");
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }finally {
            conexao.desconectar();
        }
    }

    // Método para buscar uma categoria de produto pelo ID
    public ResultSet buscarCategoriaProdutoPeloID(int id) {
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            pstmt = conn.prepareStatement("SELECT * FROM categoria_produto WHERE id = ? ORDER BY nome");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }finally {
            conexao.desconectar();
        }
    }

    // Método para buscar uma categoria de produto pelo nome
    public ResultSet buscarCategoriaProdutoPeloNome(String nome) {
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            pstmt = conn.prepareStatement("SELECT * FROM categoria_produto WHERE nome = ?");
            pstmt.setString(1, nome);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }finally {
            conexao.desconectar();
        }
    }

    // Método para buscar categorias de produto com nome similar
    public ResultSet buscarSimilarCategoriaProdutoPeloNome(String nome) {
        PreparedStatement pstmt;
        ResultSet rs;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            pstmt = conn.prepareStatement("SELECT * FROM categoria_produto WHERE nome LIKE ? ORDER BY nome");
            pstmt.setString(1, "%" + nome + "%");
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Erro na execução da query
        }finally {
            conexao.desconectar();
        }
    }

    // Método para remover uma categoria de produto pelo ID
    public int removerCategoriaProduto(int id) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Executa a query de remoção
            String remover = "DELETE FROM tag_servico WHERE id = ?"; // Query para deletar um tag_servico pelo ID
            pstmt = conn.prepareStatement(remover); // Prepara a instrução SQL
            pstmt.setInt(1, id); // Define o parâmetro ID na consulta

            int rows = pstmt.executeUpdate(); // Executa a remoção

            if (rows > 0) {
                return 1; // Retorna 1 se a remoção foi bem-sucedida
            }
            return 0; // Retorna 0 se nenhuma linha foi afetada
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Exibe qualquer erro SQL
            return -1; // Retorna -1 em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta a conexão no final
        }
    }

    // Método para alterar uma categoria de produto
    public int alterarCategoriaProduto(int id, CategoriaProduto categoriaProduto) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Prepara a consulta de atualização
            pstmt = conn.prepareStatement("UPDATE categoria_produto SET nome = ?, descricao = ? WHERE id = ?"); // Query para atualizar os dados do categoria_produto

            pstmt.setString(1, categoriaProduto.getNome());
            pstmt.setString(2, categoriaProduto.getDescricao());
            pstmt.setInt(3, id);

            return pstmt.executeUpdate() > 0 ? 1 : 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Erro na execução da query
        }finally {
            conexao.desconectar();
        }
    }
}
