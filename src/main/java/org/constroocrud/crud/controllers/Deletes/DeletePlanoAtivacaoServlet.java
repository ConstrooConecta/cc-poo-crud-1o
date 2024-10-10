package org.constroocrud.crud.controllers.Deletes;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoAtivacaoDAO;


import java.io.IOException;
import java.io.PrintWriter;
//SERVLET QUE FAZ O DELET DE USUARIOS
//O que precisa ser implementado?

//1. Por enquanto este servlet apenas deleta o registro na tabela Comprador_vendedor ou Profissional, sendo que é preciso deletar da tabela usuarios também, caso nao exista nenhum registro nem nos profissionais nem nos compradores vendedores


@WebServlet(name = "DeletePlanoAtivacaoServlet", value = "/DeletarPlanoAtivacaoServlet")
public class DeletePlanoAtivacaoServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //Recebe o id da entidade comprador/vendedor ou Profissional

        String str_id_tipo = req.getParameter("id_planoativacao");
        int id_tipo = Integer.parseInt(str_id_tipo);

        out.println(id_tipo);

        //Estabelece a conexao
        PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();

        //caso seja comprador e vendedor, ele é removido da tabela comprador/vendedor

        if (planoAtivacaoDAO.removerPlanoAtivacao(id_tipo)){
            out.println("removido");

            //caso seja profissional, ele é removido da tabela profissional
        }

        //Voce é direcionado para a listagem de usuarios!
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/listagemUsuarios.jsp");
        rd.include(req, resp);



    }
}
