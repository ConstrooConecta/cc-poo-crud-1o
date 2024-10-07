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

        //Recebe o id da entidade comprador/vendedor ou Profissional

        String str_id = req.getParameter("administrador_id");
        int id = Integer.parseInt(str_id);
        out.println(id);
        req.setAttribute("id",id);


        req.getRequestDispatcher("pages/alterarAdministrador.jsp").forward(req, resp);


    }

}
