package org.constroocrud.crud.controllers.PlanoAtivação;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoAtivacaoDAO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

// Servlet responsável por deletar planos de ativação com base no ID fornecido
@WebServlet(name = "DeletePlanoAtivacaoServlet", value = "/DeletarPlanoAtivacaoServlet")
public class DeletePlanoAtivacaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Define o tipo de resposta como HTML
        resp.setContentType("text/html");

        // Recebe o ID do plano de ativação e tenta converter para inteiro
        String str_id_tipo = req.getParameter("id_planoativacao");
        int id;

        req.setAttribute("metodo", "DELETAR");

        try {
            id = Integer.parseInt(str_id_tipo);
        } catch (NumberFormatException e) {
            // Caso o ID não seja um número válido, configura o retorno como "notfound" e redireciona
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Plano de Ativação não encontrado!");
            req.getRequestDispatcher("/pages/planoAtivacao/deletarPlanoAtivacaoPeloID.jsp").forward(req, resp);
            return;
        }

        PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();

        try {
            // Verifica se o plano existe no banco de dados
            ResultSet rs = planoAtivacaoDAO.buscarPlanoAtivacaoPeloID(id);
            if (!rs.next()) {
                req.setAttribute("retorno", "notfound");
                req.setAttribute("mensagem", "Plano de Ativação não encontrado!");
                req.setAttribute("entidade", id);
                req.getRequestDispatcher("/pages/planoAtivacao/deletarPlanoAtivacaoPeloID.jsp").forward(req, resp);
            } else {
                // Remove o plano de ativação
                int num = planoAtivacaoDAO.removerPlanoAtivacao(id);

                if (num == 1) {
                    req.setAttribute("retorno", "certo");
                } else {
                    req.setAttribute("retorno", "erro");
                }

                req.setAttribute("entidade", id);
                req.getRequestDispatcher("/pages/planoAtivacao/listagemPlanosAtivacao.jsp").forward(req, resp);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");
            req.getRequestDispatcher("/pages/planoAtivacao/listagemPlanosAtivacao.jsp").forward(req, resp);
        }
    }
}
