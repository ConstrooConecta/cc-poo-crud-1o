package org.constroocrud.crud.controllers.Plano;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
//SERVLET QUE FAZ O DELET DE USUARIOS
//O que precisa ser implementado?

//1. Por enquanto este servlet apenas deleta o registro na tabela Comprador_vendedor ou Profissional, sendo que é preciso deletar da tabela usuarios também, caso nao exista nenhum registro nem nos profissionais nem nos compradores vendedores


@WebServlet(name = "DeletePlanoServlet", value = "/DeletarPlanoServlet")
public class DeletePlanoServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.setAttribute("metodo", "DELETAR");

        String str_id = req.getParameter("plano_id");
        int id = 0;

        try {
            id = Integer.parseInt(str_id);
        }catch (NumberFormatException numberFormatException) {
            req.setAttribute("retorno", "notfound");
            req.getRequestDispatcher("/pages/plano/deletarPlanoPeloID.jsp").forward(req, resp);
        }finally {
            PlanoDAO planoDAO = new PlanoDAO();

            try {
                ResultSet rs = planoDAO.buscarPlanoPeloID(id);
                if (!rs.next()) {
                    req.setAttribute("retorno", "notfound");
                    req.setAttribute("entidade", id);

                    req.getRequestDispatcher("/pages/plano/deletarPlanoPeloID.jsp").forward(req, resp);

                } else {

                    String nome = rs.getString("nome_plano");

                    int num = planoDAO.removerPlanoPeloID(id);

                    if (num == 1) {
                        req.setAttribute("retorno", "certo");
                    } else {
                        req.setAttribute("retorno", "erro");
                    }

                    req.setAttribute("entidade", nome);

                    req.getRequestDispatcher("/pages/plano/listagemPlanos.jsp").forward(req, resp);
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                req.setAttribute("retorno", "erro");
                req.setAttribute("mensagem", "Erro SQL");

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/pages/plano/listagemPlanos.jsp");
                rd.include(req, resp);

            }
        }
    }
}
