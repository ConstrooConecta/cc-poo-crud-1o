package org.constroocrud.crud.controllers.CategoriaProduto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DirecionarCategoriaProdutoAlterarServlet", value = "/DirecionarCategoriaProdutoAlterarServlet")
public class DirecionarCategoriaProdutoAlterarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe os dados da categoria de produto a ser alterada
        String str_id = req.getParameter("categoria_id");
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        int id = Integer.parseInt(str_id);
        out.println(id);

        // Define atributos para serem usados na página de alteração
        req.setAttribute("id", id);
        req.setAttribute("nome", nome);
        req.setAttribute("descricao", descricao);

        // Redireciona para a página de alteração da categoria de produto
        req.getRequestDispatcher("pages/alterarCategoriaProduto.jsp").forward(req, resp);
    }
}