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
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe os dados do administrador a ser alterado
        String str_id = req.getParameter("administrador_id");
        int id = 0;

        try{
            id = Integer.parseInt(str_id);
        }catch (NumberFormatException numberFormatException){
            // Define atributos para serem usados na página de alteração
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Admin não encontrado!");

            // Redireciona para a página de alteração do administrador
            req.getRequestDispatcher("/pages/administrador/alterarAdministradorPeloID.jsp").forward(req, resp);
        }finally {
            AdministradorDAO administradorDAO = new AdministradorDAO();

            ResultSet resultSet = administradorDAO.buscarAdministradorPeloID(id);

            try {
                if (!resultSet.next()){
                    // Define atributos para serem usados na página de alteração
                    req.setAttribute("retorno", "notfound");
                    req.setAttribute("mensagem", "Admin não encontrado!");
                }else{

                    String nome = resultSet.getString("nome");
                    String senha = resultSet.getString("senha");
                    String email = resultSet.getString("email");

                    // Define atributos para serem usados na página de alteração
                    req.setAttribute("id", id);
                    req.setAttribute("nome", nome);
                    req.setAttribute("senha", senha);
                    req.setAttribute("email", email);
                    // Redireciona para a página de alteração do administrador
                    req.getRequestDispatcher("/pages/administrador/alterarCamposAdministrador.jsp").forward(req, resp);
                }
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
                // Define atributos para serem usados na página de alteração
                req.setAttribute("retorno", "erro");
                req.setAttribute("mensagem", "Erro SQL");
            }finally{
                // Redireciona para a página de alteração do administrador
                req.getRequestDispatcher("/pages/administrador/alterarAdministradorPeloID.jsp").forward(req, resp);
            }
        }
    }
}
