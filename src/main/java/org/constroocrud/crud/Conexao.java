package org.constroocrud.crud;

import java.sql.*;

import static java.lang.System.getenv;

public class Conexao {

    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    // Método que faz a conexão com o banco de dados
    public boolean conectar() {
        try {
            Class.forName("org.postgresql.Driver");

            String dbUrl = getenv("CC_URL");
            String dbUser = getenv("CC_USER");
            String dbPassword = getenv("CC_PASSWORD");

            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
            return false;
        }
    }

    public boolean desconectar() {
        boolean verificar = false;
        try {
            if (conn != null && !conn.isClosed()) {
                // Desconectando do DB
                conn.close();
                verificar = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return verificar;
    }
}

