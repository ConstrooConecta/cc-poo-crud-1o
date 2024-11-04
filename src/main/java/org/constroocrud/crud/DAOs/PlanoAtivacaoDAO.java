package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;

import java.sql.*;

public class PlanoAtivacaoDAO {

    // Método buscar Plano_Ativacao que retorna um ResultSet com TODOS os planos_ativacao no BD
    public ResultSet buscarPlanoAtivacao() {
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        PreparedStatement pstmt;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM plano_ativacao ORDER BY id"; // Query para selecionar todos os planos de ativação
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

    // Método que retorna apenas um ResultSet se existir um usuário com ID recebido no parâmetro,
    // ou null se ocorrer algum erro (o ID é PK, portanto não terá mais de um resultado)
    public ResultSet buscarPlanoAtivacaoPeloID(int id) {
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        PreparedStatement pstmt;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM plano_ativacao WHERE id = ?"; // Query para selecionar um plano pelo ID
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

    // UPDATE

    // Método para alterar o status de ativação de um plano de acordo com o ID
    public int alterarAtivacao(int id) {
        ResultSet rs = buscarPlanoAtivacaoPeloID(id); // Busca o plano pelo ID
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        PreparedStatement pstmt = null; // Inicializa o PreparedStatement
        String regex = "^[AI]$"; // Regex para validar o status de ativação (A para ativo, I para inativo)
        try {
            if (rs != null && rs.next()) { // Verifica se o resultado não é nulo e se há um registro
                String ativacaoStr = rs.getString("ativacao"); // Obtém o status de ativação
                if (!ativacaoStr.matches(regex)) {
                    return 0; // Retorna 0 se a ativação não é válida
                }

                char ativacao = ativacaoStr.charAt(0); // Pega o primeiro caractere do status de ativação
                // Altera o status de ativação de A para I ou de I para A
                if (ativacao == 'A') {
                    pstmt = conn.prepareStatement("UPDATE plano_ativacao SET ativacao = 'I' WHERE id = ?");
                } else if (ativacao == 'I') {
                    pstmt = conn.prepareStatement("UPDATE plano_ativacao SET ativacao = 'A' WHERE id = ?");
                }

                pstmt.setInt(1, id); // Adiciona o ID no PreparedStatement
                int rows = pstmt.executeUpdate(); // Executa o update
                // Retorna 1 se a alteração for bem-sucedida, 0 caso contrário
                if (rows > 0) {
                    return 1;
                }
                return 0; // Retorna 0 se nenhuma linha foi alterada
            }
            return 0; // Retorna 0 se nada foi alterado
        } catch (SQLException sqle) {
            sqle.printStackTrace(); // Exibe qualquer erro SQL
            return -1; // Retorna -1 em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta a conexão no final
        }
    }

    // DELETE

    // Método que remove um PlanoAtivação pelo seu ID
    public int removerPlanoAtivacao(int id) {
        Conexao conexao = new Conexao(); // Cria uma nova conexão
        conexao.conectar(); // Estabelece a conexão com o banco de dados
        Connection conn = conexao.getConn(); // Obtém a conexão ativa
        PreparedStatement pstmt;
        try {
            // Executa a query de remoção
            String remover = "DELETE FROM plano_ativacao WHERE id = ?"; // Query para deletar um plano pelo ID
            pstmt = conn.prepareStatement(remover); // Prepara a instrução SQL
            pstmt.setInt(1, id); // Define o parâmetro ID na consulta

            // Retorna 1 se a remoção for bem-sucedida, 0 caso contrário
            int rows = pstmt.executeUpdate(); // Executa o delete
            if (rows > 0) {
                return 1; // Retorna 1 se a remoção foi bem-sucedida
            }
            return 0; // Retorna 0 se nenhuma linha foi removida
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Exibe qualquer erro SQL
            return -1; // Retorna -1 em caso de erro
        } finally {
            conexao.desconectar(); // Desconecta a conexão no final
        }
    }
}
