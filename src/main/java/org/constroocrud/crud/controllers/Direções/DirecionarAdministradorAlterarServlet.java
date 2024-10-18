package org.constroocrud.crud.controllers.Direções;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DirecionarAdministradorAlterarServlet", value = "/DirecionarAdministradorAlterarServlet")
public class DirecionarAdministradorAlterarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe os dados do administrador a ser alterado
        String str_id = req.getParameter("administrador_id");
        String nome = req.getParameter("nome");
        String senha = req.getParameter("senha");
        String email = req.getParameter("email");
        int id = Integer.parseInt(str_id);
        out.println(id);

        // Define atributos para serem usados na página de alteração
        req.setAttribute("id", id);
        req.setAttribute("nome", nome);
        req.setAttribute("senha", senha);
        req.setAttribute("email", email);

        // Redireciona para a página de alteração do administrador
        req.getRequestDispatcher("pages/alterarAdministrador.jsp").forward(req, resp);
    }
}
