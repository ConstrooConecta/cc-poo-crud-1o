package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.entidades.Administrador;

import java.sql.*;

public class AdministradorDAO {
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


    public boolean inserirAdministrador(Administrador administrador){
        conectar();
        try {

            //faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO administrador(nome,email,senha) VALUES ( ?,?,?)");

            //Coloca como parametro cada atributo do objeto CategoriaProduto por meio dos getters
            pstmt.setString(1, administrador.getNome());
            pstmt.setString(2, administrador.getEmail());
            pstmt.setString(3, administrador.getSenha());


            pstmt.execute();

            // Retorna True caso a execução da query seja bem sucedida

            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            // Retorna False caso a execução da query seja mal sucedida

            return false;
        }


    }


    public ResultSet buscarAdministrador(){
        try {
            conectar();
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
        try {
            conectar();
            String query = "Select * from administrador where ? = id";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }


    }

    public ResultSet buscarAdministradorPeloEmail(String email){
        try {
            conectar();
            String query = "Select * from administrador where ? = email";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,email);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }


    }
    public boolean removerAdministrador(int id){


        boolean possuiRegistros = true;

        try {
            conectar();

            //Verifica se existe um comprador e vendedor nesse ID e atribui ao boolean possuiRegistros
            ResultSet resultSet = buscarAdministradorPeloID(id);
            if (!resultSet.next()){

                possuiRegistros = false;
            }else {

                possuiRegistros = true;
            }

            //executa a query
            String remover = "DELETE FROM administrador WHERE id = ?";
            pstmt = conn.prepareStatement(remover);

            pstmt.setInt(1, id);
            pstmt.execute();



            return possuiRegistros;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
    }
    public String buscaSenhaPorEmail(String email){

        AdministradorDAO administradorDAO = new AdministradorDAO();
        ResultSet resultSet = administradorDAO.buscarAdministradorPeloEmail(email);
        try {
            while (resultSet.next()){
                return resultSet.getString("senha");

            }
        }catch (SQLException sqlException){
            return null;

        }
        return null;
    }

    public boolean alterarAdministrador(int id, Administrador administrador) {
        conectar();
        try {
            // Prepare a single statement with placeholders for all columns
            pstmt = conn.prepareStatement("UPDATE administrador SET nome = ?, email = ?, senha = ? WHERE id = ?");

            // Set parameters without quotation marks
            pstmt.setString(1, administrador.getNome());
            pstmt.setString(2, administrador.getEmail());
            pstmt.setString(3, administrador.getSenha());
            pstmt.setInt(4, id);

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
