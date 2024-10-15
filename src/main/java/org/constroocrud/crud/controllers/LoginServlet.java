package org.constroocrud.crud.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdministradorDAO administradorDAO = new AdministradorDAO();

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Busca a senha correta do administrador com base no email fornecido
        String senhacerta = administradorDAO.buscaSenhaPorEmail(req.getParameter("email-admin"));
        String senhaInserida = req.getParameter("senha-admin");

        // Verifica se a senha inserida corresponde à senha correta
        if (senhaInserida.equals(senhacerta)) {
            // Redireciona para a página de listagem de categorias de produtos se a senha estiver correta
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/listagemCategoriaProdutos.jsp");
            rd.include(req, resp);
        } else {
            // Se a senha estiver incorreta, exibe uma mensagem de erro
            out.println("Senha incorreta");
            out.println(senhacerta);
            out.println(req.getParameter("senha-admin"));
        }
    }
}
