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

        String str_id = req.getParameter("plano_id");
        int id = Integer.parseInt(str_id);
        out.println(id);

        PlanoDAO planoDAO = new PlanoDAO();

        try {
            ResultSet rs = planoDAO.buscarPlanoPeloID(id);
            if (!rs.next()){
                req.setAttribute("retorno", "notfound");
                req.setAttribute("metodo", "DELETAR");
                req.setAttribute("entidade", id);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/pages/listagemPlanos.jsp");
                rd.include(req, resp);
            }else{
                String nome = rs.getString("nome_plano");

                int num = planoDAO.removerPlanoPeloID(id);

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
                rd = getServletContext().getRequestDispatcher("/pages/listagemPlanos.jsp");
                rd.include(req, resp);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/listagemPlanos.jsp");
            rd.include(req, resp);

        }
    }
}
