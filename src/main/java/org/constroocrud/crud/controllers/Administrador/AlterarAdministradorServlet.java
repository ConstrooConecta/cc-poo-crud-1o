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
        PrintWriter out = resp.getWriter(); // Cria o PrintWriter para resposta

        // Recebe o ID do administrador a ser alterado, fornecido como String e convertido para int
        String str_id = req.getParameter("id");
        int id = Integer.parseInt(str_id);

        // Recebe os demais parâmetros enviados pelo formulário de alteração
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        String errosMensagem = ""; // Armazena mensagens de erro para validação dos dados

        // Validação do formato do email e da senha usando expressões regulares
        if (email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$") &&
                senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$")) {

            // Gera um hash seguro da senha utilizando BCrypt
            String hash = BCrypt.hashpw(senha, BCrypt.gensalt());

            // Cria uma instância de Administrador com os dados atualizados
            Administrador administrador = new Administrador(nome, email, hash);
            AdministradorDAO administradorDAO = new AdministradorDAO();

            // Chama o método para atualizar o administrador no banco de dados
            int num = administradorDAO.alterarAdministrador(id, administrador);

            // Define mensagens de retorno com base no resultado da operação
            if (num == 1) {
                req.setAttribute("retorno", "certo");
            } else if (num == 0) {
                req.setAttribute("retorno", "notfound");
            } else {
                req.setAttribute("retorno", "erro");
            }

            // Define atributos adicionais para exibição na próxima página e encaminha para listagem
            req.setAttribute("metodo", "ALTERAR");
            req.setAttribute("entidade", nome);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/administrador/listagemAdministradores.jsp");
            rd.include(req, resp);

        } else {
            // Caso o email ou a senha estejam no formato inválido, cria mensagem de erro específica
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                errosMensagem += "| E-mail inválido |";
            }
            if (!senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$")) {
                errosMensagem += "| Senha inválida (É necessário ter de 8 a 20 caracteres, uma letra maiúscula e minúscula e um número) |";
            }

            // Reatribui os valores e mensagens ao request para manter os dados na página
            RequestDispatcher rd;
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", errosMensagem);
            req.setAttribute("id", id);
            req.setAttribute("nome", nome);
            req.setAttribute("senha", senha);
            req.setAttribute("email", email);
            rd = getServletContext().getRequestDispatcher("/pages/administrador/alterarAdministrador.jsp");
            rd.include(req, resp); // Redireciona para a página de alteração mantendo as informações e mensagens de erro
        }
    }
}
