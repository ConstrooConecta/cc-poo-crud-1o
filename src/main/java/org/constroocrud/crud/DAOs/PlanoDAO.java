package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.Plano;

import java.sql.*;

public class PlanoDAO {

    // Método para buscar todos os planos
    public ResultSet buscarPlanos() {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            String query = "SELECT * FROM plano ORDER BY id"; // Query para selecionar todos os planos
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

    // Método para buscar um plano pelo ID
    public ResultSet buscarPlanoPeloID(int id) {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            String query = "SELECT * FROM plano WHERE id = ?"; // Query para selecionar um plano pelo ID
            pstmt = conn.prepareStatement(query); // Prepara a instrução SQL
            pstmt.setInt(1, id); // Define o parâmetro ID na consulta
            rs = pstmt.executeQuery(); // Executa a consulta e armazena o resultado
            return rs; // Retorna o conjunto de resultados
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Exibe qualquer erro SQL
            return null; // Retorna null em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta a conexão no final
        }
    }

    // Método para buscar um plano pelo nome
    public ResultSet buscarPlanoPeloNome(String nome) {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            String query = "SELECT * FROM plano WHERE nome_plano LIKE ? ORDER BY id"; // Query para selecionar planos com nome correspondente
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

    // Método para remover um plano pelo ID
    public int removerPlanoPeloID(int id) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Verifica se existe um plano com esse ID
            ResultSet resultSet = buscarPlanoPeloID(id); // Busca o plano pelo ID
            if (!resultSet.next()) {
                return 0; // Retorna 0 se o plano não for encontrado
            }

            // Executa a query de remoção
            String remover = "DELETE FROM plano WHERE id = ?"; // Query para deletar um plano pelo ID
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

    // Método para inserir um novo plano
    public int inserirPlano(Plano plano) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO plano (nome_plano, tempo_duracao, valor, descricao, tipo_plano) VALUES (?, ?, ?, ?, ?)"); // Query para inserir um novo plano
            // Coloca como parâmetro cada atributo do objeto Plano
            pstmt.setString(1, plano.getNome()); // Define o nome do plano
            pstmt.setInt(2, plano.getDuracao()); // Define a duração do plano
            pstmt.setDouble(3, plano.getValor()); // Define o valor do plano
            pstmt.setString(4, plano.getDescricao()); // Define a descrição do plano
            pstmt.setString(5, plano.getTipo_plano()); // Define o tipo do plano

            int rows = pstmt.executeUpdate(); // Executa a inserção

            if (rows > 0) {
                return 1; // Retorna 1 se a inserção for bem-sucedida
            }
            return 0; // Retorna 0 se nenhuma linha foi afetada
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Exibe qualquer erro SQL
            return -1; // Retorna -1 em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta a conexão no final
        }
    }

    // Método para alterar um plano
    public int alterarPlano(int id, Plano plano) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        try {
            // Prepara a consulta de atualização
            pstmt = conn.prepareStatement("UPDATE plano SET nome_plano = ?, descricao = ?, tempo_duracao = ?, valor = ?, tipo_plano = ? WHERE id = ?"); // Query para atualizar os dados do plano
            // Define os parâmetros da consulta
            pstmt.setString(1, plano.getNome()); // Define o nome do plano
            pstmt.setString(2, plano.getDescricao()); // Define a descrição do plano
            pstmt.setInt(3, plano.getDuracao()); // Define a duração do plano
            pstmt.setDouble(4, plano.getValor()); // Define o valor do plano
            pstmt.setString(5, plano.getTipo_plano()); // Define o tipo do plano
            pstmt.setInt(6, id); // Define o ID do plano que será atualizado

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
