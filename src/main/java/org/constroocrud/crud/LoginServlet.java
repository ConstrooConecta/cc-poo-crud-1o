package org.constroocrud.crud;

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


        String senhacerta = administradorDAO.buscaSenhaPorEmail(req.getParameter("email-admin"));
        String senhaInserida = req.getParameter("senha-admin");



        if (senhaInserida.equals(senhacerta)){
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listagemCategoriaProdutos.jsp");
            rd.include(req, resp);
        }else {
            out.println("Senha incorreta");
            out.println(senhacerta);
            out.println( req.getParameter("senha-admin"));
           ;
        }

    }
}
