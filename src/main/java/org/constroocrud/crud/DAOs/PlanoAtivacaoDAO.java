package org.constroocrud.crud.DAOs;

import java.sql.*;

public class PlanoAtivacaoDAO {
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


    public boolean conectar() {
        try {
            Class.forName("org.postgresql.Driver");

            String dbUrl = System.getenv("CC_URL");
            String dbUser = System.getenv("CC_USER");
            String dbPassword = System.getenv("CC_PASSWORD");

            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            return true;

        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
            return false;
        }
    }

    public boolean desconectar(){
        boolean verificar = false;
        try {
            if (conn != null && !conn.isClosed()) {
                //Desconectando do DB
                conn.close();
                verificar = true;
            }
        }catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        return verificar;
    }

    //SELECT

    //Metodo buscar PLano_Ativacao retorna um resultSet com TODOS os planos_ativacao no BD
    public ResultSet buscarPlanoAtivacao(){
        try {
            conectar();
            String query = "Select * from plano_usuario order by id";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;
        }finally {
            desconectar();
        }
    }


    //Metodo que retorna apenas um resultSet se existir um usuario com ID recebido no parametro ou null se ocorrer algum erro (o ID é PK portanto nao tera outro mais de um resultado)

    public ResultSet buscarPlanoAtivacaoPeloID(int id){
        try {
            conectar();
            String query = "Select * from plano_usuario where id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            return rs;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;

        }finally {
            desconectar();
        }

    }

    //Update
    public int alterarAtivacao(int id){

        try {
            rs = buscarPlanoAtivacaoPeloID(id);

            conectar();//abrindo conexão com o banco
            rs.next();
            String ativacaoStr = rs.getString("ativacao");

            if (ativacaoStr != null && !ativacaoStr.isEmpty()) {
                char ativacao = ativacaoStr.charAt(0); // Pega o primeiro caractere

                if (ativacao == 'A') { // Verifica se é igual a 'A'
                    pstmt = conn.prepareStatement("UPDATE PLANO_USUARIO SET ATIVACAO = 'I' WHERE ID = ?");
                } else if (ativacao == 'I') { // Verifica se é igual a 'I'
                    pstmt = conn.prepareStatement("UPDATE PLANO_USUARIO SET ATIVACAO = 'A' WHERE ID = ?");
                }

                pstmt.setInt(1, id); // Adiciona o ID no PreparedStatement
                pstmt.executeUpdate(); // Executa o update
                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    return 1;
                }
            } return 0;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            desconectar();
        }
    }

    public int alterarAtivacao2(int id) {
        try {
            conectar(); // abrir a conexão

            try (ResultSet rs = buscarPlanoAtivacaoPeloID(id)) { // buscar o plano de ativação pelo ID
                if (rs.next()) {
                    String ativacaoStr = rs.getString("ativacao");

                    if (ativacaoStr != null && !ativacaoStr.isEmpty()) {
                        char ativacao = ativacaoStr.charAt(0); // Pega o primeiro caractere
                        String sql;

                        if (ativacao == 'A') {
                            sql = "UPDATE PLANO_USUARIO SET ATIVACAO = 'I' WHERE ID = ?";
                        } else if (ativacao == 'I') {
                            sql = "UPDATE PLANO_USUARIO SET ATIVACAO = 'A' WHERE ID = ?";
                        } else {
                            return 0; // Caso não seja 'A' ou 'I'
                        }

                        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                            pstmt.setInt(1, id); // Adiciona o ID no PreparedStatement
                            int rows = pstmt.executeUpdate(); // Executa o update
                            return rows > 0 ? 1 : 0; // Retorna 1 se atualizou, 0 caso contrário
                        }
                    }
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return -1; // Retorna -1 em caso de erro
        } finally {
            desconectar(); // Fechar a conexão
        }
        return 0; // Retorna 0 se nada foi alterado
    }


    //Delete

    //Este metodo remove um PlanoAtivação pelo seu ID

    public boolean removerPlanoAtivacao(int id){

        boolean possuiRegistros = true;

        try {
            //Verifica se existe um comprador e vendedor nesse ID e atribui ao boolean possuiRegistros
            ResultSet resultSet = buscarPlanoAtivacaoPeloID(id);
            if (!resultSet.next()){
                possuiRegistros = false;
            }else {
                possuiRegistros = true;
            }

            conectar();

            //executa a query
            String remover = "DELETE FROM plano_usuario WHERE id = ?";
            pstmt = conn.prepareStatement(remover);

            pstmt.setInt(1, id);
            pstmt.execute();

            return possuiRegistros;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }finally {
            desconectar();
        }
    }
}
