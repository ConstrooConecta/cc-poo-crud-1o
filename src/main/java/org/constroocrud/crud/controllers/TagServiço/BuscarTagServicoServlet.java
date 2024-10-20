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
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //Recebe o id da entidade comprador/vendedor ou Profissional

        String nome = req.getParameter("nome");
        req.setAttribute("nome",nome);
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/buscarTagServico.jsp");
        rd.include(req, resp);
    }
}
