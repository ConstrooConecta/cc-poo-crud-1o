package org.constroocrud.crud.controllers.PlanoAtivação;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoAtivacaoDAO;

import java.io.IOException;
import java.io.PrintWriter;

// Servlet responsável por alterar o status de ativação de um plano com base no ID fornecido.
@WebServlet(name = "AlterarPlanoAtivacaoServlet", value = "/AlterarPlanoAtivacaoServlet")
public class AlterarPlanoAtivacaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Define o tipo de resposta como HTML
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o ID do plano de ativação e tenta converter para inteiro
        String str_id_tipo = req.getParameter("id_planoativacao");
        int id_tipo;

        req.setAttribute("metodo", "ALTERAR");

        try {
            id_tipo = Integer.parseInt(str_id_tipo);
        } catch (NumberFormatException e) {
            // Em caso de erro na conversão, configura o retorno como "notfound" e redireciona
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Plano de Ativação não encontrado!");
            req.getRequestDispatcher("/pages/planoAtivacao/alterarPlanoAtivacaoPeloID.jsp").forward(req, resp);
            return;
        }

        // Estabelece a conexão com o DAO para alterar o status de ativação
        PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();
        req.setAttribute("entidade", id_tipo);

        int num = planoAtivacaoDAO.alterarAtivacao(id_tipo);

        if (num == 1) {
            // Se a alteração foi bem-sucedida, redireciona para a listagem com mensagem de sucesso
            req.setAttribute("retorno", "certo");
            req.getRequestDispatcher("/pages/planoAtivacao/listagemPlanosAtivacao.jsp").forward(req, resp);
        } else if (num == 0) {
            // Se o plano de ativação não foi encontrado, retorna para a página de alteração com mensagem de erro
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Plano de Ativação não encontrado!");
            req.getRequestDispatcher("/pages/planoAtivacao/alterarPlanoAtivacaoPeloID.jsp").forward(req, resp);
        } else {
            // Em caso de erro desconhecido, redireciona para a listagem com mensagem de erro
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro ao alterar o status de ativação.");
            req.getRequestDispatcher("/pages/planoAtivacao/listagemPlanosAtivacao.jsp").forward(req, resp);
        }
    }
}
