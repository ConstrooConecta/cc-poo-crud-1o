package org.constroocrud.crud.controllers.Alterações;

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

// Servlet mapeada para a rota "/AlterarAdministradorServlet"
@WebServlet(name = "AlterarAdministradorServlet", value = "/AlterarAdministradorServlet")
public class AlterarAdministradorServlet extends HttpServlet {

    // Método que trata requisições POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Captura e converte parâmetros
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        // Atualiza o administrador
        Administrador administrador = new Administrador(name, email, senha);
        AdministradorDAO administradorDAO = new AdministradorDAO();
        out.println(administradorDAO.alterarAdministrador(id, administrador));

        // Redireciona para a página de listagem
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/listagemAdministradores.jsp");
        rd.include(req, resp);
    }
}