package org.constroocrud.crud.controllers.Plano;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BuscarPlanoServlet", value = "/BuscarPlanoServlet")
public class BuscarPlanoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o nome do plano a ser buscado
        String nome = req.getParameter("nome");

        // Define o atributo "nome" para ser utilizado na página JSP
        req.setAttribute("nome", nome);

        // Redireciona para a página de busca de planos
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/plano/buscarPlano.jsp");
        rd.include(req, resp);
    }
}
