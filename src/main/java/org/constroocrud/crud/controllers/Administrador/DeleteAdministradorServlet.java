package org.constroocrud.crud.controllers.Administrador;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        try {
            ResultSet rs = administradorDAO.buscarAdministradorPeloID(Integer.parseInt(id));
            if (!rs.next()){
                req.setAttribute("retorno", "notfound");
                req.setAttribute("metodo", "DELETAR");
                req.setAttribute("entidade", id);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/pages/administrador/listagemAdministradores.jsp");
                rd.include(req, resp);
            }else{
                String nome = rs.getString("nome");

                int num = administradorDAO.removerAdministrador(Integer.parseInt(id));

                if (num == 1){
                    req.setAttribute("retorno", "certo");
                }else if (num == 0){
                    req.setAttribute("retorno", "notfound");
                }else {
                    req.setAttribute("retorno", "erro");
                }

                req.setAttribute("metodo", "DELETAR");
                req.setAttribute("entidade", nome);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/pages/administrador/listagemAdministradores.jsp");
                rd.include(req, resp);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/administrador/listagemAdministradores.jsp");
            rd.include(req, resp);

        }
    }
}
