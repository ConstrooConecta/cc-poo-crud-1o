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

@WebServlet(name = "AlterarAdministradorServlet", value = "/AlterarAdministradorServlet")
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

        int num = administradorDAO.alterarAdministrador(id, administrador);
        if (num == 1){
            out.println("Administrador alterado");
        } else if (num == 0) {
            out.println("Administrador nao alterado");
        } else{
            out.println("Erro");
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemAdministradores.jsp");
        rd.include(req, resp);





    }

}
