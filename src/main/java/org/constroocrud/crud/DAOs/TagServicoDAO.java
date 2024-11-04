package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.TagServico;

import java.sql.*;

public class TagServicoDAO {

    // Método para buscar todas as tags de serviço
    public ResultSet buscarTagServicos() {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            String query = "SELECT * FROM tag_servico ORDER BY nome"; // Query para selecionar todas as tags
            pstmt = conn.prepareStatement(query); // Prepara a instrução SQL
            rs = pstmt.executeQuery(); // Executa a consulta e armazena o resultado
            return rs; // Retorna o conjunto de resultados
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Exibe qualquer erro SQL
            return rs; // Retorna null em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta a conexão no final
        }
    }

    // Método para buscar uma tag de serviço pelo ID
    public ResultSet buscarTagServicoPeloID(int id) {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            String query = "SELECT * FROM tag_servico WHERE id = ? ORDER BY nome"; // Query para selecionar uma tag pelo ID
            pstmt = conn.prepareStatement(query); // Prepara a instrução SQL
            pstmt.setInt(1, id); // Define o parâmetro ID na consulta
            rs = pstmt.executeQuery(); // Executa a consulta e armazena o resultado
            return rs; // Retorna o conjunto de resultados
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Exibe qualquer erro SQL
            return rs; // Retorna null em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta a conexão no final
        }
    }

    // Método para buscar uma tag de serviço pelo nome
    public ResultSet buscarTagServicoPeloNome(String nome) {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            String query = "SELECT * FROM tag_servico WHERE nome LIKE ? ORDER BY nome"; // Query para selecionar tags com nome correspondente
            pstmt = conn.prepareStatement(query); // Prepara a instrução SQL
            pstmt.setString(1, "%" + nome + "%"); // Define o parâmetro nome na consulta (wildcard para busca parcial)
            rs = pstmt.executeQuery(); // Executa a consulta e armazena o resultado
            return rs; // Retorna o conjunto de resultados
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Exibe qualquer erro SQL
            return rs; // Retorna null em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta a conexão no final
        }
    }

    // Método para remover uma tag de serviço pelo ID
    public int removerTagServicoPeloID(int id) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Verifica se existe uma tag de serviço com esse ID
            ResultSet resultSet = buscarTagServicoPeloID(id); // Busca a tag pelo ID

            // Executa a query de remoção
            String remover = "DELETE FROM tag_servico WHERE id = ?"; // Query para deletar uma tag pelo ID
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

    // Método para inserir uma nova tag de serviço
    public int inserirTagServico(TagServico tagServico) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO tag_servico (nome, descricao) VALUES (?, ?)"); // Query para inserir uma nova tag
            // Coloca como parâmetro cada atributo do objeto TagServico
            pstmt.setString(1, tagServico.getNome()); // Define o nome da tag
            pstmt.setString(2, tagServico.getDescricao()); // Define a descrição da tag

            int rows = pstmt.executeUpdate(); // Executa a inserção

            if (rows > 0) {
                return 1; // Retorna 1 se a inserção foi bem-sucedida
            }
            return 0; // Retorna 0 se nenhuma linha foi afetada
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Exibe qualquer erro SQL
            return -1; // Retorna -1 em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta a conexão no final
        }
    }

    // Método para alterar uma tag de serviço
    public int alterarTagServico(int id, TagServico tagServico) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Prepara a consulta de atualização
            pstmt = conn.prepareStatement("UPDATE tag_servico SET nome = ?, descricao = ? WHERE id = ?"); // Query para atualizar os dados da tag
            // Define os parâmetros da consulta
            pstmt.setString(1, tagServico.getNome()); // Define o nome da tag
            pstmt.setString(2, tagServico.getDescricao()); // Define a descrição da tag
            pstmt.setInt(3, id); // Define o ID da tag que será atualizado

            int rows = pstmt.executeUpdate(); // Executa a atualização

            if (rows > 0) {
                return 1; // Retorna 1 se a alteração for bem-sucedida
            }
            return 0; // Retorna 0 se nenhuma linha foi afetada
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Exibe qualquer erro SQL
            return -1; // Retorna -1 em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta a conexão no final
        }
    }
}
