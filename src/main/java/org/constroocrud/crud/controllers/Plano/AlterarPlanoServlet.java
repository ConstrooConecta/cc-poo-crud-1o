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
        out.println(str_id);

        String name = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String strDuracao = req.getParameter("duracao");
        String strValor = req.getParameter("valor");
        String tipo = req.getParameter("tipo");

        double valor = Double.parseDouble(strValor);
        int duracao = Integer.parseInt(strDuracao);

        PlanoDAO planoDAO = new PlanoDAO();
        Plano plano = new Plano(tipo, valor, descricao, name,duracao);

        int num = planoDAO.alterarPlano(id, plano);
        if (num == 1){
            out.println("Plano alterado");
        } else if (num == 0) {
            out.println("Plano nao alterado");
        } else {
            out.println("Erro");
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemPlanos.jsp");
        rd.include(req, resp);





    }

}
