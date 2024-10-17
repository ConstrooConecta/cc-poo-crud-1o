package org.constroocrud.crud.controllers.Inserts;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.CategoriaProdutoDAO;
import org.constroocrud.crud.models.CategoriaProduto;

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

        int num = categoriaProdutoDAO.inserirCategoriaProduto(categoriaProduto);
        if (num == 1){
            out.println("Categoria Produto inserido");
        } else if (num == 0){
            out.println("Categoria Produto não inserido");
        } else{
            out.println("Erro");
        }

        //Voce é direcionado para a listagem de usuarios!
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemCategoriaProdutos.jsp");
        rd.include(req, resp);




    }
}
