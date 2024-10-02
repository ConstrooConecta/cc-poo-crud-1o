package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.entidades.CategoriaProduto;
import org.constroocrud.crud.entidades.TagServico;

import java.sql.*;

public class TagServicoDAO {
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


    //Metodo que faz a conexao com o banco de dados


    public boolean conectar(){
        try{
            Class.forName("org.postgresql.Driver");

            String dbUrl = System.getenv("CC_URL");
            String dbUser = System.getenv("CC_USER");
            String dbPassword = System.getenv("CC_PASSWORD");

            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            return true;

        }catch (SQLException sqlException ){
            sqlException.printStackTrace();
            return false;
        }catch(ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
            return false;
        }



    }
    public boolean inserirTagServico(TagServico tagServico){
        conectar();
        try {

            //faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO tag_servico (nome) VALUES ( ?)");

            //Coloca como parametro cada atributo do objeto CategoriaProduto por meio dos getters
            pstmt.setString(1, tagServico.getNome());


            pstmt.execute();

            // Retorna True caso a execução da query seja bem sucedida

            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            // Retorna False caso a execução da query seja mal sucedida

            return false;
        }


    }

    public ResultSet buscarTagServico(){
        try {
            conectar();
            String query = "Select * from tag_servico";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }


    }

    public ResultSet buscarTagServicoPeloID(int id){
        try {
            conectar();
            String query = "Select * from tag_servico where ? = id";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }


    }


    public boolean removerTagServicoPeloID(int id){


        boolean possuiRegistros = true;

        try {
            conectar();

            //Verifica se existe um comprador e vendedor nesse ID e atribui ao boolean possuiRegistros
            ResultSet resultSet = buscarTagServicoPeloID(id);
            if (!resultSet.next()){

                possuiRegistros = false;
            }else {

                possuiRegistros = true;
            }

            //executa a query
            String remover = "DELETE FROM tag_servico WHERE id = ?";
            pstmt = conn.prepareStatement(remover);

            pstmt.setInt(1, id);
            pstmt.execute();



            return possuiRegistros;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
    }
}
