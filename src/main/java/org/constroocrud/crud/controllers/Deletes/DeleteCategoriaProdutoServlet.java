package org.constroocrud.crud.controllers.Deletes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.CategoriaProdutoDAO;

import java.io.IOException;
import java.io.PrintWriter;

// Servlet mapeada para "/DeletarCategoriaProdutoServlet"
@WebServlet(name = "DeleteCategoriaProdutoServlet", value = "/DeletarCategoriaProdutoServlet")
public class DeleteCategoriaProdutoServlet extends HttpServlet {

    // Método que trata requisições POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o ID da categoria de produto a ser deletada
        String id = req.getParameter("categoria_id");
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

        // Verifica se a remoção foi bem-sucedida
        if (categoriaProdutoDAO.removerCategoriaProduto(Integer.parseInt(id))) {
            System.out.println("Deu certo");
        } else {
            System.out.println("Nao deu certo");
        }

        // Redireciona para a página de listagem de categorias de produto
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/listagemCategoriaProdutos.jsp");
        rd.include(req, resp);
    }
}