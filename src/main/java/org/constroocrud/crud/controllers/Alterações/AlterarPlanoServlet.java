package org.constroocrud.crud.controllers.Alterações;

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

// Servlet mapeada para "/AlterarPlanoServlet"
@WebServlet(name = "AlterarPlanoServlet", value = "/AlterarPlanoServlet")
public class AlterarPlanoServlet extends HttpServlet {

    // Método que trata requisições POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe parâmetros da requisição e converte para tipos apropriados
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

        // Instancia DAO e cria objeto Plano
        PlanoDAO planoDAO = new PlanoDAO();
        Plano plano = new Plano(tipo, valor, descricao, name, duracao);

        // Altera o plano no banco e exibe o resultado
        out.println(planoDAO.alterarPlano(id, plano));

        // Redireciona para a página de listagem de planos
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/listagemPlanos.jsp");
        rd.include(req, resp);
    }
}