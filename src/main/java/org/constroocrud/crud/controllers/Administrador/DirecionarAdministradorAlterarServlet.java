package org.constroocrud.crud.controllers.Administrador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// Este servlet é responsável por redirecionar para a página de alteração de dados de um administrador,
// utilizando os dados fornecidos na requisição para preencher os campos na página de edição.

@WebServlet(name = "DirecionarAdministradorAlterarServlet", value = "/DirecionarAdministradorAlterarServlet")
public class DirecionarAdministradorAlterarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configura o tipo de resposta para HTML e cria um PrintWriter para depuração
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe os dados do administrador que será alterado
        String str_id = req.getParameter("administrador_id");
        String nome = req.getParameter("nome");
        String senha = req.getParameter("senha");
        String email = req.getParameter("email");
        int id = Integer.parseInt(str_id); // Converte o ID para um tipo inteiro
        out.println(id); // Exibe o ID no response para fins de depuração

        // Define atributos para serem utilizados na página de alteração
        req.setAttribute("id", id);
        req.setAttribute("nome", nome);
        req.setAttribute("senha", senha);
        req.setAttribute("email", email);

        // Redireciona para a página de alteração do administrador, onde os dados serão exibidos para edição
        req.getRequestDispatcher("/pages/administrador/alterarAdministrador.jsp").forward(req, resp);
    }
}
