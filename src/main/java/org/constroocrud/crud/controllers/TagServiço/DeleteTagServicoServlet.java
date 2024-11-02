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

@WebServlet(name = "DeleteTagServicoServlet", value = "/DeletarTagServicoServlet")
public class DeleteTagServicoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Define o tipo de resposta como HTML
        resp.setContentType("text/html");

        req.setAttribute("metodo", "DELETAR");

        // Recebe e converte o ID da Tag Serviço
        String str_id = req.getParameter("tag_id");
        int id;

        try {
            id = Integer.parseInt(str_id);
        } catch (NumberFormatException e) {
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Tag Serviço não encontrado!");
            req.getRequestDispatcher("/pages/tagServico/deletarTagServicoPeloID.jsp").forward(req, resp);
            return;
        }

        TagServicoDAO tagServicoDAO = new TagServicoDAO();

        try {
            // Verifica se a Tag Serviço existe no banco de dados
            ResultSet rs = tagServicoDAO.buscarTagServicoPeloID(id);
            if (!rs.next()) {
                req.setAttribute("retorno", "notfound");
                req.setAttribute("mensagem", "Tag Serviço não encontrado!");
                req.setAttribute("entidade", id);
                req.getRequestDispatcher("/pages/tagServico/deletarTagServicoPeloID.jsp").forward(req, resp);
            } else {
                // Recupera o nome da tag para feedback
                String nome = rs.getString("nome");
                req.setAttribute("entidade", nome);

                // Executa a remoção
                int num = tagServicoDAO.removerTagServicoPeloID(id);

                req.setAttribute("retorno", num == 1 ? "certo" : "erro");
                req.getRequestDispatcher("/pages/tagServico/listagemTagServico.jsp").forward(req, resp);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");
            req.getRequestDispatcher("/pages/tagServico/listagemTagServico.jsp").forward(req, resp);
        }
    }
}
