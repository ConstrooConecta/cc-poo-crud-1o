package org.constroocrud.crud;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;
import org.constroocrud.crud.entidades.Administrador;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AlterarCategoriaProdutoServlet", value = "/AlterarCategoriaProdutoServlet")
public class AlterarAdministradorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //Recebe o id da entidade comprador/vendedor ou Profissional

        String str_id = req.getParameter("id");
        int id = Integer.parseInt(str_id);
        out.println(str_id);

        String name = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        Administrador administrador = new Administrador(name, email, senha);
        AdministradorDAO administradorDAO = new AdministradorDAO();

        out.println(administradorDAO.alterarAdministrador(id,administrador));

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/listagemAdministradores.jsp");
        rd.include(req, resp);





    }

}