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

@WebServlet(name = "InsertPlanoServlet", value = "/InserirPlanoServlet")
public class InsertPlanoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        PlanoDAO planoDAO = new PlanoDAO();

        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String strDuracao = req.getParameter("duracao");
        String tipo = req.getParameter("tipo");
        String strValor = req.getParameter("valor");
        double valor = Double.parseDouble(strValor);
        int duracao = Integer.parseInt(strDuracao);

        Plano plano = new Plano(tipo,valor,descricao,nome,duracao);

        int num = planoDAO.inserirPlano(plano);
        if (num == 1){
            out.println("Plano inserido");
        }else if (num == 0){
            out.println("Plano não deu certo");
        }else {
            out.println("Erro");
        }

        //Voce é direcionado para a listagem de usuarios!
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemTagServico.jsp");
        rd.include(req, resp);




    }
}
