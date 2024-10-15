package org.constroocrud.crud.controllers.Deletes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoAtivacaoDAO;

import java.io.IOException;
import java.io.PrintWriter;

// Servlet mapeada para "/DeletarPlanoAtivacaoServlet"
@WebServlet(name = "DeletePlanoAtivacaoServlet", value = "/DeletarPlanoAtivacaoServlet")
public class DeletePlanoAtivacaoServlet extends HttpServlet {

    // Método que trata requisições POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o ID do plano de ativação a ser deletado
        String str_id_tipo = req.getParameter("id_planoativacao");
        int id_tipo = Integer.parseInt(str_id_tipo);

        out.println(id_tipo);

        // Estabelece a conexão
        PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();

        // Verifica se a remoção foi bem-sucedida
        if (planoAtivacaoDAO.removerPlanoAtivacao(id_tipo)) {
            out.println("removido");
        }

        // Redireciona para a página de listagem de planos de ativação
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/listagemPlanosAtivacao.jsp");
        rd.forward(req, resp);
    }
}