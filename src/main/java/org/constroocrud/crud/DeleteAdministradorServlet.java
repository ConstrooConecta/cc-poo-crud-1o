package org.constroocrud.crud;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;
import org.constroocrud.crud.DAOs.CategoriaProdutoDAO;

import java.io.IOException;
import java.io.PrintWriter;
//SERVLET QUE FAZ O DELET DE USUARIOS
//O que precisa ser implementado?

//1. Por enquanto este servlet apenas deleta o registro na tabela Comprador_vendedor ou Profissional, sendo que é preciso deletar da tabela usuarios também, caso nao exista nenhum registro nem nos profissionais nem nos compradores vendedores


@WebServlet(name = "DeleteAdministradorServlet", value = "/DeletarAdministradorServlet")
public class DeleteAdministradorServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("administrador_id");
        AdministradorDAO administradorDAO = new AdministradorDAO();
        if (administradorDAO.removerAdministrador(Integer.parseInt(id))){
            System.out.println("Deu certo");
        }else{
            System.out.println("Nao deu certo");
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/listagemAdministradores.jsp");
        rd.include(req, resp);



    }
}
