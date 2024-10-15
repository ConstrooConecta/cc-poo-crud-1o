package org.constroocrud.crud.controllers.Deletes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;

import java.io.IOException;
import java.io.PrintWriter;

// Servlet mapeada para "/DeletarAdministradorServlet"
@WebServlet(name = "DeleteAdministradorServlet", value = "/DeletarAdministradorServlet")
public class DeleteAdministradorServlet extends HttpServlet {

    // Método que trata requisições POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o ID do administrador a ser deletado
        String id = req.getParameter("administrador_id");
        AdministradorDAO administradorDAO = new AdministradorDAO();

        // Verifica se a remoção foi bem-sucedida
        if (administradorDAO.removerAdministrador(Integer.parseInt(id))) {
            System.out.println("Deu certo");
        } else {
            System.out.println("Nao deu certo");
        }

        // Redireciona para a página de listagem de administradores
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/listagemAdministradores.jsp");
        rd.include(req, resp);
    }
}