package org.constroocrud.crud.controllers.Deletes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.TagServicoDAO;

import java.io.IOException;
import java.io.PrintWriter;

// Servlet para deletar uma Tag de Serviço
@WebServlet(name = "DeleteTagServicoServlet", value = "/DeletarTagServicoServlet")
public class DeleteTagServicoServlet extends HttpServlet {

    // Método que trata requisições POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o ID da Tag de Serviço a ser deletada
        String id = req.getParameter("tag_id");
        TagServicoDAO tagServicoDAO = new TagServicoDAO();

        // Verifica se a remoção foi bem-sucedida
        if (tagServicoDAO.removerTagServicoPeloID(Integer.parseInt(id))) {
            System.out.println("Deu certo");
        } else {
            System.out.println("Nao deu certo");
        }

        // Redireciona para a página de listagem de Tags de Serviço
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/listagemTagServico.jsp");
        rd.include(req, resp);
    }
}
