package org.constroocrud.crud.controllers.TagServiço;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.TagServicoDAO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "IncluirCamposTagServicoServlet", value = "/IncluirCamposTagServicoServlet")
public class IncluirCamposTagServicoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // Recebe o ID da Tag Serviço
        String str_id = req.getParameter("tag_id");
        int id;

        // Valida o ID da Tag Serviço
        try {
            id = Integer.parseInt(str_id);
        } catch (NumberFormatException e) {
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Tag Serviço não encontrada!");
            req.getRequestDispatcher("/pages/tagServico/alterarTagServicoPeloID.jsp").forward(req, resp);
            return; // Para garantir que a execução pare após o redirecionamento
        }

        TagServicoDAO tagServicoDAO = new TagServicoDAO();
        ResultSet resultSet;

        try {
            // Busca a Tag Serviço pelo ID
            resultSet = tagServicoDAO.buscarTagServicoPeloID(id);
            if (!resultSet.next()) {
                req.setAttribute("retorno", "notfound");
                req.setAttribute("mensagem", "Tag Serviço não encontrada!");
            } else {
                // Recupera os detalhes da Tag Serviço
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");

                // Define atributos para a página de alteração
                req.setAttribute("id", id);
                req.setAttribute("nome", nome);
                req.setAttribute("descricao", descricao);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro ao acessar o banco de dados.");
        }

        // Redireciona para a página de alteração do Tag Serviço
        req.getRequestDispatcher("/pages/tagServico/alterarCamposTagServico.jsp").forward(req, resp);
    }
}
