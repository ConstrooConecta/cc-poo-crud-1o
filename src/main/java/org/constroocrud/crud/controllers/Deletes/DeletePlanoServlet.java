package org.constroocrud.crud.controllers.Deletes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoDAO;

import java.io.IOException;
import java.io.PrintWriter;

// Servlet mapeada para "/DeletarPlanoServlet"
@WebServlet(name = "DeletePlanoServlet", value = "/DeletarPlanoServlet")
public class DeletePlanoServlet extends HttpServlet {

    // Método que trata requisições POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o ID do plano a ser deletado
        String id = req.getParameter("plano_id");
        PlanoDAO planoDAO = new PlanoDAO();

        // Verifica se a remoção foi bem-sucedida
        if (planoDAO.removerPlanoPeloID(Integer.parseInt(id))) {
            System.out.println("Deu certo");
        } else {
            System.out.println("Nao deu certo");
        }

        // Redireciona para a página de listagem de planos
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/listagemPlanos.jsp");
        rd.include(req, resp);
    }
}