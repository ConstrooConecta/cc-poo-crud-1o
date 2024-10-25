package org.constroocrud.crud.controllers.Plano;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoDAO;
import org.constroocrud.crud.models.Plano;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AlterarPlanoServlet", value = "/AlterarPlanoServlet")
public class AlterarPlanoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //Recebe o id da entidade comprador/vendedor ou Profissional

        String str_id = req.getParameter("id");
        int id = Integer.parseInt(str_id);

        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String strDuracao = req.getParameter("duracao");
        String strValor = req.getParameter("valor");
        String tipo = req.getParameter("tipo");

        double valor = Double.parseDouble(strValor);
        int duracao = Integer.parseInt(strDuracao);

        PlanoDAO planoDAO = new PlanoDAO();
        Plano plano = new Plano(tipo, valor, descricao, nome,duracao);

        int num = planoDAO.alterarPlano(id, plano);
        if (num == 1){
            req.setAttribute("retorno", "certo");
        }else if (num == 0){
            req.setAttribute("retorno", "notfound");
        }else {
            req.setAttribute("retorno", "erro");
        }

        req.setAttribute("metodo", "ALTERAR");
        req.setAttribute("entidade", nome);

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemPlanos.jsp");
        rd.include(req, resp);





    }

}
