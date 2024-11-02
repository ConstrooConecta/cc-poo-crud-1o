package org.constroocrud.crud.controllers.Administrador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "DeleteAdministradorServlet", value = "/DeletarAdministradorServlet")
public class DeleteAdministradorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configura o tipo de resposta como HTML e cria um PrintWriter para escrever no response
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.setAttribute("metodo", "DELETAR");

        String str_id = req.getParameter("administrador_id");
        int id = 0;

        try {
            id = Integer.parseInt(str_id);
        } catch (NumberFormatException numberFormatException) {
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Tag Serviço não encontrado!");
            req.getRequestDispatcher("../pages/administrador/deletarAdministradorPeloID").forward(req, resp);
        } finally {

            AdministradorDAO administradorDAO = new AdministradorDAO();

            try {
                ResultSet rs = administradorDAO.buscarAdministradorPeloID(id);
                if (!rs.next()) {
                    req.setAttribute("retorno", "notfound");
                    req.setAttribute("entidade", id);

                    req.getRequestDispatcher("/pages/administrador/deletarAdministradorPeloID.jsp").forward(req, resp);
                } else {
                    String nome = rs.getString("nome");

                    int num = administradorDAO.removerAdministrador(id);

                    if (num == 1) {
                        req.setAttribute("retorno", "certo");
                    } else {
                        req.setAttribute("retorno", "erro");
                    }

                    req.setAttribute("entidade", nome);

                    // Redireciona para a página de listagem de administradores
                    req.getRequestDispatcher("/pages/administrador/listagemAdministradores.jsp").forward(req, resp);
                }
            } catch (SQLException sqlException) {
                // Captura exceções SQL e configura os atributos de erro para exibir a mensagem
                req.setAttribute("retorno", "erro");
                req.setAttribute("mensagem", "Erro SQL");

                // Redireciona para a página de listagem com a mensagem de erro SQL
                req.getRequestDispatcher("/pages/administrador/listagemAdministradores.jsp").forward(req, resp);

            }
        }
    }
}
