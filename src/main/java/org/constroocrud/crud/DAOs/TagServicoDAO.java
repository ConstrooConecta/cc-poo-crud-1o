package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.models.TagServico;

import java.sql.*;

public class TagServicoDAO {
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

    // Método para inserir uma nova tag de serviço
    public boolean inserirTagServico(TagServico tagServico) {
        conectar();
        try {
            // Faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO tag_servico (nome, descricao) VALUES (?, ?)");

            // Coloca como parâmetro cada atributo do objeto TagServico
            pstmt.setString(1, tagServico.getNome());
            pstmt.setString(2, tagServico.getDescricao());

            pstmt.execute();

            // Retorna true caso a execução da query seja bem-sucedida
            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            // Retorna false caso a execução da query seja mal-sucedida
            return false;
        } finally {
            desconectar();
        }
    }

    // Método para buscar todas as tags de serviço
    public ResultSet buscarTagServico() {
        try {
            conectar();
            String query = "SELECT * FROM tag_servico";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return rs;

        } finally {
            desconectar();
        }
    }

    // Método para buscar uma tag de serviço pelo ID
    public ResultSet buscarTagServicoPeloID(int id) {
        try {
            conectar();
            String query = "SELECT * FROM tag_servico WHERE id = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            return rs;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return rs;

        } finally {
            desconectar();
        }
    }

    // Método para remover uma tag de serviço pelo ID
    public boolean removerTagServicoPeloID(int id) {
        boolean possuiRegistros = true;

        try {
            conectar();

            // Verifica se existe uma tag de serviço nesse ID
            ResultSet resultSet = buscarTagServicoPeloID(id);
            if (!resultSet.next()) {
                possuiRegistros = false;
            } else {
                possuiRegistros = true;
            }

            // Executa a query de remoção
            String remover = "DELETE FROM tag_servico WHERE id = ?";
            pstmt = conn.prepareStatement(remover);
            pstmt.setInt(1, id);
            pstmt.execute();

            return possuiRegistros;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    // Método para alterar uma tag de serviço
    public boolean alterarTagServico(int id, TagServico tagServico) {
        conectar();
        try {
            // Prepara a consulta de atualização
            pstmt = conn.prepareStatement("UPDATE tag_servico SET nome = ?, descricao = ? WHERE id = ?");

            // Define os parâmetros da consulta
            pstmt.setString(1, tagServico.getNome());
            pstmt.setString(2, tagServico.getDescricao());
            pstmt.setInt(3, id);

            pstmt.execute();

            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    // Método para desconectar do banco
    private void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}