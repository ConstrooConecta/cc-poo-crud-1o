package org.constroocrud.crud.conexao;

import org.constroocrud.crud.entidades.CompradorVendedor;
import org.constroocrud.crud.entidades.Profissional;
import org.constroocrud.crud.entidades.Usuario;

import java.sql.*;

public class Conexao {
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

            //IMPORTANTE INSIRA A URL, USER E PASSWORD NESSA PARTE
            conn = DriverManager.getConnection(
                    "","",""
            );
            return true;




        }catch (SQLException sqlException ){
            sqlException.printStackTrace();
            return false;
        }catch(ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
            return false;
        }



    }

    //INSERÇÃO DE INDENTIDADES


    //Metodo que recebe como parametro um usuario ja instanciado e insere ele no banco de dados

    public boolean inserirUsuario(Usuario usuario){


        conectar();
        try {

            //faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO usuario(nome_completo, email, senha, telefone_celular, uf, cidade, bairro, rua, numero, complemento, cep) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            //Coloca como parametro cada atributo do objeto Usuario por meio dos getters
            pstmt.setString(1, usuario.getNomeCompleto());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setString(4,usuario.getTelefoneCelular());
            pstmt.setString(5,usuario.getUF());
            pstmt.setString(6,usuario.getCidade());
            pstmt.setString(7, usuario.getBairro());
            pstmt.setString(8, usuario.getRua());
            pstmt.setInt(9, usuario.getNumero());
            pstmt.setString(10, usuario.getComplemento());
            pstmt.setString(11, usuario.getCEP());

            pstmt.execute();

            // Retorna True caso a execução da query seja bem sucedida

            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            // Retorna False caso a execução da query seja mal sucedida

            return false;
        }


    }

    //Metodo que recebe como parametro um objeto compradorVendedor e faz sua inserçao no banco de Dados
    public boolean inserirCompradorVendedor(CompradorVendedor compradorVendedor){

        conectar();
        try {

            //faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO comprador_vendedor( cpf, id_usuario) VALUES ( ?, ?)");

            //Coloca como parametro cada atributo do objeto compradorVendedor por meio dos getters
            pstmt.setString(1, compradorVendedor.getCPF());
            pstmt.setInt(2, compradorVendedor.getIDUsuario());

            pstmt.execute();

            //Caso a execucao seja bem sucedida retorna true

            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();

            //Caso a execucao seja mal sucedida retorna false
            return false;
        }


    }

    //Metodo que recebe como parametro um objeto Profissional e faz sua inserçao no banco de Dados
    public boolean inserirProfissional(Profissional profissional){
        conectar();
        try {
            //faz o comando SQL
            pstmt = conn.prepareStatement("INSERT INTO profissional( cpf, id_usuario) VALUES ( ?, ?)");

            //Coloca como parametro cada atributo do objeto compradorVendedor por meio dos getters
            pstmt.setString(1, profissional.getCPF());
            pstmt.setInt(2, profissional.getIDUsuario());

            //Caso a execucao seja bem sucedida retorna true
            pstmt.execute();

            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            //Caso a execucao seja mal sucedida retorna false

            return false;
        }
    }

    //SELECT

    //Metodo buscar Usuarios retorna um resultSet com TODOS os usuários no BD
    public ResultSet buscarUsuarios(){
        try {
            conectar();
            String query = "Select * from usuario";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }


    }

    //Metodo que retorna apenas um objeto usuario que tiver o mesmo email recebido no parametro com todos os seus outros atributos (o email é unique portanto nao tera outro mais de um resultado)
    //Este metodo é utilizado no forms HTML que fara a inserção de um usuario e compradorVendedor e/ou Profissional, para conseguir o ID do registro do Usuario que tem o email inserido e colocar como FK no CompradorVendedor ou/e no Profissional
    public Usuario buscarUsuarioPeloEmail(String email){
        try {
            conectar();
            String query = "Select * from usuario where email = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            rs.next();
            Usuario usuario = new Usuario(rs.getInt("id"),rs.getString("nome_completo"),rs.getString("email"),rs.getString("senha"),rs.getString("telefone_celular"),rs.getString("UF"), rs.getString("cidade"),rs.getString("bairro"), rs.getString("rua"), rs.getInt("numero"), rs.getString("complemento"),rs.getString("cep"));
            return usuario;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;

        }


    }
    //Metodo que retorna apenas um resultSet se existir um usuario com ID recebido no parametro ou null se ocorrer algum erro (o ID é PK portanto nao tera outro mais de um resultado)
    //Ele é utilizado no remover usuario para verificar se existe um usuario ou nao naquele id


    public ResultSet buscarUsuarioPeloID(int id){
        try {
            conectar();
            String query = "Select * from usuario where id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;

        }


    }

    //Metodo buscar Profissionais e retorna um resultSet com TODOS os profissionais no BD

    public ResultSet buscarProfissionais(){
        try {
            conectar();
            String query = "SELECT usuario.id \"Usuario_id\", usuario.nome_completo, usuario.email, usuario.senha, usuario.telefone_celular, usuario.uf, usuario.cidade, usuario.bairro, usuario.rua, usuario.numero, usuario.complemento, usuario.cep,  profissional.id \"profissional_id\", cpf FROM profissional join usuario on usuario.id = profissional.id_usuario;\n";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }


    }
    //Metodo que retorna apenas um resultSet se existir um profissional com ID recebido no parametro ou null se ocorrer algum erro (o ID é PK portanto nao tera outro mais de um resultado)
    //Ele é utilizado no remover profissional para verificar se existe um usuario ou nao naquele id
    public ResultSet buscarProfissionalPeloID(int id){


        try {
            conectar();
            String query = "SELECT usuario.id \"Usuario_id\", usuario.nome_completo, usuario.email, usuario.senha, usuario.telefone_celular, usuario.uf, usuario.cidade, usuario.bairro, usuario.rua, usuario.numero, usuario.complemento, usuario.cep,  profissional.id \"profissional_id\", cpf\n" +
                    "\tFROM profissional join usuario on usuario.id = profissional.id_usuario where profissional.id = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;

        }


    }

    //Metodo buscar Compradores e vendedores e retorna um resultSet com TODOS os profissionais no BD
    public ResultSet buscarCompradorVendedor(){
        try {
            conectar();
            String query = "SELECT usuario.id \"Usuario_id\", usuario.nome_completo, usuario.email, usuario.senha, usuario.telefone_celular, usuario.uf, usuario.cidade, usuario.bairro, usuario.rua, usuario.numero, usuario.complemento, usuario.cep, comprador_vendedor.id \"CompradorVendedor_id\", cpf FROM comprador_vendedor join usuario on usuario.id = comprador_vendedor.id_usuario;";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return rs;

        }


    }
    //Metodo que retorna apenas um resultSet se existir um  com ID recebido no parametro ou null se ocorrer algum erro (o ID é PK portanto nao tera outro mais de um resultado)
    //Ele é utilizado no remover profissional para verificar se existe um usuario ou nao naquele id
    public ResultSet buscarCompradorVendedorPeloID(int id){
        try {
            conectar();
            String query = "SELECT usuario.id \"Usuario_id\", usuario.nome_completo, usuario.email, usuario.senha, usuario.telefone_celular, usuario.uf, usuario.cidade, usuario.bairro, usuario.rua, usuario.numero, usuario.complemento,usuario.cep,  comprador_vendedor.id \"CompradorVendedor_id\", cpf FROM comprador_vendedor join usuario on usuario.id = comprador_vendedor.id_usuario where comprador_vendedor.id = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            return rs;


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;

        }


    }



    //Delete
    //Este metodo remove um compradorVendedor pelo seu ID

    public boolean removerCompradorVendedor(int id){


        boolean possuiRegistros = true;

        try {
            conectar();

            //Verifica se existe um comprador e vendedor nesse ID e atribui ao boolean possuiRegistros
            ResultSet resultSet = buscarCompradorVendedorPeloID(id);
            if (!resultSet.next()){

                possuiRegistros = false;
            }else {

                possuiRegistros = true;
            }

            //executa a query
            String remover = "DELETE FROM comprador_vendedor WHERE id = ?";
            pstmt = conn.prepareStatement(remover);

            pstmt.setInt(1, id);
            pstmt.execute();



            return possuiRegistros;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
    }

    //Este metodo remove um Profissional pelo seu ID
    public boolean removerProfissional(int id){
        boolean possuiRegistros = true;

        try {
            conectar();
            ResultSet resultSet = buscarProfissionalPeloID(id);

            //Verifica se existe um profissional nesse ID e atribui ao boolean possuiRegistros
            if (!resultSet.next()){
                possuiRegistros = false;
            }else {

                possuiRegistros = true;
            }
            String remover = "DELETE FROM profissional WHERE id = ?";
            pstmt = conn.prepareStatement(remover);

            pstmt.setInt(1, id);
            pstmt.execute();




            return possuiRegistros;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
    }
    //Este metodo remove um Usuario pelo seu ID

    public boolean removerUsuario(int id){
        boolean possuiRegistros = true;

        try {
            conectar();
            //Verifica se existe um usuario nesse ID e atribui ao boolean possuiRegistros
            ResultSet resultSet = buscarUsuarioPeloID(id);
            if (!resultSet.next()){
                possuiRegistros = false;
            }else {

                possuiRegistros = true;
            }
            String remover = "DELETE FROM usuario WHERE id = ?";
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
