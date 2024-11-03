package org.constroocrud.crud.controllers.CategoriaProduto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BuscarCategoriaProdutoServlet", value = "/BuscarCategoriaProdutoServlet")
public class BuscarCategoriaProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o nome da categoria do produto a ser buscada a partir do parâmetro da requisição
        String nome = req.getParameter("nome");

        // Define o nome recebido como atributo da requisição para ser acessado no JSP
        req.setAttribute("nome", nome);

        // Encaminha a requisição e resposta para o JSP de busca de categoria de produtos
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/categoriaProduto/buscarCategoriaProdutos.jsp");
        rd.include(req, resp); // Usa include para manter a requisição original e permitir visualização no JSP
    }
}
