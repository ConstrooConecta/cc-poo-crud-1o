package org.constroocrud.crud.controllers.Administrador;

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

@WebServlet(name = "IncluirCamposAdministradorServlet", value = "/IncluirCamposAdministradorServlet")
public class IncluirCamposAdministradorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configura o tipo de resposta como HTML e inicializa o PrintWriter para a resposta
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o ID do administrador como String e converte para int
        String str_id = req.getParameter("administrador_id");
        int id = 0;

        try {
            // Tenta converter o ID para int
            id = Integer.parseInt(str_id);
        } catch (NumberFormatException numberFormatException) {
            // Caso ocorra erro na conversão do ID, define mensagem de erro e redireciona
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Admin não encontrado!");
            req.getRequestDispatcher("/pages/administrador/alterarAdministradorPeloID.jsp").forward(req, resp);
        }

        // Cria uma instância do DAO para buscar dados do administrador
        AdministradorDAO administradorDAO = new AdministradorDAO();
        ResultSet resultSet = administradorDAO.buscarAdministradorPeloID(id);

        try {
            // Verifica se o administrador foi encontrado no banco
            if (!resultSet.next()) {
                // Caso não encontre o administrador, define mensagem de erro
                req.setAttribute("retorno", "notfound");
                req.setAttribute("mensagem", "Admin não encontrado!");
            } else {
                // Se o administrador foi encontrado, obtém os dados para exibição
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");

                // Define os atributos para exibição na página de alteração
                req.setAttribute("id", id);
                req.setAttribute("nome", nome);
                req.setAttribute("senha", senha);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/administrador/alterarCamposAdministrador.jsp").forward(req, resp);

            }
        } catch (SQLException sqlException) {
            // Em caso de erro SQL, define uma mensagem de erro e imprime o stack trace
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");
        } finally {
            // Redireciona para a página de alteração do administrador com os atributos configurados
            req.getRequestDispatcher("/pages/administrador/alterarAdministradorPeloID.jsp").forward(req, resp);
        }
    }
}
