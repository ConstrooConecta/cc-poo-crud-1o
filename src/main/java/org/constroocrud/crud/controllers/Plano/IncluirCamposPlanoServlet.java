package org.constroocrud.crud.controllers.Plano;

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

@WebServlet(name = "IncluirCamposPlanoServlet", value = "/IncluirCamposPlanoServlet")
public class IncluirCamposPlanoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configura o tipo de resposta como HTML
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o ID do plano para ser alterado
        String str_id = req.getParameter("plano_id");
        int id;

        try {
            id = Integer.parseInt(str_id);
        } catch (NumberFormatException e) {
            // Em caso de erro de conversão, configura o retorno como "notfound" e redireciona
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Plano não encontrado!");
            req.getRequestDispatcher("/pages/plano/alterarPlanoPeloID.jsp").forward(req, resp);
            return;
        }

        // Instancia o DAO do plano e busca o plano pelo ID
        PlanoDAO planoDAO = new PlanoDAO();
        ResultSet resultSet = planoDAO.buscarPlanoPeloID(id);

        try {
            if (!resultSet.next()) {
                // Se o plano não for encontrado, configura o retorno como "notfound"
                req.setAttribute("retorno", "notfound");
                req.setAttribute("mensagem", "Plano não encontrado!");
                req.getRequestDispatcher("/pages/plano/alterarPlanoPeloID.jsp").forward(req, resp);

            } else {
                // Obtém os dados do plano do ResultSet
                String nome = resultSet.getString("nome_plano");
                String descricao = resultSet.getString("descricao");
                int duracao = resultSet.getInt("tempo_duracao");
                double valor = resultSet.getDouble("valor");
                String tipo = resultSet.getString("tipo_plano");

                // Define os atributos para a página de alteração
                req.setAttribute("id", id);
                req.setAttribute("nome", nome);
                req.setAttribute("descricao", descricao);
                req.setAttribute("duracao", duracao);
                req.setAttribute("tipo", tipo);
                req.setAttribute("valor", valor);

                // Redireciona para a página de alteração com os dados carregados
                req.getRequestDispatcher("/pages/plano/alterarCamposPlano.jsp").forward(req, resp);
            }
        } catch (SQLException sqlException) {
            // Em caso de erro SQL, configura o retorno como "erro" e redireciona com mensagem
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");
            req.getRequestDispatcher("/pages/plano/alterarPlanoPeloID.jsp").forward(req, resp);
        }
    }
}
