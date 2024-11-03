package org.constroocrud.crud.controllers.PlanoAtivação;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// Este servlet é responsável por buscar um plano de ativação com base em um ID fornecido.

@WebServlet(name = "BuscarPlanoAtivacaoServlet", value = "/BuscarPlanoAtivacaoServlet")
public class BuscarPlanoAtivacaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Define o tipo de resposta como HTML
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o ID da entidade (plano de ativação)
        String idStr = req.getParameter("id");
        int id;

        try {
            // Tenta converter o ID recebido para inteiro
            id = Integer.parseInt(idStr);
            req.setAttribute("id", id); // Define o ID como um atributo da requisição
        } catch (NumberFormatException e) {
            // Se ocorrer um erro de conversão, define ID como 0
            id = 0;
            req.setAttribute("id", id); // Define 0 como o ID
        } finally {
            // Redireciona para a página de busca de planos de ativação
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/planoAtivacao/buscarPlanosAtivacao.jsp");
            rd.include(req, resp); // Inclui a página na resposta
        }
    }
}
