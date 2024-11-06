package org.constroocrud.crud.controllers.TagServiço;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.TagServicoDAO;
import org.constroocrud.crud.models.TagServico;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AlterarTagServicoServlet", value = "/AlterarTagServicoServlet")
public class AlterarTagServicoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Define o tipo de resposta como HTML
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o id da tag de serviço que será alterada
        String str_id = req.getParameter("id");
        int id = Integer.parseInt(str_id); // Converte o id para inteiro

        // Recebe os novos valores de nome e descrição da tag de serviço
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");

        // Instancia o DAO e a model da TagServico
        TagServicoDAO tagServicoDAO = new TagServicoDAO();
        TagServico tagServico = new TagServico(nome, descricao); // Cria uma nova instância da TagServico com os dados atualizados

        // Chama o método para alterar a tag de serviço no banco de dados
        int num = tagServicoDAO.alterarTagServico(id, tagServico);
        if (num == 1) {
            req.setAttribute("retorno", "certo"); // Alteração bem-sucedida
        } else if (num == 0) {
            req.setAttribute("retorno", "notfound"); // Tag de serviço não encontrada
        } else {
            req.setAttribute("retorno", "erro"); // Erro ao tentar alterar
        }

        // Define atributos adicionais para a requisição
        req.setAttribute("metodo", "ALTERAR");
        req.setAttribute("entidade", nome);

        // Redireciona para a página de listagem de tags de serviço
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/tagServico/listagemTagServico.jsp");
        rd.include(req, resp); // Inclui a página de listagem na resposta
    }
}
