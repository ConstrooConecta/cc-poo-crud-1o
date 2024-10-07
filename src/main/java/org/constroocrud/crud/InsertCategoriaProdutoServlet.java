package org.constroocrud.crud;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.CategoriaProdutoDAO;
import org.constroocrud.crud.conexao.Conexao;
import org.constroocrud.crud.entidades.CategoriaProduto;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InsertCategoriaProdutoServlet", value = "/InserirCategoriaProdutoServlet")
public class InsertCategoriaProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

        String name = req.getParameter("nome");
        String descricao = req.getParameter("descricao");

        CategoriaProduto categoriaProduto = new CategoriaProduto(name,descricao);
        if (categoriaProdutoDAO.inserirCategoriaProduto(categoriaProduto)){
            System.out.println("DEUU");
        }

        //Voce é direcionado para a listagem de usuarios!
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/listagemCategoriaProdutos.jsp");
        rd.include(req, resp);




    }
}
