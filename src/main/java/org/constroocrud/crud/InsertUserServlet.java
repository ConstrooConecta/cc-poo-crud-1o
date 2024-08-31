package org.constroocrud.crud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.conexao.Conexao;
import org.constroocrud.crud.entidades.CompradorVendedor;
import org.constroocrud.crud.entidades.Profissional;
import org.constroocrud.crud.entidades.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "InsertUserServlet", value = "/InserirUsuarioServlet")
public class InsertUserServlet extends HttpServlet {
    @Override

    //Por meio desse Servlet é possivel fazer a inserção de usuarios
    //O que precisa ser implementado?
    //1. Mensagem de erros e sucesso
    //2. Nao permitir a inserção de um usuario caso a opcao de profissional ou comprador Vendedor nao esteja marcada

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        Conexao conexao = new Conexao();

        //Recebe os valores que indicam se o tipo de usuario é profissional e/ou vendedor e comprador


        String profissionalCheck = req.getParameter("profissional");
        String compradorVendedorCheck = req.getParameter("compradorVendedor");

        //Recebe os valores necessarios para fazer o cadastro de um usuario/comprador/Vendedor/profissional

        String email = req.getParameter("email");
        out.println(email);
        String telefone = req.getParameter("telefone");
        out.println(telefone);
        String cpf = req.getParameter("cpf");
        out.println(cpf);
        String senha = req.getParameter("senha");
        out.println(senha);
        String nome = req.getParameter("nome");
        out.println(nome);
        String sobrenome = req.getParameter("sobrenome");
        out.println(sobrenome);
        String cep = req.getParameter("cep");
        out.println(cep);
        String rua = req.getParameter("rua");
        out.println(rua);
        String strNumero = req.getParameter("numero");

        //como o numero precisa ser um int e no metodo acima ele é um string, ent por isso é feita uma conversao
        int numero = Integer.parseInt(strNumero);

        String complemento = req.getParameter("complemento");

        String cidade = req.getParameter("cidade");

        String uf = req.getParameter("uf");

        String bairro = req.getParameter("bairro");

        //Para uma melhor experiencia decidi separar o nome e o sobrenome e juntar aqui no servlet (pois o atributo no banco de dados é "nome completo"

        String nomeCompleto = nome + " " + sobrenome;

        //Faço a instanciação do usuario com todas as informações dele

        Usuario usuario = new Usuario(nomeCompleto, email,senha, telefone, uf, cidade, bairro, rua, numero,complemento,cep);

        out.println(usuario);

        //Faço a inserção dele no banco de dados
        if(conexao.inserirUsuario(usuario)){
            out.println("usuario inserido");


        }else {

            //Importante resssaltar que se ja existir um usuario no mesmo email, telefone ou algum atributo que esteja fora da formataçao do script como o CPF que precisa ser "xxx.xxx.xxx-xx", a insercao nao sera bem sucedida
            out.println("usuario não inserido");
        }

        //Como nao sabemos o ID que foi colocado no banco de dados (e precisamos dele para colocar no FK do comprador Vendedor ou do profissional) procuramos um result set com essa informação por meio do metodo de procurar pelo email


        Usuario usuarioInserido = conexao.buscarUsuarioPeloEmail(email);

        //FK do compradorVendedor ou profissional
        int idUsuario = usuarioInserido.getId();
        out.println(idUsuario);




        //Verifica se a opção de profissional esta marcada
        if (profissionalCheck != null && profissionalCheck.equals("checked")) {//se ele for um profissional
            out.println("\nUsuário é profissional");

            //É instanciado o profissional que precisa do cpf e sua fk q é o id do usuario
            Profissional profissional = new Profissional(cpf, idUsuario);
            out.println(profissional);
            if (conexao.inserirProfissional(profissional)){
                out.println("profissional inserido");
            }else{

                out.println("Profissional não inserido");
            }
            ;
        } else {
            out.println("\nUsuário não é profissional");
        }

        //Verifica se a opção de Comprador/Vendedor esta marcada

        if ( compradorVendedorCheck != null && compradorVendedorCheck.equals("checked")) {//se ele for um Comprador Vendedor
            CompradorVendedor compradorVendedor = new CompradorVendedor(cpf, idUsuario);
            out.println(compradorVendedor);
            //É instanciado o comprador/Vendedor que precisa do cpf e sua fk q é o id do usuario
            if (conexao.inserirCompradorVendedor(compradorVendedor)){
                out.println("comprador inserido");
            }else{
                out.println("comprador não inserido");
            }

            out.println("\nUsuário é Comprador/Vendedor");
        } else {
            out.println("\nUsuário não é Comprador/Vendedor");
        }

    }

}
