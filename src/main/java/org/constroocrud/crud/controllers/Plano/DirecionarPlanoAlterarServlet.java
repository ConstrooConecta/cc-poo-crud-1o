package org.constroocrud.crud.controllers.Plano;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DirecionarPlanoAlterarServlet", value = "/DirecionarPlanoAlterarServlet")
public class DirecionarPlanoAlterarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe os dados do plano a ser alterado
        String str_id = req.getParameter("plano_id");
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String duracao = req.getParameter("duracao");
        String tipo = req.getParameter("tipoUsuario");
        String valor = req.getParameter("precoMensal");

        int id = Integer.parseInt(str_id);
        out.println(id);

        // Define os atributos que serão utilizados na página de alteração
        req.setAttribute("id", id);
        req.setAttribute("nome", nome);
        req.setAttribute("descricao", descricao);
        req.setAttribute("duracao", duracao);
        req.setAttribute("tipo", tipo);
        req.setAttribute("valor", valor);

        // Redireciona para a página de alteração do plano
        req.getRequestDispatcher("/pages/plano/alterarPlano.jsp").forward(req, resp);
    }
}