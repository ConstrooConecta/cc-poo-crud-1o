package org.constroocrud.crud.controllers.Administrador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;
import org.constroocrud.crud.models.Administrador;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "BuscarAdministradorServlet", value = "/BuscarAdministradorServlet")
public class BuscarAdministradorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //Recebe o id da entidade comprador/vendedor ou Profissional

        String nome = req.getParameter("nome");
        req.setAttribute("nome", nome);
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/administrador/buscarAdministrador.jsp");
        rd.include(req, resp);
    }
}
