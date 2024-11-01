package org.constroocrud.crud.controllers.TagServiço;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.CategoriaProdutoDAO;
import org.constroocrud.crud.DAOs.TagServicoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "IncluirCamposTagServicoServlet", value = "/IncluirCamposTagServicoServlet")
public class IncluirCamposTagServicoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe os dados do administrador a ser alterado
        String str_id = req.getParameter("tag_id");
        int id = 0;

        try {
            id = Integer.parseInt(str_id);
        } catch (NumberFormatException numberFormatException) {
            // Define atributos para serem usados na página de alteração
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Admin não encontrado!");

            // Redireciona para a página de alteração do administrador
            req.getRequestDispatcher("/pages/tagServico/alterarTagServicoPeloID.jsp").forward(req, resp);
        } finally {

            TagServicoDAO tagServicoDAO = new TagServicoDAO();

            ResultSet resultSet = tagServicoDAO.buscarTagServicoPeloID(id);

            try {
                if (!resultSet.next()) {
                    // Define atributos para serem usados na página de alteração
                    req.setAttribute("retorno", "notfound");
                    req.setAttribute("mensagem", "Admin não encontrado!");
                } else {

                    String nome = resultSet.getString("nome");
                    String descricao = resultSet.getString("descricao");

                    // Define atributos para serem usados na página de alteração
                    req.setAttribute("id", id);
                    req.setAttribute("nome", nome);
                    req.setAttribute("descricao", descricao);

                    // Redireciona para a página de alteração do administrador
                    req.getRequestDispatcher("/pages/tagServico/alterarCamposTagServico.jsp").forward(req, resp);
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                // Define atributos para serem usados na página de alteração
                req.setAttribute("retorno", "erro");
                req.setAttribute("mensagem", "Erro SQL");
            }finally {
                // Redireciona para a página de alteração do administrador
                req.getRequestDispatcher("/pages/tagServico/alterarTagServicoPeloID.jsp").forward(req, resp);
            }
        }
    }
}
