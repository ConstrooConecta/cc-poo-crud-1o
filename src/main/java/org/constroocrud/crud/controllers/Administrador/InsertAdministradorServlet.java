package org.constroocrud.crud.controllers.Administrador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;
import org.constroocrud.crud.models.Administrador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet(name = "InsertAdministradorServlet", value = "/InserirAdministradorServlet")
public class InsertAdministradorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        AdministradorDAO administradorDAO = new AdministradorDAO();

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        Administrador administrador = new Administrador(nome, email, senha);

        try {
            ResultSet rs = administradorDAO.buscarAdministradorPeloEmail(email);
            if (!rs.next()){
                int num = administradorDAO.inserirAdministrador(administrador);
                if (num == 1){
                    req.setAttribute("retorno", "certo");
                }else if (num == 0){
                    req.setAttribute("retorno", "notfound");
                }else {
                    req.setAttribute("retorno", "erro");
                }
            }else {
                req.setAttribute("retorno", "existente");  // Categoria já existe
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("metodo", "INSERIR");
        req.setAttribute("entidade", nome);

        //Voce é direcionado para a listagem de usuarios!
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemAdministradores.jsp");
        rd.include(req, resp);



    }
}
