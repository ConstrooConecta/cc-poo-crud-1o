package org.constroocrud.crud.DAOs;

import java.sql.*;

public class PlanoAtivacaoDAO {
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

    public boolean desconectar() {
        boolean verificar = false;
        try {
            if (conn != null && !conn.isClosed()) {
                // Desconectando do DB
                conn.close();
                verificar = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return verificar;
    }

    // SELECT

    // Método buscar PLano_Ativacao retorna um ResultSet com TODOS os planos_ativacao no BD
    public ResultSet buscarPlanoAtivacao() {
        try {
            conectar();
            String query = "SELECT * FROM plano_usuario ORDER BY id";
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

    // Método que retorna apenas um ResultSet se existir um usuário com ID recebido no parâmetro ou null se ocorrer algum erro (o ID é PK, portanto não terá mais de um resultado)
    public ResultSet buscarPlanoAtivacaoPeloID(int id) {
        try {
            conectar();
            String query = "SELECT * FROM plano_usuario WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            return rs;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;

        } finally {
            desconectar();
        }
    }

    // Update
    public int alterarAtivacao(int id) {
        String regex = "^[AI]$";
        try {
            rs = buscarPlanoAtivacaoPeloID(id);

            conectar(); // abrindo conexão com o banco
            if (rs.next()) {
                String ativacaoStr = rs.getString("ativacao");
                if (!ativacaoStr.matches(regex)) {
                    return 0;
                }
                if (ativacaoStr != null && !ativacaoStr.isEmpty()) {
                    char ativacao = ativacaoStr.charAt(0); // Pega o primeiro caractere

                    if (ativacao == 'A') { // Verifica se é igual a 'A'
                        pstmt = conn.prepareStatement("UPDATE PLANO_USUARIO SET ATIVACAO = 'I' WHERE ID = ?");
                    } else if (ativacao == 'I') { // Verifica se é igual a 'I'
                        pstmt = conn.prepareStatement("UPDATE PLANO_USUARIO SET ATIVACAO = 'A' WHERE ID = ?");
                    }

                    pstmt.setInt(1, id); // Adiciona o ID no PreparedStatement
                    int rows = pstmt.executeUpdate(); // Executa o update
                    return rows > 0 ? 1 : 0; // Retorna 1 se atualizou, 0 caso contrário
                }
            }
            return 0; // Retorna 0 se nada foi alterado
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return -1; // Retorna -1 em caso de erro
        } finally {
            desconectar();
        }
    }

    // Delete

    // Este método remove um PlanoAtivação pelo seu ID
    public boolean removerPlanoAtivacao(int id) {
        boolean possuiRegistros = true;

        try {
            // Verifica se existe um plano de ativação nesse ID
            ResultSet resultSet = buscarPlanoAtivacaoPeloID(id);
            if (!resultSet.next()) {
                possuiRegistros = false;
            }

            conectar();

            // Executa a query
            String remover = "DELETE FROM plano_usuario WHERE id = ?";
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
}
