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
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");

        CategoriaProduto categoriaProduto = new CategoriaProduto(nome,descricao);

        try {
            ResultSet rs = categoriaProdutoDAO.buscarCategoriaProdutoPeloNome(nome);
            if (!rs.next()){  // Verifica se não existe uma categoria com o nome
                int num = categoriaProdutoDAO.inserirCategoriaProduto(categoriaProduto);
                if (num == 1){
                    req.setAttribute("retorno", "certo");
                }else if (num == 0){
                    req.setAttribute("retorno", "notfound");
                }else {
                    req.setAttribute("retorno", "erro");
                }
            }else {
                req.setAttribute("retorno", "existente");  // Categoria já existe
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        req.setAttribute("metodo", "INSERIR");
        req.setAttribute("entidade", nome);


        //Voce é direcionado para a listagem de usuarios!
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemCategoriaProdutos.jsp");
        rd.include(req, resp);




    }
}
