package org.constroocrud.crud.controllers.PlanoAtivação;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BuscarPlanoAtivacaoServlet", value = "/BuscarPlanoAtivacaoServlet")
public class BuscarPlanoAtivacaoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //Recebe o id da entidade comprador/vendedor ou Profissional

        String idStr = req.getParameter("id");
        int id;
        try{
            id = Integer.parseInt(idStr);
            req.setAttribute("id",id);

        }catch (NumberFormatException e){
            id = 0;
            req.setAttribute("id",id);

        }finally {
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/planoAtivacao/buscarPlanosAtivacao.jsp");
            rd.include(req, resp);
        }


    }
}
