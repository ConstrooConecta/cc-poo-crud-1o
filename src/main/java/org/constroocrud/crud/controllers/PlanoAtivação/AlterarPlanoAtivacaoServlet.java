package org.constroocrud.crud.controllers.PlanoAtivação;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoAtivacaoDAO;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AlterarPlanoAtivacaoServlet", value = "/AlterarPlanoAtivacaoServlet")
public class AlterarPlanoAtivacaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //Recebe o id do PLano Ativação

        String str_id_tipo = req.getParameter("id_planoativacao");
        int id_tipo = Integer.parseInt(str_id_tipo);
        out.println(id_tipo);

        //Estabelece a conexao
        PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();

        //caso esteja na tabela Plano Ativação, a coluna ativação será alterada

        int num = planoAtivacaoDAO.alterarAtivacao(id_tipo);
        if (num == 1){
            req.setAttribute("retorno", "certo");
        }else if (num == 0){
            req.setAttribute("retorno", "notfound");
        }else {
            req.setAttribute("retorno", "erro");
        }

        req.setAttribute("metodo", "ALTERAR");
        req.setAttribute("entidade", id_tipo);

        //Voce é direcionado para a listagem de Planos de Ativação!
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemPlanosAtivacao.jsp");
        rd.forward(req, resp);

    }
}
