package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.Plano;

import java.sql.*;

public class PlanoDAO {

    // Método para buscar todos os planos
    public ResultSet buscarPlanos() {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            String query = "SELECT * FROM plano order by nome_plano";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return rs;
        } finally {
            conexao.desconectar();
        }
    }

    // Método para buscar um plano pelo ID
    public ResultSet buscarPlanoPeloID(int id) {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            String query = "SELECT * FROM plano WHERE id = ? order by nome_plano";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        } finally {
            conexao.desconectar();
        }
    }
    public ResultSet buscarPlanoPeloNome(String nome) {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            String query = "SELECT * FROM plano WHERE nome_plano like ? order by nome_plano";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,"%"+nome+"%");
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return rs;
        } finally {
            conexao.desconectar();
        }
    }

    // Método para remover um plano pelo ID
    public int removerPlanoPeloID(int id) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            // Verifica se existe um plano com esse ID
            ResultSet resultSet = buscarPlanoPeloID(id);
            if (!resultSet.next()) {
                return 0; // Retorna 0 se o plano não for encontrado
            }

            // Executa a query de remoção
            String remover = "DELETE FROM plano WHERE id = ?";
            pstmt = conn.prepareStatement(remover);
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                return 1; // Retorna 1 se a remoção for bem-sucedida
            }
            return 0; // Retorna 0 se nenhuma linha foi afetada
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Retorna -1 em caso de erro
        } finally {
            conexao.desconectar();
        }
    }

    // Método para inserir um novo plano
    public int inserirPlano(Plano plano) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            // Faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO plano (nome_plano, tempo_duracao, valor, descricao, tipo_plano) VALUES (?, ?, ?, ?, ?)");
            // Coloca como parâmetro cada atributo do objeto Plano
            pstmt.setString(1, plano.getNome());
            pstmt.setInt(2, plano.getDuracao());
            pstmt.setDouble(3, plano.getValor());
            pstmt.setString(4, plano.getDescricao());
            pstmt.setString(5, plano.getTipo_plano());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                return 1; // Retorna 1 se a inserção for bem-sucedida
            }
            return 0; // Retorna 0 se nenhuma linha foi afetada
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Retorna -1 em caso de erro
        } finally {
            conexao.desconectar();
        }
    }

    // Método para alterar um plano
    public int alterarPlano(int id, Plano plano) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            // Prepara a consulta de atualização
            pstmt = conn.prepareStatement("UPDATE plano SET nome_plano = ?, descricao = ?, tempo_duracao = ?, valor = ?, tipo_plano = ? WHERE id = ?");
            // Define os parâmetros da consulta
            pstmt.setString(1, plano.getNome());
            pstmt.setString(2, plano.getDescricao());
            pstmt.setInt(3, plano.getDuracao());
            pstmt.setDouble(4, plano.getValor());
            pstmt.setString(5, plano.getTipo_plano());
            pstmt.setInt(6, id);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                return 1; // Retorna 1 se a alteração for bem-sucedida
            }
            return 0; // Retorna 0 se nenhuma linha foi afetada
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Retorna -1 em caso de erro
        } finally {
            conexao.desconectar();
        }
    }
}
