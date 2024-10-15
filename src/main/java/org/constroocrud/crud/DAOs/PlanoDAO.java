package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.models.Plano;

import java.sql.*;

public class PlanoDAO {
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

    // Método para buscar todos os planos
    public ResultSet buscarPlanos() {
        try {
            conectar();
            String query = "SELECT * FROM plano";
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

    // Método para buscar um plano pelo ID
    public ResultSet buscarPlanoPeloID(int id) {
        try {
            conectar();
            String query = "SELECT * FROM plano WHERE id = ?";
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

    // Método para remover um plano pelo ID
    public boolean removerPlanoPeloID(int id) {
        boolean possuiRegistros = true;

        try {
            conectar();

            // Verifica se existe um plano nesse ID
            ResultSet resultSet = buscarPlanoPeloID(id);
            if (!resultSet.next()) {
                possuiRegistros = false;
            } else {
                possuiRegistros = true;
            }

            // Executa a query
            String remover = "DELETE FROM plano WHERE id = ?";
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

    // Método para inserir um novo plano
    public boolean inserirPlano(Plano plano) {
        conectar();
        try {
            // Faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO plano (nome_plano, tempo_duracao, valor, descricao, tipo_plano) VALUES (?, ?, ?, ?, ?)");

            // Coloca como parâmetro cada atributo do objeto Plano
            pstmt.setString(1, plano.getNome());
            pstmt.setInt(2, plano.getDuracao());
            pstmt.setDouble(3, plano.getValor());
            pstmt.setString(4, plano.getDescricao());
            pstmt.setString(5, plano.getTipo_plano());

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

    // Método para alterar um plano
    public boolean alterarPlano(int id, Plano plano) {
        conectar();
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