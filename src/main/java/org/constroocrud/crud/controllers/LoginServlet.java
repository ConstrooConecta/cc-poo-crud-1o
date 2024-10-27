package org.constroocrud.crud.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;
import org.mindrot.jbcrypt.BCrypt;

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


        try {
            // Verifica se a senha inserida corresponde à senha correta
            if (BCrypt.checkpw(senhaInserida, senhacerta)) {

                // Redireciona para a página de listagem de categorias de produtos se a senha estiver correta
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/pages/listagemAdministradores.jsp");
                rd.include(req, resp);
            } else {

                // Se a senha estiver incorreta, exibe uma mensagem de erro
                req.setAttribute("retorno", "senha incorreta");
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/login.jsp");
                rd.include(req, resp);

            }
        }catch (NullPointerException e){
            req.setAttribute("retorno", "email incorreto");
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.include(req, resp);
        }
    }
}
