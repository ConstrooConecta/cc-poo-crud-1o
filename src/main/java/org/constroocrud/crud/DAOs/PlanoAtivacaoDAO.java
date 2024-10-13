package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;

import java.sql.*;

public class PlanoAtivacaoDAO {

    //SELECT

    //Metodo buscar PLano_Ativacao retorna um resultSet com TODOS os planos_ativacao no BD
    public ResultSet buscarPlanoAtivacao() {
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        PreparedStatement pstmt;
        ResultSet rs = null;
        try {
            String query = "Select * from plano_usuario order by id";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;
        }finally {
            conexao.desconectar();
        }
    }


    //Metodo que retorna apenas um resultSet se existir um usuario com ID recebido no parametro ou null se ocorrer algum erro (o ID é PK portanto nao tera outro mais de um resultado)

    public ResultSet buscarPlanoAtivacaoPeloID(int id){
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        PreparedStatement pstmt;
        ResultSet rs;
        try {

            String query = "Select * from plano_usuario where id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            return rs;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;

        }finally {
            conexao.desconectar();
        }

    }

    //Update
    public int alterarAtivacao(int id){
        PreparedStatement pstmt = null;
        ResultSet rs = buscarPlanoAtivacaoPeloID(id);
        Conexao conexao = new Conexao();
        conexao.conectar();//abrindo conexão com o banco
        Connection conn = conexao.getConn();
        String regex = "^[AI]$";

        try {

            rs.next();
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
                pstmt.executeUpdate(); // Executa o update
                int rows = pstmt.executeUpdate();

                // Retorna 1 caso a execução da query seja bem sucedida e 0 caso não ache

                if (rows > 0) {
                    return 1;
                }
            } return 0;
        }catch (SQLException sqle){
            sqle.printStackTrace();

            // Retorna -1 caso a execução da query seja mal sucedida
            return -1;
        }finally {
            conexao.desconectar();
        }
    }



    //Delete

    //Este metodo remove um PlanoAtivação pelo seu ID

    public int removerPlanoAtivacao(int id){
        PreparedStatement pstmt;
        ResultSet resultSet = buscarPlanoAtivacaoPeloID(id);
        Conexao conexao = new Conexao();
        boolean possuiRegistros = true;
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            //Verifica se existe um comprador e vendedor nesse ID e atribui ao boolean possuiRegistros
            if (!resultSet.next()){
                return 0;
            }

            //executa a query
            String remover = "DELETE FROM plano_usuario WHERE id = ?";
            pstmt = conn.prepareStatement(remover);

            pstmt.setInt(1, id);
            pstmt.execute();
            // Retorna 1 caso a execução da query seja bem sucedida e 0 caso não ache

            pstmt.executeUpdate();

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                return 1;
            } else {
                return 0;
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();

            // Retorna -1 caso a execução da query seja mal sucedida
            return -1;
        }finally {
            conexao.desconectar();
        }
    }
}
