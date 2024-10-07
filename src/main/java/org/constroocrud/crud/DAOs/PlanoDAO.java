package org.constroocrud.crud.DAOs;

import java.sql.*;

public class PlanoDAO {
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
    public ResultSet buscarPlanos() {
        try {
            conectar();
            String query = "Select * from plano";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return rs;

        }
    }

}
