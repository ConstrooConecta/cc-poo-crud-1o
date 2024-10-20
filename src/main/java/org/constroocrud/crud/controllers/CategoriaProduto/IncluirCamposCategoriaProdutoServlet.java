package org.constroocrud.crud.controllers.CategoriaProduto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;
import org.constroocrud.crud.DAOs.CategoriaProdutoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "IncluirCamposCategoriaProdutoServlet", value = "/IncluirCamposCategoriaProdutoServlet")
public class IncluirCamposCategoriaProdutoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe os dados do administrador a ser alterado
        String str_id = req.getParameter("categoria_id");
        int id = Integer.parseInt(str_id);
        out.println(id);
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

        ResultSet resultSet = categoriaProdutoDAO.buscarCategoriaProdutoPeloID(id);

        try {
            if (!resultSet.next()){
                req.setAttribute("retorno", "notfound");
                req.setAttribute("mensagem", "Admin não encontrado!");
                req.getRequestDispatcher("pages/alterarCategoriaProdutoPeloID.jsp").forward(req, resp);
            }else{

                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");

                req.setAttribute("id", id);
                req.setAttribute("nome", nome);
                req.setAttribute("descricao", descricao);

                req.getRequestDispatcher("pages/alterarCamposCategoriaProduto.jsp").forward(req, resp);
            }



        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");
        }

        // Define atributos para serem usados na página de alteração


        // Redireciona para a página de alteração do administrador

    }
}
