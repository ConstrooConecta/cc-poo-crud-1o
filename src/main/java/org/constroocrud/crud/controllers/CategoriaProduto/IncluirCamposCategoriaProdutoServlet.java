package org.constroocrud.crud.controllers.CategoriaProduto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.CategoriaProdutoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "IncluirCamposCategoriaProdutoServlet", value = "/IncluirCamposCategoriaProdutoServlet")
public class IncluirCamposCategoriaProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configura o tipo de resposta como HTML e inicializa o PrintWriter para saída
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o ID da categoria de produto e o inicializa como 0
        String str_id = req.getParameter("categoria_id");
        int id = 0;

        try {
            // Tenta converter o ID da categoria para inteiro
            id = Integer.parseInt(str_id);
        } catch (NumberFormatException numberFormatException) {
            // Em caso de erro na conversão, configura o retorno como "notfound" e redireciona para a página de alteração
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Categoria não encontrada!");
            req.getRequestDispatcher("/pages/categoriaProduto/alterarCategoriaProdutoPeloID.jsp").forward(req, resp);
            return;
        }

        // Instancia o DAO para interagir com o banco de dados
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

        // Busca a categoria de produto pelo ID no banco de dados
        ResultSet resultSet = categoriaProdutoDAO.buscarCategoriaProdutoPeloID(id);

        try {
            // Verifica se a categoria foi encontrada no banco de dados
            if (!resultSet.next()) {
                // Se a categoria não foi encontrada, define o retorno como "notfound" e redireciona para a página de alteração
                req.setAttribute("retorno", "notfound");
                req.setAttribute("mensagem", "Categoria não encontrada!");
            } else {
                // Obtém os dados da categoria para exibição na página de alteração
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");

                // Define atributos para serem exibidos na página de alteração
                req.setAttribute("id", id);
                req.setAttribute("nome", nome);
                req.setAttribute("descricao", descricao);

                // Redireciona para a página de alteração da categoria com os dados preenchidos
                req.getRequestDispatcher("/pages/categoriaProduto/alterarCamposCategoriaProduto.jsp").forward(req, resp);
                return;
            }
        } catch (SQLException sqlException) {
            // Em caso de erro SQL, imprime o stack trace e define o retorno como "erro" com a mensagem correspondente
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");
        } finally {
            // Redireciona para a página de alteração caso ocorra erro de qualquer tipo
            req.getRequestDispatcher("/pages/categoriaProduto/alterarCategoriaProdutoPeloID.jsp").forward(req, resp);
        }
    }
}
