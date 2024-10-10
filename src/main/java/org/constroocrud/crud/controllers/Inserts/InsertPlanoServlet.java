package org.constroocrud.crud.controllers.Inserts;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoDAO;
import org.constroocrud.crud.DAOs.TagServicoDAO;
import org.constroocrud.crud.models.Plano;
import org.constroocrud.crud.models.TagServico;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InsertTagServicoServlet", value = "/InserirTagServicoServlet")
public class InsertPlanoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        PlanoDAO planoDAO = new PlanoDAO();

        String name = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String duracao = req.getParameter("duracao");
        

        Plano plano = new Plano();
        if (planoDAO.inserirPlano(plano)){
            System.out.println("DEUU");
        }

        //Voce é direcionado para a listagem de usuarios!
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemTagServico.jsp");
        rd.include(req, resp);




    }
}
