package org.constroocrud.crud.controllers.Alterações;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.CategoriaProdutoDAO;
import org.constroocrud.crud.models.CategoriaProduto;

import java.io.IOException;
import java.io.PrintWriter;

// Servlet mapeada para a rota "/AlterarCategoriaProdutoServlet"
@WebServlet(name = "AlterarCategoriaProdutoServlet", value = "/AlterarCategoriaProdutoServlet")
public class AlterarCategoriaProdutoServlet extends HttpServlet {

    // Método que trata requisições POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Captura e converte parâmetros
        String str_id = req.getParameter("id");
        int id = Integer.parseInt(str_id);
        out.println(str_id);

        String name = req.getParameter("nome");
        String descricao = req.getParameter("descricao");

        // Atualiza a categoria de produto
        CategoriaProduto categoriaProduto = new CategoriaProduto(name, descricao);
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

        out.println(categoriaProdutoDAO.alterarCategoriaProduto(id, categoriaProduto));

        // Redireciona para a página de listagem
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/listagemCategoriaProdutos.jsp");
        rd.forward(req, resp);
    }
}