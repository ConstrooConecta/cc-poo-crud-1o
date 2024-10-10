package org.constroocrud.crud.DAOs;

import org.constroocrud.crud.models.CategoriaProduto;
import org.constroocrud.crud.models.Plano;

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

    public ResultSet buscarPlanoPeloID(int id){
        try {
            conectar();
            String query = "Select * from plano where ? = id";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }


    }
    public boolean removerPlanoPeloID(int id){


        boolean possuiRegistros = true;

        try {
            conectar();

            //Verifica se existe um comprador e vendedor nesse ID e atribui ao boolean possuiRegistros
            ResultSet resultSet =buscarPlanoPeloID(id);
            if (!resultSet.next()){

                possuiRegistros = false;
            }else {

                possuiRegistros = true;
            }

            //executa a query
            String remover = "DELETE FROM plano WHERE id = ?";
            pstmt = conn.prepareStatement(remover);

            pstmt.setInt(1, id);
            pstmt.execute();



            return possuiRegistros;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
    }

    public boolean inserirPlano(Plano plano){
        conectar();
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

            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            // Retorna False caso a execução da query seja mal sucedida

            return false;
        }


    }
    public boolean alterarPlano(int id, Plano plano) {
        conectar();
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

            // No need for separate statements for each column update
            // ...

            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

}
