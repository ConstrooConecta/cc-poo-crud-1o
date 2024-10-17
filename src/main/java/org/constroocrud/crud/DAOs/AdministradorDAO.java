package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.Administrador;

import java.sql.*;

public class AdministradorDAO {

    // Método para inserir um administrador
    public int inserirAdministrador(Administrador administrador) {
        Conexao conexao = new Conexao();
        conexao.conectar(); // Conecta ao banco de dados
        Connection conn = conexao.getConn();
        try {
            // Prepara o comando SQL
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO administrador(nome, email, senha) VALUES (?, ?, ?)");

            // Coloca como parâmetro cada atributo do objeto Administrador por meio dos getters
            pstmt.setString(1, administrador.getNome());
            pstmt.setString(2, administrador.getEmail());
            pstmt.setString(3, administrador.getSenha());

            // Retorna 1 se a execução da query for bem-sucedida, caso contrário, retorna 0
            int rows = pstmt.executeUpdate();
            if (rows > 0){
                return 1;
            }
            return 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Retorna -1 se a execução da query for mal-sucedida
        } finally {
            conexao.desconectar(); // Desconecta do banco de dados
        }
    }

    // Método para buscar todos os administradores
    public ResultSet buscarAdministrador() {
        Conexao conexao = new Conexao();
        conexao.conectar(); // Conecta ao banco de dados
        Connection conn = conexao.getConn();

        try {
            String query = "SELECT * FROM administrador";
            PreparedStatement pstmt = conn.prepareStatement(query);
            return pstmt.executeQuery(); // Retorna o ResultSet dos administradores

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Retorna null em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta do banco de dados
        }
    }

    // Método para buscar um administrador pelo ID
    public ResultSet buscarAdministradorPeloID(int id) {
        Conexao conexao = new Conexao();
        conexao.conectar(); // Conecta ao banco de dados
        Connection conn = conexao.getConn();

        try {
            String query = "SELECT * FROM administrador WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            return pstmt.executeQuery(); // Retorna o ResultSet do administrador

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Retorna null em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta do banco de dados
        }
    }

    // Método para buscar um administrador pelo email
    public ResultSet buscarAdministradorPeloEmail(String email) {
        Conexao conexao = new Conexao();
        conexao.conectar(); // Conecta ao banco de dados
        Connection conn = conexao.getConn();

        try {
            String query = "SELECT * FROM administrador WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            return pstmt.executeQuery(); // Retorna o ResultSet do administrador

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null; // Retorna null em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta do banco de dados
        }
    }

    // Método para remover um administrador pelo ID
    public int removerAdministrador(int id) {
        Conexao conexao = new Conexao();
        conexao.conectar(); // Conecta ao banco de dados
        Connection conn = conexao.getConn();

        try {
            // Verifica se existe um administrador com o ID fornecido
            ResultSet resultSet = buscarAdministradorPeloID(id);
            if (!resultSet.next()) {
                return 0; // Retorna 0 se não encontrar o administrador
            }

            // Executa a query
            String remover = "DELETE FROM administrador WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(remover);
            pstmt.setInt(1, id);

            // Retorna 1 se a execução da query for bem-sucedida, caso contrário, retorna 0
            int rows = pstmt.executeUpdate();
            if (rows > 0){
                return 1;
            }
            return 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Retorna -1 se a execução da query for mal-sucedida
        } finally {
            conexao.desconectar(); // Desconecta do banco de dados
        }
    }

    // Método para buscar a senha de um administrador pelo email
    public String buscaSenhaPorEmail(String email) {
        ResultSet resultSet = buscarAdministradorPeloEmail(email);
        try {
            while (resultSet != null && resultSet.next()) {
                return resultSet.getString("senha"); // Retorna a senha
            }
        } catch (SQLException sqlException) {
            return null; // Retorna null em caso de erro
        }
        return null; // Retorna null se não encontrar a senha
    }

    // Método para alterar um administrador
    public int alterarAdministrador(int id, Administrador administrador) {
        Conexao conexao = new Conexao();
        conexao.conectar(); // Conecta ao banco de dados
        Connection conn = conexao.getConn();

        try {
            // Prepara a instrução de atualização
            PreparedStatement pstmt = conn.prepareStatement("UPDATE administrador SET nome = ?, email = ?, senha = ? WHERE id = ?");

            // Define os parâmetros
            pstmt.setString(1, administrador.getNome());
            pstmt.setString(2, administrador.getEmail());
            pstmt.setString(3, administrador.getSenha());
            pstmt.setInt(4, id);

            // Retorna 1 se a execução da query for bem-sucedida, caso contrário, retorna 0
            int rows = pstmt.executeUpdate();
            if (rows > 0){
                return 1;
            }
            return 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Retorna -1 se a execução da query for mal-sucedida
        } finally {
            conexao.desconectar(); // Desconecta do banco de dados
        }
    }
}
