package org.constroocrud.crud.controllers.Deletes;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.TagServicoDAO;

import java.io.IOException;
import java.io.PrintWriter;
//SERVLET QUE FAZ O DELET DE USUARIOS
//O que precisa ser implementado?

//1. Por enquanto este servlet apenas deleta o registro na tabela Comprador_vendedor ou Profissional, sendo que é preciso deletar da tabela usuarios também, caso nao exista nenhum registro nem nos profissionais nem nos compradores vendedores


@WebServlet(name = "DeleteTagServicoServlet", value = "/DeletarTagServicoServlet")
public class DeleteTagServicoServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("tag_id");
        TagServicoDAO tagServicoDAO = new TagServicoDAO();
        int num = tagServicoDAO.removerTagServicoPeloID(Integer.parseInt(id));
        if (num == 1){
            out.println("Tag Serviço removido");
        }else if (num == 0){
            out.println("Tag Serviço não removido");
        }else {
            out.println("Erro");
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemTagServico.jsp");
        rd.include(req, resp);



    }
}
