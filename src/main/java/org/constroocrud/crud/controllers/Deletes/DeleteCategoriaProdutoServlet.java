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
//SERVLET QUE FAZ O DELET DE USUARIOS
//O que precisa ser implementado?

//1. Por enquanto este servlet apenas deleta o registro na tabela Comprador_vendedor ou Profissional, sendo que é preciso deletar da tabela usuarios também, caso nao exista nenhum registro nem nos profissionais nem nos compradores vendedores


@WebServlet(name = "DeleteCategoriaProdutoServlet", value = "/DeletarCategoriaProdutoServlet")
public class DeleteCategoriaProdutoServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("categoria_id");
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();
        int num = categoriaProdutoDAO.removerCategoriaProduto(Integer.parseInt(id));

        if (num == 1){
            out.println("Categoria Produto removido");
        }else if (num == 0){
            out.println("Categoria Produto não removido");
        }else {
            out.println("Erro");
        }


        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemCategoriaProdutos.jsp");
        rd.include(req, resp);



    }
}
