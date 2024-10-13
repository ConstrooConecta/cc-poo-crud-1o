package org.constroocrud.crud.controllers.Alterações;

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

@WebServlet(name = "AlterarCategoriaProdutoServlet", value = "/AlterarCategoriaProdutoServlet")
public class AlterarCategoriaProdutoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //Recebe o id da entidade comprador/vendedor ou Profissional

        String str_id = req.getParameter("id");
        int id = Integer.parseInt(str_id);
        out.println(str_id);

        String name = req.getParameter("nome");
        String descricao = req.getParameter("descricao");


        CategoriaProduto categoriaProduto = new CategoriaProduto(name, descricao);
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

        int num = categoriaProdutoDAO.alterarCategoriaProduto(id, categoriaProduto);
        if (num == 1) {
            out.println("Categoria Produto alterado");
        } else if (num == 0) {
            out.println("Categoria Produto não alterado");
        } else {
            out.println("Erro");
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemCategoriaProdutos.jsp");
        rd.forward(req, resp);





    }

}
