package org.constroocrud.crud.controllers.Alterações;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoAtivacaoDAO;

import java.io.IOException;
import java.io.PrintWriter;

// Servlet mapeada para "/AlterarPlanoAtivacaoServlet"
@WebServlet(name = "AlterarPlanoAtivacaoServlet", value = "/AlterarPlanoAtivacaoServlet")
public class AlterarPlanoAtivacaoServlet extends HttpServlet {

    // Método que trata requisições POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o ID do Plano de Ativação e converte para int
        String str_id_tipo = req.getParameter("id_planoativacao");
        int id_tipo = Integer.parseInt(str_id_tipo);

        out.println(id_tipo);

        // Estabelece a conexão com o DAO
        PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();

        // Altera a ativação na tabela Plano Ativação e verifica o resultado
        int result = planoAtivacaoDAO.alterarAtivacao(id_tipo);
        if (result == 1){
            out.println("Ativação alterada");
        } else if (result == 0){
            out.println("Ativação não encontrada");
        } else {
            out.println("ERRO");
        }

        // Redireciona para a página de listagem de Planos de Ativação
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemPlanosAtivacao.jsp");
        rd.forward(req, resp);
    }
}