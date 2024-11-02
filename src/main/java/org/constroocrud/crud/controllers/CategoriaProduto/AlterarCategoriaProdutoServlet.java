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

@WebServlet(name = "AlterarCategoriaProdutoServlet", value = "/AlterarCategoriaProdutoServlet")
public class AlterarCategoriaProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o id da entidade CategoriaProduto enviado pela requisição
        String str_id = req.getParameter("id");
        int id = Integer.parseInt(str_id);

        // Recebe os parâmetros 'nome' e 'descricao' da categoria do produto a ser alterada
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");

        // Cria um novo objeto CategoriaProduto com os dados recebidos
        CategoriaProduto categoriaProduto = new CategoriaProduto(nome, descricao);

        // Instancia a classe DAO para acessar métodos de manipulação de dados de CategoriaProduto
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

        // Chama o método alterarCategoriaProduto para atualizar a categoria no banco de dados
        int num = categoriaProdutoDAO.alterarCategoriaProduto(id, categoriaProduto);

        // Define o resultado da operação na requisição para controle de fluxo e mensagens
        if (num == 1) {
            req.setAttribute("retorno", "certo"); // Alteração bem-sucedida
        } else if (num == 0) {
            req.setAttribute("retorno", "notfound"); // Categoria não encontrada
        } else {
            req.setAttribute("retorno", "erro"); // Erro ao atualizar
        }

        // Define o método e a entidade para exibir informações no JSP de resposta
        req.setAttribute("metodo", "ALTERAR");
        req.setAttribute("entidade", nome);

        // Encaminha a requisição para o JSP de listagem de categorias
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/categoriaProduto/listagemCategoriaProdutos.jsp");
        rd.forward(req, resp);
    }
}
