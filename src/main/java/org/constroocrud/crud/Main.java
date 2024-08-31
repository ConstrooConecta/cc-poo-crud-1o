package org.constroocrud.crud;


import org.constroocrud.crud.conexao.Conexao;
import org.constroocrud.crud.entidades.CompradorVendedor;
import org.constroocrud.crud.entidades.Profissional;
import org.constroocrud.crud.entidades.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        //public Usuario(String nomeCompleto, String email, String senha, String telefoneCelular, String UF, String cidade, String bairro, String rua, int numero, String complemento) {
//        Usuario usuario = new Usuario("Theo", "theo@gmail.com", "Theo1000a",  "(11) 99257-4545","SP", "Cotia", "Sítio", "Rua Luthero", 10, "Não tem", "02535-140");
        Conexao conexao = new Conexao();
//        conexao.inserirUsuario(usuario);
//        if (conexao.inserirUsuario(usuario)){
//            System.out.println("Profissional inserido");
//        }
//        CompradorVendedor compradorVendedor = new CompradorVendedor("634.066.999-07",26)
//
        ;
//        Profissional profissional = new Profissional("602.066.628-07", 11 );
//        conexao.inserirProfssional(profissional);
//        if(conexao.inserirCompradorVendedor(compradorVendedor)){
//            System.out.println("Inseriu");
//        }
//        System.out.println(conexao.removerCompradorVendedor(9));


//        if (conexao.inserirCompradorVendedor(compradorVendedor)){
//            System.out.println("Profissional inserido");
//        }
        if ( conexao.removerUsuario(26)){
            System.out.println("Usuario removido");

        }else {
            System.out.println("ERRO");
        }


//        try {
//            ResultSet resultSet = conexao.buscarProfissionais();
//            while (resultSet.next()){
//                System.out.println(resultSet.getString("CPF"));
//            }
//        }catch (SQLException sqlException){
//            sqlException.printStackTrace();
//
//        }


//
//
//        System.out.println(conexao.conectar());

//        System.out.println(conexao.buscarUsuarioPeloEmail("theo@gmail.com"));

//        Usuario usuario1 = new Usuario("Matheus Oshiro", "matheus.ueno@germinare.org","Mat20090212", "(11) 95395-1202", "SP","São Paulo", "Casa Verde", "Rua Brigadeiro Arthur Carlos Peralta", 91,"Não tem","02535-140");
//        if(conexao.inserirUsuario(usuario1)){
//            System.out.println("usuario inserido");
//        }else {
//            System.out.println("usuario não inserido");
//        }
    }





}
