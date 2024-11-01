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

        // Recebe o ID do plano de ativação a ser deletado
        String str_id_tipo = req.getParameter("id_planoativacao");
        int id_tipo = Integer.parseInt(str_id_tipo); // Converte o ID para inteiro

        // Estabelece a conexão com o DAO
        PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();

        try {
            // Verifica se o plano de ativação existe
            ResultSet rs = planoAtivacaoDAO.buscarPlanoAtivacaoPeloID(id_tipo);
            if (!rs.next()) {
                // Se não encontrado, define atributos para resposta
                req.setAttribute("retorno", "notfound");
                req.setAttribute("metodo", "DELETAR");
                req.setAttribute("entidade", id_tipo);

                // Redireciona para a página de listagem de planos de ativação
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/planoAtivacao/listagemPlanosAtivacao.jsp");
                rd.include(req, resp);
            } else {
                // Tenta remover o plano de ativação
                int num = planoAtivacaoDAO.removerPlanoAtivacao(id_tipo);

                // Verifica o resultado da operação de remoção
                if (num == 1) {
                    req.setAttribute("retorno", "certo"); // Deletado com sucesso
                } else if (num == 0) {
                    req.setAttribute("retorno", "notfound"); // Não encontrado
                } else {
                    req.setAttribute("retorno", "erro"); // Erro inesperado
                }

                req.setAttribute("metodo", "DELETAR");
                req.setAttribute("entidade", id_tipo);

                // Redireciona para a página de listagem de planos de ativação
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/planoAtivacao/listagemPlanosAtivacao.jsp");
                rd.include(req, resp);
            }
        } catch (SQLException sqlException) {
            // Trata exceções SQL
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");

            // Redireciona para a página de listagem de planos de ativação
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/planoAtivacao/listagemPlanosAtivacao.jsp");
            rd.include(req, resp);
        }
    }
}