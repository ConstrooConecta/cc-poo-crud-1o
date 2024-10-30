package org.constroocrud.crud.controllers.TagServiço;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DirecionarTagServicoAlterarServlet", value = "/DirecionarTagServicoServlet")
public class DirecionarTagServicoAlterarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o id da tag de serviço a ser alterada
        String str_id = req.getParameter("tag_id");
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");

        int id = Integer.parseInt(str_id);
        out.println(id);

        // Define os atributos que serão utilizados na página de alteração
        req.setAttribute("id", id);
        req.setAttribute("nome", nome);
        req.setAttribute("descricao", descricao);

        // Redireciona para a página de alteração da tag de serviço
        req.getRequestDispatcher("/pages/tagServico/alterarTagServico.jsp").forward(req, resp);
    }
}