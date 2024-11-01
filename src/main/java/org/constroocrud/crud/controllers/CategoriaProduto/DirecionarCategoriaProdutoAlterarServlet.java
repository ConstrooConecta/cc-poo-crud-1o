package org.constroocrud.crud.controllers.CategoriaProduto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DirecionarCategoriaProdutoAlterarServlet", value = "/DirecionarCategoriaProdutoAlterarServlet")
public class DirecionarCategoriaProdutoAlterarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe os dados da categoria de produto a ser alterada a partir dos parâmetros da requisição
        String str_id = req.getParameter("categoria_id");
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");

        // Converte o ID recebido para um inteiro
        int id = Integer.parseInt(str_id);
        out.println(id); // Imprime o ID para fins de depuração

        // Define os atributos da categoria para serem acessados na página de alteração
        req.setAttribute("id", id);
        req.setAttribute("nome", nome);
        req.setAttribute("descricao", descricao);

        // Redireciona para a página de alteração da categoria de produto
        req.getRequestDispatcher("/pages/categoriaProduto/alterarCategoriaProduto.jsp").forward(req, resp);
    }
}
