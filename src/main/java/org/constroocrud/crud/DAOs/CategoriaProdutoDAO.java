package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.CategoriaProduto;

import java.sql.*;

public class CategoriaProdutoDAO {

    public int inserirCategoriaProduto(CategoriaProduto categoriaProduto){
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            //faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO categoria_produto(nome, descricao) VALUES (?, ?)");

            //Coloca como parametro cada atributo do objeto CategoriaProduto por meio dos getters
            pstmt.setString(1, categoriaProduto.getNome());
            pstmt.setString(2, categoriaProduto.getDescricao());

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

    public ResultSet buscarCategoriaProduto(){
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            String query = "Select * from categoria_produto";
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

    public ResultSet buscarCategoriaProdutoPeloID(int id) {
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            String query = "Select * from categoria_produto where ? = id";

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

    public ResultSet buscarCategoriaProdutoPeloNome(String nome){
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            String query = "Select * from categoria_produto where ? = nome";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nome);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }finally {
            conexao.desconectar();
        }


    }


    public int removerCategoriaProduto(int id){
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            //Verifica se existe um comprador e vendedor nesse ID e atribui ao boolean possuiRegistros
            ResultSet resultSet = buscarCategoriaProdutoPeloID(id);
            if (!resultSet.next()){
                return 0;
            }

            //executa a query
            String remover = "DELETE FROM categoria_produto WHERE id = ?";
            pstmt = conn.prepareStatement(remover);

            pstmt.setInt(1, id);

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

    public int alterarCategoriaProduto(int id, CategoriaProduto categoriaProduto) {
        PreparedStatement pstmt = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            // Prepare a single statement with placeholders for all columns
            pstmt = conn.prepareStatement("UPDATE categoria_produto SET nome = ?, descricao = ? WHERE id = ?");

            // Set parameters without quotation marks
            pstmt.setString(1, categoriaProduto.getNome());
            pstmt.setString(2, categoriaProduto.getDescricao());

            pstmt.setInt(3, id);

            // Retorna 1 caso a execução da query seja bem sucedida e 0 caso não ache
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
        } finally {
            conexao.desconectar();
        }
    }
}
