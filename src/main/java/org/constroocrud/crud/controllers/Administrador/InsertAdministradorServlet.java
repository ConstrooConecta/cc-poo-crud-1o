package org.constroocrud.crud.controllers.Administrador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;
import org.constroocrud.crud.models.Administrador;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
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
        String errosMensagem = "";
        AdministradorDAO administradorDAO = new AdministradorDAO();

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        String hashedPassword = BCrypt.hashpw(senha, BCrypt.gensalt());

        Administrador administrador = new Administrador(nome, email, hashedPassword);

        if (email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$") && senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$")){
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
        }else{
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
                errosMensagem+="| E-mail inválido |";
            }
            if (!senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$\n")){
                errosMensagem+="| Senha inválida (É necessário ter de 8 a 20 caracteres, uma letra maiúscula e minúscula e um número) |";
            }
            RequestDispatcher rd;
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", errosMensagem);
            rd = getServletContext().getRequestDispatcher("/cadastros/cadastrarAdministrador.jsp");
            rd.include(req, resp);

        }

    }
}
