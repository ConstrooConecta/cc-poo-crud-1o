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

// SERVLET QUE FAZ O DELET DE PLANOS
// O que precisa ser implementado?
// 1. Este servlet deleta o registro na tabela de planos.
// Caso não exista o registro, retorna uma mensagem apropriada.

@WebServlet(name = "DeletePlanoServlet", value = "/DeletarPlanoServlet")
public class DeletePlanoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.setAttribute("metodo", "DELETAR");
      
        // Recebe o ID do plano a ser deletado
        String str_id = req.getParameter("plano_id");
        int id = Integer.parseInt(str_id);
        out.println(id); // Imprime o ID para debug

        String str_id = req.getParameter("plano_id");
        int id = 0;

        try {
            id = Integer.parseInt(str_id);
        }catch (NumberFormatException numberFormatException) {
            req.setAttribute("retorno", "notfound");
            req.getRequestDispatcher("/pages/plano/deletarPlanoPeloID.jsp").forward(req, resp);
        }finally {
            PlanoDAO planoDAO = new PlanoDAO();

            try {
                ResultSet rs = planoDAO.buscarPlanoPeloID(id);
                if (!rs.next()) {
                    req.setAttribute("retorno", "notfound");
                    req.setAttribute("entidade", id);

                    req.getRequestDispatcher("/pages/plano/deletarPlanoPeloID.jsp").forward(req, resp);

                } else {

                    String nome = rs.getString("nome_plano");

            // Busca o plano pelo ID
            ResultSet rs = planoDAO.buscarPlanoPeloID(id);
            if (!rs.next()) {
                // Se o plano não for encontrado, define atributos de retorno e redireciona
                req.setAttribute("retorno", "notfound");
                req.setAttribute("metodo", "DELETAR");
                req.setAttribute("entidade", id);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/plano/listagemPlanos.jsp");
                rd.include(req, resp);
            } else {
                // Se o plano for encontrado, obtém o nome do plano
                String nome = rs.getString("nome_plano");

                // Tenta remover o plano pelo ID
                int num = planoDAO.removerPlanoPeloID(id);

                // Define os atributos de retorno baseados no resultado da remoção
                if (num == 1) {
                    req.setAttribute("retorno", "certo"); // Sucesso
                } else if (num == 0) {
                    req.setAttribute("retorno", "notfound"); // Plano não encontrado
                } else {
                    req.setAttribute("retorno", "erro"); // Erro durante a remoção
                }

                    int num = planoDAO.removerPlanoPeloID(id);

                    if (num == 1) {
                        req.setAttribute("retorno", "certo");
                    } else {
                        req.setAttribute("retorno", "erro");
                    }

                    req.setAttribute("entidade", nome);

                    req.getRequestDispatcher("/pages/plano/listagemPlanos.jsp").forward(req, resp);
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                req.setAttribute("retorno", "erro");
                req.setAttribute("mensagem", "Erro SQL");

                // Redireciona para a página de listagem de planos
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/plano/listagemPlanos.jsp");
                rd.include(req, resp);

            }

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // Imprime o erro no console
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL"); // Mensagem de erro SQL

            // Redireciona para a página de listagem de planos
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/plano/listagemPlanos.jsp");
            rd.include(req, resp);
        }
    }
}
