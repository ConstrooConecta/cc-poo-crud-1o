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

@WebServlet(name = "AlterarTagServicoServlet", value = "/AlterarTagServicoServlet")
public class AlterarTagServicoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //Recebe o id da entidade comprador/vendedor ou Profissional

        String str_id = req.getParameter("id");
        int id = Integer.parseInt(str_id);
        out.println(str_id);

        String name = req.getParameter("nome");
        String descricao = req.getParameter("descricao");


        TagServicoDAO tagServicoDAO = new TagServicoDAO();
        TagServico tagServico = new TagServico(name,descricao);

        out.println(tagServicoDAO.alterarTagServico(id, tagServico));

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/listagemTagServico.jsp");
        rd.include(req, resp);





    }

}
