package org.constroocrud.crud.daos;

import org.constroocrud.crud.conexao.Conexao;
import org.constroocrud.crud.entidades.CategoriaProduto;
import org.constroocrud.crud.entidades.Usuario;

import java.sql.*;

public class CategoriaProdutoDAO {
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


    public boolean inserirCategoriaProduto(CategoriaProduto categoriaProduto){
        conectar();
        try {

            //faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO usuario(name,) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            //Coloca como parametro cada atributo do objeto Usuario por meio dos getters


            pstmt.execute();

            // Retorna True caso a execução da query seja bem sucedida

            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            // Retorna False caso a execução da query seja mal sucedida

            return false;
        }


    }
}
