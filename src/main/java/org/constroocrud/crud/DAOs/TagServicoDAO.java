package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.TagServico;

import java.sql.*;

public class TagServicoDAO {

    public int inserirTagServico(TagServico tagServico){
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            //faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO tag_servico (nome, descricao) VALUES (?, ?)");

            //Coloca como parametro cada atributo do objeto CategoriaProduto por meio dos getters
            pstmt.setString(1, tagServico.getNome());
            pstmt.setString(2, tagServico.getDescricao());


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

    public ResultSet buscarTagServico(){
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            String query = "Select * from tag_servico";
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

    public ResultSet buscarTagServicoPeloID(int id){
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            String query = "Select * from tag_servico where ? = id";

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


    public int removerTagServicoPeloID(int id){
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            //Verifica se existe um comprador e vendedor nesse ID e atribui ao boolean possuiRegistros
            ResultSet resultSet = buscarTagServicoPeloID(id);
            if (!resultSet.next()){
                return 0;
            }

            //executa a query
            String remover = "DELETE FROM tag_servico WHERE id = ?";
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

    public int alterarTagServico(int id, TagServico tagServico) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try{
            // Prepare a single statement with placeholders for all columns
            pstmt = conn.prepareStatement("UPDATE tag_servico SET nome = ?, descricao = ? WHERE id = ?");

            // Set parameters without quotation marks
            pstmt.setString(1, tagServico.getNome());
            pstmt.setString(2, tagServico.getDescricao());

            pstmt.setInt(3, id);

            pstmt.execute();

            // Retorna 1 caso a execução da query seja bem sucedida e 0 caso não ache
            int rows = pstmt.executeUpdate();

            if (rows > 0){
                return 1;
            }else {
                return 0;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

            // Retorna -1 caso a execução da query seja mal sucedida
            return -1;
        }finally {
            conexao.desconectar();
        }
    }
}
