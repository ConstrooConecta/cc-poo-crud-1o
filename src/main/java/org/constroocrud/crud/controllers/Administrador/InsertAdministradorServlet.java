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
import java.sql.ResultSet;
import java.sql.SQLException;

// Este servlet é responsável por realizar o cadastro de um novo administrador, verificando a existência do email e validando os campos.

@WebServlet(name = "InsertAdministradorServlet", value = "/InserirAdministradorServlet")
public class InsertAdministradorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configuração do tipo de conteúdo e criação de PrintWriter para resposta
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Inicializa variáveis para mensagens de erro e DAO do administrador
        String errosMensagem = "";
        AdministradorDAO administradorDAO = new AdministradorDAO();

        // Recebe os dados do formulário
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        // Gera o hash da senha para armazenar de forma segura
        String hashedPassword = BCrypt.hashpw(senha, BCrypt.gensalt());

        // Cria um objeto Administrador com os dados fornecidos
        Administrador administrador = new Administrador(nome, email, hashedPassword);

        // Verifica a validade do email e da senha usando expressões regulares
        if (email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")
                && senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$")) {
            try {
                // Verifica se já existe um administrador com o email fornecido
                ResultSet rs = administradorDAO.buscarAdministradorPeloEmail(email);

                if (!rs.next()) { // Caso o email não exista, procede com a inserção
                    int num = administradorDAO.inserirAdministrador(administrador);

                    // Define o resultado da operação com base na resposta da DAO
                    if (num == 1) {
                        req.setAttribute("retorno", "certo");
                    } else if (num == 0) {
                        req.setAttribute("retorno", "notfound");
                    } else {
                        req.setAttribute("retorno", "erro");
                    }
                } else {
                    req.setAttribute("retorno", "existente");  // Email já existe
                }
            } catch (SQLException e) {
                throw new RuntimeException(e); // Lança exceção em caso de erro SQL
            }

            // Define atributos para exibir o resultado e redireciona para a página de listagem
            req.setAttribute("metodo", "INSERIR");
            req.setAttribute("entidade", nome);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/administrador/listagemAdministradores.jsp");
            rd.include(req, resp);
        } else {
            // Gera mensagens de erro em caso de email ou senha inválidos
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                errosMensagem += "| E-mail inválido |";
            }
            if (!senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$")) {
                errosMensagem += "| Senha inválida (É necessário ter de 8 a 20 caracteres, uma letra maiúscula e minúscula e um número) |";
            }

            // Redireciona para a página de cadastro com mensagens de erro
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/administrador/cadastrarAdministrador.jsp");
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", errosMensagem);
            rd.include(req, resp);
        }
    }
}
