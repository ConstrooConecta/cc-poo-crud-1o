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
        // Configura o tipo de resposta como HTML e cria um PrintWriter para escrever na resposta
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Atribui o método de operação ao request para exibir na página
        req.setAttribute("metodo", "DELETAR");

        // Recebe o ID do administrador como String e inicializa a variável id como 0
        String str_id = req.getParameter("administrador_id");
        int id = 0;

        try {
            // Converte o ID do administrador para o tipo int
            id = Integer.parseInt(str_id);
        } catch (NumberFormatException numberFormatException) {
            // Em caso de erro de conversão, exibe mensagem de erro e redireciona para a página de deleção
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Tag Serviço não encontrado!");
            req.getRequestDispatcher("../pages/administrador/deletarAdministradorPeloID").forward(req, resp);
        } finally {
            // Cria uma instância de AdministradorDAO para realizar operações de banco de dados
            AdministradorDAO administradorDAO = new AdministradorDAO();

            try {
                // Tenta buscar o administrador pelo ID para verificar sua existência
                ResultSet rs = administradorDAO.buscarAdministradorPeloID(id);
                if (!rs.next()) {
                    // Caso não encontre o administrador, define mensagens de erro e redireciona
                    req.setAttribute("retorno", "notfound");
                    req.setAttribute("entidade", id);
                    req.getRequestDispatcher("/pages/administrador/deletarAdministradorPeloID.jsp").forward(req, resp);
                } else {
                    // Caso o administrador seja encontrado, armazena o nome para exibição
                    String nome = rs.getString("nome");

                    // Executa a remoção do administrador e recebe o resultado da operação
                    int num = administradorDAO.removerAdministrador(id);

                    // Define o retorno com base no resultado da operação de remoção
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
