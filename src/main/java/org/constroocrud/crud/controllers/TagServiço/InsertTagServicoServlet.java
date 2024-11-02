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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "InsertTagServicoServlet", value = "/InserirTagServicoServlet")
public class InsertTagServicoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");

        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");

        TagServico tagServico = new TagServico(nome, descricao);
        TagServicoDAO tagServicoDAO = new TagServicoDAO();

        try {
            ResultSet rs = tagServicoDAO.buscarTagServicoPeloNome(nome);
            if (!rs.next()) {
                // Tag Serviço não existe, prossegue para inserção
                int num = tagServicoDAO.inserirTagServico(tagServico);
                req.setAttribute("retorno", num == 1 ? "certo" : "erro");
            } else {
                // Tag Serviço já existe
                req.setAttribute("retorno", "existente");
            }
        } catch (SQLException e) {
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro ao acessar o banco de dados: " + e.getMessage());
            e.printStackTrace(); // Logar a exceção para depuração
        }

        req.setAttribute("metodo", "INSERIR");
        req.setAttribute("entidade", nome);

        // Redireciona para a listagem de Tag Serviço
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/tagServico/listagemTagServico.jsp");
        rd.include(req, resp);
    }
}
