package org.constroocrud.crud.controllers.Alterações;

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

// Servlet mapeada para "/AlterarTagServicoServlet"
@WebServlet(name = "AlterarTagServicoServlet", value = "/AlterarTagServicoServlet")
public class AlterarTagServicoServlet extends HttpServlet {

    // Método que trata requisições POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe e converte o ID
        String str_id = req.getParameter("id");
        int id = Integer.parseInt(str_id);
        out.println(str_id);

        // Recebe os outros parâmetros
        String name = req.getParameter("nome");
        String descricao = req.getParameter("descricao");

        // Instancia o DAO e cria um objeto TagServico
        TagServicoDAO tagServicoDAO = new TagServicoDAO();
        TagServico tagServico = new TagServico(name, descricao);

        // Altera a tag no banco e exibe o resultado
        out.println(tagServicoDAO.alterarTagServico(id, tagServico));

        // Redireciona para a página de listagem de Tags de Serviço
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/listagemTagServico.jsp");
        rd.include(req, resp);
    }
}