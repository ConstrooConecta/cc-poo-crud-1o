package org.constroocrud.crud.controllers.Inserts;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.TagServicoDAO;
import org.constroocrud.crud.models.TagServico;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InsertTagServicoServlet", value = "/InserirTagServicoServlet")
public class InsertTagServicoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        TagServicoDAO tagServicoDAO = new TagServicoDAO();

        String name = req.getParameter("nome");
        String descricao = req.getParameter("descricao");

        TagServico tagServico = new TagServico(name,descricao);

        int num = tagServicoDAO.inserirTagServico(tagServico);
        if (num == 1){
            out.println("Tag Serviço inserido");
        } else if (num == 0) {
            out.println("Tag Serviço não inserido");
        } else {
            out.println("Erro");
        }

        //Voce é direcionado para a listagem de usuarios!
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemTagServico.jsp");
        rd.include(req, resp);




    }
}
