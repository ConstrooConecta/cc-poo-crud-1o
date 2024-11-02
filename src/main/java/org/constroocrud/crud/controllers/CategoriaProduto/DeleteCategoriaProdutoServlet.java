package org.constroocrud.crud.controllers.CategoriaProduto;

import jakarta.servlet.RequestDispatcher;
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

/*
 * SERVLET QUE FAZ O DELETE DE CATEGORIAS DE PRODUTO
 * Observação: Este servlet atualmente deleta o registro apenas da tabela de CategoriaProduto.
 * Para uma exclusão completa, seria necessário verificar se o registro também está ausente em outras tabelas, como `usuarios`.
 */

@WebServlet(name = "DeleteCategoriaProdutoServlet", value = "/DeletarCategoriaProdutoServlet")
public class DeleteCategoriaProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Configura o tipo de resposta como HTML e inicializa o PrintWriter
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Define o método de operação como "DELETAR" para exibição na página
        req.setAttribute("metodo", "DELETAR");

        // Recebe o ID da categoria de produto como String e inicializa o ID como 0
        String str_id = req.getParameter("categoria_id");
        int id = 0;

        try {
            // Tenta converter o ID para inteiro
            id = Integer.parseInt(str_id);
        } catch (NumberFormatException numberFormatException) {
            // Em caso de erro na conversão, define retorno "notfound" e redireciona para a página de deleção
            req.setAttribute("retorno", "notfound");
            req.getRequestDispatcher("/pages/categoriaProduto/deletarCategoriaProdutoPeloID.jsp").forward(req, resp);
            return;
        }

        // Instancia o DAO para operar no banco de dados
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

        try {
            // Verifica se a categoria de produto existe no banco de dados
            ResultSet rs = categoriaProdutoDAO.buscarCategoriaProdutoPeloID(id);
            if (!rs.next()) {
                // Se a categoria não é encontrada, define o retorno como "notfound" e redireciona para a página de deleção
                req.setAttribute("retorno", "notfound");
                req.setAttribute("entidade", id);
                req.getRequestDispatcher("/pages/categoriaProduto/deletarCategoriaProdutoPeloID.jsp").forward(req, resp);
            } else {
                // Caso a categoria seja encontrada, obtém seu nome para exibição e realiza a remoção
                String nome = rs.getString("nome");

                int num = categoriaProdutoDAO.removerCategoriaProduto(id);

                // Define o retorno com base no sucesso ou falha da operação de remoção
                if (num == 1) {
                    req.setAttribute("retorno", "certo");
                } else {
                    req.setAttribute("retorno", "erro");
                }

                req.setAttribute("entidade", nome);

                // Redireciona para a página de listagem de categorias de produtos
                req.getRequestDispatcher("/pages/categoriaProduto/listagemCategoriaProdutos.jsp").forward(req, resp);
            }
        } catch (SQLException sqlException) {
            // Captura e trata exceções SQL, configurando mensagem de erro e redirecionando para a listagem
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");

            req.getRequestDispatcher("/pages/categoriaProduto/listagemCategoriaProdutos.jsp").forward(req, resp);
        }
    }
}
