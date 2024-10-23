package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.TagServico;

import java.sql.*;

public class TagServicoDAO {

    // Método para buscar todas as tags de serviço
    public ResultSet buscarTagServicos() {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            String query = "SELECT * FROM tag_servico";
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

    // Método para buscar uma tag de serviço pelo ID
    public ResultSet buscarTagServicoPeloID(int id) {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            String query = "SELECT * FROM tag_servico WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            return rs;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return rs;
        } finally {
            conexao.desconectar();
        }
    }

    // Método para buscar uma tag de serviço pelo Nome
    public ResultSet buscarTagServicoPeloNome(String nome) {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            String query = "SELECT * FROM tag_servico WHERE nome like ?";
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

    // Método para remover uma tag de serviço pelo ID
    public int removerTagServicoPeloID(int id) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            // Verifica se existe uma tag de serviço com esse ID
            ResultSet resultSet = buscarTagServicoPeloID(id);
            if (!resultSet.next()) {
                return 0; // Retorna 0 se a tag não for encontrada
            }

            // Executa a query de remoção
            String remover = "DELETE FROM tag_servico WHERE id = ?";
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

    // Método para inserir uma nova tag de serviço
    public int inserirTagServico(TagServico tagServico) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            // Faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO tag_servico (nome, descricao) VALUES (?, ?)");
            // Coloca como parâmetro cada atributo do objeto TagServico
            pstmt.setString(1, tagServico.getNome());
            pstmt.setString(2, tagServico.getDescricao());

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

    // Método para alterar uma tag de serviço
    public int alterarTagServico(int id, TagServico tagServico) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            // Prepara a consulta de atualização
            pstmt = conn.prepareStatement("UPDATE tag_servico SET nome = ?, descricao = ? WHERE id = ?");
            // Define os parâmetros da consulta
            pstmt.setString(1, tagServico.getNome());
            pstmt.setString(2, tagServico.getDescricao());
            pstmt.setInt(3, id);

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
