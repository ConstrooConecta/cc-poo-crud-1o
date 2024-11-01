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
        // Configura o tipo de resposta como HTML e cria um PrintWriter para escrever no response
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Obtém o ID do administrador a ser deletado do parâmetro "administrador_id" da requisição
        String id = req.getParameter("administrador_id");

        // Instancia o AdministradorDAO para acessar os métodos de manipulação do banco de dados
        AdministradorDAO administradorDAO = new AdministradorDAO();

        try {
            // Busca o administrador pelo ID para verificar se existe na base de dados
            ResultSet rs = administradorDAO.buscarAdministradorPeloID(Integer.parseInt(id));

            if (!rs.next()) {
                // Caso o administrador não seja encontrado, configura atributos para notificar o usuário
                req.setAttribute("retorno", "notfound");
                req.setAttribute("metodo", "DELETAR");
                req.setAttribute("entidade", id);

                // Redireciona para a página de listagem com a notificação de "não encontrado"
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/administrador/listagemAdministradores.jsp");
                rd.include(req, resp);
            } else {
                // Caso o administrador seja encontrado, obtém o nome do administrador para exibir na notificação
                String nome = rs.getString("nome");

                // Chama o método para remover o administrador e verifica o resultado da operação
                int num = administradorDAO.removerAdministrador(Integer.parseInt(id));

                if (num == 1) {
                    // Configura os atributos caso a exclusão seja bem-sucedida
                    req.setAttribute("retorno", "certo");
                } else if (num == 0) {
                    // Configura os atributos caso o administrador não seja encontrado (inconsistência de dados)
                    req.setAttribute("retorno", "notfound");
                } else {
                    // Configura os atributos caso ocorra um erro de execução
                    req.setAttribute("retorno", "erro");
                }

                // Atribui informações sobre a ação realizada para exibir na resposta
                req.setAttribute("metodo", "DELETAR");
                req.setAttribute("entidade", nome);

                // Redireciona para a página de listagem de administradores
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/administrador/listagemAdministradores.jsp");
                rd.include(req, resp);
            }
        } catch (SQLException sqlException) {
            // Captura exceções SQL e configura os atributos de erro para exibir a mensagem
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");

            // Redireciona para a página de listagem com a mensagem de erro SQL
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/administrador/listagemAdministradores.jsp");
            rd.include(req, resp);
        }
    }
}
