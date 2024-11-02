package org.constroocrud.crud.controllers.PlanoAtivação;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoAtivacaoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

// Este servlet é responsável por deletar planos de ativação.

@WebServlet(name = "DeletePlanoAtivacaoServlet", value = "/DeletarPlanoAtivacaoServlet")
public class DeletePlanoAtivacaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Define o tipo de resposta como HTML
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.setAttribute("metodo", "DELETAR");

        //Recebe o id da entidade comprador/vendedor ou Profissional
        String str_id_tipo = req.getParameter("id_planoativacao");
        int id = 0;
        try {
            id = Integer.parseInt(str_id_tipo);
        } catch (NumberFormatException numberFormatException) {
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Plano Ativação não encontrado!");
            req.getRequestDispatcher("/pages/planoAtivacao/deletarPlanoAtivacaoPeloID.jsp").forward(req, resp);
        } finally {
            //Estabelece a conexao
            PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();

            try {
                ResultSet rs = planoAtivacaoDAO.buscarPlanoAtivacaoPeloID(id);
                if (!rs.next()) {
                    req.setAttribute("retorno", "notfound");
                    req.setAttribute("mensagem", "Plano Ativação não encontrado!");
                    req.setAttribute("entidade", id);

                    req.getRequestDispatcher("/pages/planoAtivacao/deletarPlanoAtivacaoPeloID.jsp").forward(req, resp);
                } else {

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
}
