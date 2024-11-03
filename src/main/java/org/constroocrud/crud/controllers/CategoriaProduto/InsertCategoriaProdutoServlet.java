package org.constroocrud.crud.controllers.CategoriaProduto;

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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "InsertCategoriaProdutoServlet", value = "/InserirCategoriaProdutoServlet")
public class InsertCategoriaProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Inicializa DAO para manipulação de categorias de produto
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

        // Recebe os parâmetros da requisição para o nome e descrição da nova categoria
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");

        // Cria um objeto CategoriaProduto com os valores recebidos
        CategoriaProduto categoriaProduto = new CategoriaProduto(nome, descricao);

        try {
            // Verifica se já existe uma categoria com o mesmo nome
            ResultSet rs = categoriaProdutoDAO.buscarCategoriaProdutoPeloNome(nome);

            if (!rs.next()) {  // Caso não exista, prossegue com a inserção
                int num = categoriaProdutoDAO.inserirCategoriaProduto(categoriaProduto);

                // Define o resultado da operação com base no número de registros afetados
                if (num == 1) {
                    req.setAttribute("retorno", "certo"); // Inserção bem-sucedida
                } else if (num == 0) {
                    req.setAttribute("retorno", "notfound"); // Nenhum registro foi afetado
                } else {
                    req.setAttribute("retorno", "erro"); // Erro na operação de inserção
                }
            } else {
                req.setAttribute("retorno", "existente"); // Categoria já existe
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // Exceção SQL lançada como RuntimeException
        }

        // Define atributos de feedback para exibir o resultado da operação
        req.setAttribute("metodo", "INSERIR");
        req.setAttribute("entidade", nome);

        // Redireciona para a página de listagem de categorias de produtos
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/categoriaProduto/listagemCategoriaProdutos.jsp");
        rd.include(req, resp);
    }
}
