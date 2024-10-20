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
        int id = Integer.parseInt(str_id);
        out.println(id);
        AdministradorDAO administradorDAO = new AdministradorDAO();

        ResultSet resultSet = administradorDAO.buscarAdministradorPeloID(id);

        try {
            if (!resultSet.next()){
                req.setAttribute("retorno", "notfound");
                req.setAttribute("mensagem", "Admin não encontrado!");
                req.getRequestDispatcher("pages/alterarAdministradorPeloID.jsp").forward(req, resp);
            }else{

                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
                req.setAttribute("id", id);
                req.setAttribute("nome", nome);
                req.setAttribute("senha", senha);
                req.setAttribute("email", email);
                req.getRequestDispatcher("pages/alterarCamposAdministrador.jsp").forward(req, resp);
            }



        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");
        }

        // Define atributos para serem usados na página de alteração


        // Redireciona para a página de alteração do administrador

    }
}
