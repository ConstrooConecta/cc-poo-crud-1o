package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;

import java.sql.*;

public class PlanoAtivacaoDAO {

    // Método buscar Plano_Ativacao que retorna um ResultSet com TODOS os planos_ativacao no BD
    public ResultSet buscarPlanoAtivacao() {
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        PreparedStatement pstmt;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM plano_ativacao ORDER BY id";
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

    // Método que retorna apenas um ResultSet se existir um usuário com ID recebido no parâmetro, ou null se ocorrer algum erro (o ID é PK, portanto não terá mais de um resultado)
    public ResultSet buscarPlanoAtivacaoPeloID(int id) {
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        PreparedStatement pstmt;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM plano_ativacao WHERE id = ?";
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


    // UPDATE

    // Método para alterar o status de ativação de um plano de acordo com o ID
    public int alterarAtivacao(int id) {
        ResultSet rs = buscarPlanoAtivacaoPeloID(id);
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        PreparedStatement pstmt = null;
        String regex = "^[AI]$";
        try {
            if (rs != null && rs.next()) {
                String ativacaoStr = rs.getString("ativacao");
                if (!ativacaoStr.matches(regex)) {
                    return 0; // Retorna 0 se a ativação não é válida
                }

                char ativacao = ativacaoStr.charAt(0); // Pega o primeiro caractere
                if (ativacao == 'A') {
                    pstmt = conn.prepareStatement("UPDATE plano_ativacao SET ativacao = 'I' WHERE id = ?");
                } else if (ativacao == 'I') {
                    pstmt = conn.prepareStatement("UPDATE plano_ativacao SET ativacao = 'A' WHERE id = ?");
                }

                pstmt.setInt(1, id); // Adiciona o ID no PreparedStatement

                int rows = pstmt.executeUpdate(); // Executa o update
                // Retorna 1 se a remoção for bem-sucedida, 0 caso contrário
                if (rows>0){
                    return 1;
                }
                return 0;
            }
            return 0; // Retorna 0 se nada foi alterado
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return -1; // Retorna -1 em caso de erro
        } finally {
            conexao.desconectar();
        }
    }

    // DELETE

    // Método que remove um PlanoAtivação pelo seu ID
    public int removerPlanoAtivacao(int id) {
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        PreparedStatement pstmt;
        ResultSet resultSet = buscarPlanoAtivacaoPeloID(id);
        try {
            // Verifica se existe um plano de ativação nesse ID
            if (resultSet == null || !resultSet.next()) {
                return 0; // Retorna 0 se o plano não foi encontrado
            }

            // Executa a query de remoção
            String remover = "DELETE FROM plano_ativacao WHERE id = ?";
            pstmt = conn.prepareStatement(remover);
            pstmt.setInt(1, id);

            // Retorna 1 se a remoção for bem-sucedida, 0 caso contrário
            int rows = pstmt.executeUpdate(); // Executa o delete
            if (rows>0){
                return 1;
            }
            return 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1; // Retorna -1 em caso de erro
        } finally {
            conexao.desconectar();
        }
    }
}
