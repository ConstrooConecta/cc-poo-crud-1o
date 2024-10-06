package org.constroocrud.crud;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;
import org.constroocrud.crud.DAOs.CategoriaProdutoDAO;
import org.constroocrud.crud.entidades.Administrador;
import org.constroocrud.crud.entidades.CategoriaProduto;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "InsertAdministradorServlet", value = "/InserirAdministradorServlet")
public class InsertAdministradorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        AdministradorDAO administradorDAO = new AdministradorDAO();

        String name = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        Administrador administrador = new Administrador(name, email, senha);
        if (administradorDAO.inserirAdministrador(administrador)){

            System.out.println("DEUU");
        }

        //Voce é direcionado para a listagem de usuarios!
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/listagemAdministradores.jsp");
        rd.include(req, resp);



    }
}