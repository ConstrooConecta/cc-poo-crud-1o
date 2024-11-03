package org.constroocrud.crud.controllers.TagServiço;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BuscarTagServicoServlet", value = "/BuscarTagServicoServlet")
public class BuscarTagServicoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Define o tipo de resposta como HTML
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o nome da tag de serviço a ser buscada
        String nome = req.getParameter("nome");
        req.setAttribute("nome", nome); // Define o nome como atributo da requisição

        // Redireciona para a página de busca de tags de serviço
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/tagServico/buscarTagServico.jsp");
        rd.include(req, resp); // Inclui a página JSP na resposta
    }
}
