package org.constroocrud.crud.controllers.Plano;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

// SERVLET QUE REALIZA A EXCLUSÃO DE PLANOS
// Implementação:
// 1. Este servlet realiza a exclusão de um plano na tabela de planos.
// 2. Se o registro não for encontrado, retorna uma mensagem apropriada.

@WebServlet(name = "DeletePlanoServlet", value = "/DeletarPlanoServlet")
public class DeletePlanoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configura o tipo de resposta como HTML e inicializa o PrintWriter
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Atributo para diferenciar o método no contexto da operação
        req.setAttribute("metodo", "DELETAR");

        // Recebe o ID do plano e tenta convertê-lo para inteiro
        String str_id = req.getParameter("plano_id");
        int id = 0;

        try {
            id = Integer.parseInt(str_id);
        } catch (NumberFormatException numberFormatException) {
            // Se a conversão falhar, define o retorno como "notfound" e redireciona para a página de exclusão
            req.setAttribute("retorno", "notfound");
            req.getRequestDispatcher("/pages/plano/deletarPlanoPeloID.jsp").forward(req, resp);
            return;
        }

        // Instancia o DAO para operações com o banco de dados
        PlanoDAO planoDAO = new PlanoDAO();

        try {
            // Busca o plano pelo ID para verificar se existe
            ResultSet rs = planoDAO.buscarPlanoPeloID(id);
            if (!rs.next()) {
                // Se o plano não for encontrado, configura a resposta como "notfound" e redireciona
                req.setAttribute("retorno", "notfound");
                req.setAttribute("entidade", id);
                req.getRequestDispatcher("/pages/plano/deletarPlanoPeloID.jsp").forward(req, resp);
            } else {
                // Se o plano for encontrado, captura o nome para exibição futura
                String nome = rs.getString("nome_plano");

                // Tenta remover o plano pelo ID e configura o retorno com base no resultado
                int num = planoDAO.removerPlanoPeloID(id);
                if (num == 1) {
                    req.setAttribute("retorno", "certo");
                } else {
                    req.setAttribute("retorno", "erro");
                }

                // Define o nome do plano removido e redireciona para a listagem de planos
                req.setAttribute("entidade", nome);
                req.getRequestDispatcher("/pages/plano/listagemPlanos.jsp").forward(req, resp);
            }
        } catch (SQLException sqlException) {
            // Em caso de erro SQL, configura a mensagem de erro e redireciona para a listagem de planos
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/plano/listagemPlanos.jsp");
            rd.include(req, resp);
        }
    }
}
