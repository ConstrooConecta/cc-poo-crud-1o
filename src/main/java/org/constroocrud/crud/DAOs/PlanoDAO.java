package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.CategoriaProduto;
import org.constroocrud.crud.models.Plano;

import java.sql.*;

public class PlanoDAO {
    //Metodo que faz a conexao com o banco de dados



    public ResultSet buscarPlanos() {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            String query = "Select * from plano";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return rs;

        }finally {
            conexao.desconectar();
        }
    }

    public ResultSet buscarPlanoPeloID(int id){
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            String query = "Select * from plano where ? = id";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }finally {
            conexao.desconectar();
        }


    }
    public int removerPlanoPeloID(int id){
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            //Verifica se existe um comprador e vendedor nesse ID e atribui ao boolean possuiRegistros
            ResultSet resultSet =buscarPlanoPeloID(id);
            if (!resultSet.next()) {
                return 0;
            }

            //executa a query
            String remover = "DELETE FROM plano WHERE id = ?";
            pstmt = conn.prepareStatement(remover);

            pstmt.setInt(1, id);
            pstmt.execute();

            // Retorna 1 caso a execução da query seja bem sucedida e 0 caso não ache
            int rows = pstmt.executeUpdate();

            if (rows > 0){
                return 1;
            }else {
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

    public int inserirPlano(Plano plano){
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            //faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO plano (nome_plano, tempo_duracao, valor, descricao, tipo_plano) VALUES (?,?,?,?,?)");

            //Coloca como parametro cada atributo do objeto CategoriaProduto por meio dos getters
            pstmt.setString(1, plano.getNome());
            pstmt.setInt(2, plano.getDuracao());
            pstmt.setDouble(3, plano.getValor());
            pstmt.setString(4, plano.getDescricao());
            pstmt.setString(5, plano.getTipo_plano());

            pstmt.execute();

            // Retorna True caso a execução da query seja bem sucedida

            // Retorna 1 caso a execução da query seja bem sucedida e 0 caso não ache
            int rows = pstmt.executeUpdate();

            if (rows > 0){
                return 1;
            }else {
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
    public int alterarPlano(int id, Plano plano) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            // Prepare a single statement with placeholders for all columns
            pstmt = conn.prepareStatement("UPDATE plano SET nome_plano = ?, descricao = ?,tempo_duracao = ?, valor = ?, tipo_plano = ? WHERE id = ?");

            // Set parameters without quotation marks
            pstmt.setString(1, plano.getNome());
            pstmt.setString(2, plano.getDescricao());
            pstmt.setInt(3, plano.getDuracao());
            pstmt.setDouble(4, plano.getValor());
            pstmt.setString(5, plano.getTipo_plano());
            pstmt.setInt(6, id);

            pstmt.setInt(3, id);

            pstmt.execute();

            // Retorna 1 caso a execução da query seja bem sucedida e 0 caso não ache            int rows = pstmt.executeUpdate();
            int rows = pstmt.executeUpdate();

            if (rows > 0){
                return 1;
            }else {
                return 0;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();


            return -1;
        } finally {
            conexao.desconectar();
        }
    }

}
