package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.Conexao;
import org.constroocrud.crud.models.Administrador;

import java.sql.*;

public class AdministradorDAO {

    //Metodo que faz a conexao com o banco de dados

    public int inserirAdministrador(Administrador administrador){
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            //faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO administrador(nome,email,senha) VALUES ( ?,?,?)");

            //Coloca como parametro cada atributo do objeto CategoriaProduto por meio dos getters
            pstmt.setString(1, administrador.getNome());
            pstmt.setString(2, administrador.getEmail());
            pstmt.setString(3, administrador.getSenha());


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
        }


    }


    public ResultSet buscarAdministrador(){
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            String query = "Select * from administrador";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }


    }

    public ResultSet buscarAdministradorPeloID(int id){
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            String query = "Select * from administrador where ? = id";

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

    public ResultSet buscarAdministradorPeloEmail(String email){
        PreparedStatement pstmt;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            String query = "Select * from administrador where ? = email";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,email);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }finally {
            conexao.desconectar();
        }


    }
    public int removerAdministrador(int id){
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {

            //Verifica se existe um comprador e vendedor nesse ID e atribui ao boolean possuiRegistros
            ResultSet resultSet = buscarAdministradorPeloID(id);
            if (!resultSet.next()){
                return 0;
            }

            //executa a query
            String remover = "DELETE FROM administrador WHERE id = ?";
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
    public String buscaSenhaPorEmail(String email){

        AdministradorDAO administradorDAO = new AdministradorDAO();
        ResultSet resultSet = administradorDAO.buscarAdministradorPeloEmail(email);
        try {
            while (resultSet.next()){
                return resultSet.getString("senha");

            }
        }catch (SQLException sqlException) {
            return null;

        }
        return null;
    }

    public int alterarAdministrador(int id, Administrador administrador) {
        PreparedStatement pstmt;
        Conexao conexao = new Conexao();
        conexao.conectar();
        Connection conn = conexao.getConn();
        try {
            // Prepare a single statement with placeholders for all columns
            pstmt = conn.prepareStatement("UPDATE administrador SET nome = ?, email = ?, senha = ? WHERE id = ?");

            // Set parameters without quotation marks
            pstmt.setString(1, administrador.getNome());
            pstmt.setString(2, administrador.getEmail());
            pstmt.setString(3, administrador.getSenha());
            pstmt.setInt(4, id);

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
        }
    }
}
