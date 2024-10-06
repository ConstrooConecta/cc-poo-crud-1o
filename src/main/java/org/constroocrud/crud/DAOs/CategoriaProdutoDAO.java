package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.entidades.Administrador;
import org.constroocrud.crud.entidades.CategoriaProduto;

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
            pstmt = conn.prepareStatement("INSERT INTO categoria_produto(nome, descricao) VALUES (?, ?)");

            //Coloca como parametro cada atributo do objeto CategoriaProduto por meio dos getters
            pstmt.setString(1, categoriaProduto.getNome());
            pstmt.setString(2, categoriaProduto.getDescricao());

            pstmt.execute();

            // Retorna True caso a execução da query seja bem sucedida

            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            // Retorna False caso a execução da query seja mal sucedida

            return false;
        }


    }

    public ResultSet buscarCategoriaProduto(){
        try {
            conectar();
            String query = "Select * from categoria_produto";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }


    }

    public ResultSet buscarCategoriaProdutoPeloID(int id){
        try {
            conectar();
            String query = "Select * from categoria_produto where ? = id";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }


    }


    public boolean removerCategoriaProduto(int id){


        boolean possuiRegistros = true;

        try {
            conectar();

            //Verifica se existe um comprador e vendedor nesse ID e atribui ao boolean possuiRegistros
            ResultSet resultSet = buscarCategoriaProdutoPeloID(id);
            if (!resultSet.next()){

                possuiRegistros = false;
            }else {

                possuiRegistros = true;
            }

            //executa a query
            String remover = "DELETE FROM categoria_produto WHERE id = ?";
            pstmt = conn.prepareStatement(remover);

            pstmt.setInt(1, id);
            pstmt.execute();



            return possuiRegistros;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
    }
    public boolean alterarCategoriaProduto(int id, CategoriaProduto categoriaProduto) {
        conectar();
        try {
            // Prepare a single statement with placeholders for all columns
            pstmt = conn.prepareStatement("UPDATE categoria_produto SET nome = ?, descricao = ? WHERE id = ?");

            // Set parameters without quotation marks
            pstmt.setString(1, categoriaProduto.getNome());
            pstmt.setString(2, categoriaProduto.getDescricao());

            pstmt.setInt(3, id);

            pstmt.execute();

            // No need for separate statements for each column update
            // ...

            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
}
