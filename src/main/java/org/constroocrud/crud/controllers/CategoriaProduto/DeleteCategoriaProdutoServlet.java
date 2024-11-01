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

// SERVLET QUE FAZ O DELETE DE CATEGORIAS DE PRODUTO
// Observação: Este servlet atualmente deleta o registro apenas da tabela de CategoriaProduto.
// Para uma exclusão completa, seria necessário verificar se o registro também está ausente em outras tabelas, como `usuarios`.

@WebServlet(name = "DeleteCategoriaProdutoServlet", value = "/DeletarCategoriaProdutoServlet")
public class DeleteCategoriaProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.setAttribute("metodo", "DELETAR");

        String str_id = req.getParameter("categoria_id");
        int id = 0;

        try {
            id = Integer.parseInt(str_id);
        }catch (NumberFormatException numberFormatException) {
            req.setAttribute("retorno", "notfound");
            req.getRequestDispatcher("/pages/categoriaProduto/deletarCategoriaProdutoPeloID.jsp").forward(req, resp);
        }finally {

            CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

            try {
                ResultSet rs = categoriaProdutoDAO.buscarCategoriaProdutoPeloID(id);
                if (!rs.next()) {
                    req.setAttribute("retorno", "notfound");
                    req.setAttribute("entidade", id);

        // Recebe o ID da categoria do produto a ser deletada
        String id = req.getParameter("categoria_id");

        // Cria uma instância do DAO para manipulação dos dados da CategoriaProduto
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

        try {
            // Verifica se a categoria existe no banco de dados antes de deletar
            ResultSet rs = categoriaProdutoDAO.buscarCategoriaProdutoPeloID(Integer.parseInt(id));

            if (!rs.next()) {
                // Caso a categoria não seja encontrada, define os atributos de erro na requisição
                req.setAttribute("retorno", "notfound");
                req.setAttribute("metodo", "DELETAR");
                req.setAttribute("entidade", id);

                // Encaminha a requisição para o JSP de listagem com o erro "não encontrado"
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/categoriaProduto/listagemCategoriaProdutos.jsp");
                rd.include(req, resp);
            } else {
                // Se a categoria for encontrada, realiza o delete e define o nome da entidade
                String nome = rs.getString("nome");

                // Executa o delete e verifica o resultado da operação
                int num = categoriaProdutoDAO.removerCategoriaProduto(Integer.parseInt(id));

                // Define o resultado da operação na requisição para controle de fluxo e mensagens
                if (num == 1) {
                    req.setAttribute("retorno", "certo"); // Exclusão bem-sucedida
                } else if (num == 0) {
                    req.setAttribute("retorno", "notfound"); // Categoria não encontrada
                } else {
                    req.setAttribute("retorno", "erro"); // Erro ao deletar
                }


                    req.getRequestDispatcher("/pages/categoriaProduto/deletarCategoriaProdutoPeloID.jsp").forward(req, resp);
                } else {


                    String nome = rs.getString("nome");

                    int num = categoriaProdutoDAO.removerCategoriaProduto(id);

                    if (num == 1) {
                        req.setAttribute("retorno", "certo");
                    } else {
                        req.setAttribute("retorno", "erro");
                    }

                    req.setAttribute("entidade", nome);


                    req.getRequestDispatcher("/pages/categoriaProduto/listagemCategoriaProdutos.jsp").forward(req, resp);
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                req.setAttribute("retorno", "erro");
                req.setAttribute("mensagem", "Erro SQL");

                req.getRequestDispatcher("/pages/categoriaProduto/listagemCategoriaProdutos.jsp").forward(req, resp);

            }

                // Encaminha a requisição para o JSP de listagem de categorias com o resultado
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/categoriaProduto/listagemCategoriaProdutos.jsp");
                rd.include(req, resp);
            }
        } catch (SQLException sqlException) {
            // Tratamento de exceções SQL
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");

            // Encaminha a requisição para o JSP com a mensagem de erro
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/categoriaProduto/listagemCategoriaProdutos.jsp");
            rd.include(req, resp);
        }
    }
}
