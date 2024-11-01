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
        int id_tipo = 0;

        req.setAttribute("metodo", "ALTERAR");

        try{
            id_tipo = Integer.parseInt(str_id_tipo);
        }catch (NumberFormatException e){
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Plano Ativação não encontrado!");

            // Redireciona para a página de alteração do administrador
            req.getRequestDispatcher("../pages/planoAtivacao/alterarPlanoAtivacaoPeloID.jsp").forward(req, resp);
        }finally {
            //Estabelece a conexao
            PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();

            req.setAttribute("entidade", id_tipo);

            //caso esteja na tabela Plano Ativação, a coluna ativação será alterada
            int num = planoAtivacaoDAO.alterarAtivacao(id_tipo);
            if (num == 1){
                req.setAttribute("retorno", "certo");
                req.getRequestDispatcher("/pages/planoAtivacao/listagemPlanosAtivacao.jsp").forward(req, resp);
            }else if (num == 0){
                req.setAttribute("retorno", "notfound");
                req.setAttribute("mensagem", "Plano Ativação não encontrado!");
                req.getRequestDispatcher("/pages/planoAtivacao/alterarPlanoAtivacaoPeloID.jsp").forward(req, resp);
            }else {
                req.setAttribute("retorno", "erro");
                req.setAttribute("mensagem", "Erro");
                req.getRequestDispatcher("/pages/planoAtivacao/listagemPlanosAtivacao.jsp").forward(req, resp);

            }

        }

    }
}
