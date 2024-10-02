package org.constroocrud.crud;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.CategoriaProdutoDAO;
import org.constroocrud.crud.DAOs.TagServicoDAO;
import org.constroocrud.crud.entidades.CategoriaProduto;
import org.constroocrud.crud.entidades.TagServico;

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

        TagServico tagServico = new TagServico(name);
        if (tagServicoDAO.inserirTagServico(tagServico)){
            System.out.println("DEUU");
        }

        //Voce é direcionado para a listagem de usuarios!
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/listagemTagServico.jsp");
        rd.include(req, resp);




    }
}
