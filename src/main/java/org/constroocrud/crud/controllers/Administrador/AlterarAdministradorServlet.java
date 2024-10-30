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

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AlterarAdministradorServlet", value = "/AlterarAdministradorServlet")
public class AlterarAdministradorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //Recebe o id da entidade comprador/vendedor ou Profissional

        String str_id = req.getParameter("id");
        int id = Integer.parseInt(str_id);

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        String errosMensagem = "";
        if (email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$") && senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$")){
            String hash = BCrypt.hashpw(senha, BCrypt.gensalt());

            Administrador administrador = new Administrador(nome, email, hash);
            AdministradorDAO administradorDAO = new AdministradorDAO();

            int num = administradorDAO.alterarAdministrador(id, administrador);
            if (num == 1){
                req.setAttribute("retorno", "certo");
            }else if (num == 0){
                req.setAttribute("retorno", "notfound");
            }else {
                req.setAttribute("retorno", "erro");
            }

            req.setAttribute("metodo", "ALTERAR");
            req.setAttribute("entidade", nome);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/administrador/listagemAdministradores.jsp");
            rd.include(req, resp);
        }else {
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
                errosMensagem+="| E-mail inválido |";
            }
            if (!senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$\n")){
                errosMensagem+="| Senha inválida (É necessário ter de 8 a 20 caracteres, uma letra maiúscula e minúscula e um número) |";
            }
            RequestDispatcher rd;
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", errosMensagem);
            req.setAttribute("id", id);
            req.setAttribute("nome", nome);
            req.setAttribute("senha", senha);
            req.setAttribute("email", email);
            rd = getServletContext().getRequestDispatcher("/pages/administrador/alterarAdministrador.jsp");
            rd.include(req, resp);
        }






    }

}
