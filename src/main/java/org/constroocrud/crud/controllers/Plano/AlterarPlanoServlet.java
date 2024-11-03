package org.constroocrud.crud.controllers.Plano;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoDAO;
import org.constroocrud.crud.models.Plano;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AlterarPlanoServlet", value = "/AlterarPlanoServlet")
public class AlterarPlanoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe os parâmetros da requisição para a alteração do plano
        String str_id = req.getParameter("id");
        int id = Integer.parseInt(str_id); // Converte o ID de String para int

        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String strDuracao = req.getParameter("duracao");
        String strValor = req.getParameter("valor");
        String tipo = req.getParameter("tipo");

        // Converte os parâmetros de duração e valor
        double valor = Double.parseDouble(strValor);
        int duracao = Integer.parseInt(strDuracao);

        // Cria uma instância do objeto Plano e do DAO responsável
        PlanoDAO planoDAO = new PlanoDAO();
        Plano plano = new Plano(tipo, valor, descricao, nome, duracao);

        // Realiza a alteração do plano no banco de dados e define o resultado da operação
        int num = planoDAO.alterarPlano(id, plano);
        if (num == 1) {
            req.setAttribute("retorno", "certo"); // Sucesso
        } else if (num == 0) {
            req.setAttribute("retorno", "notfound"); // Plano não encontrado
        } else {
            req.setAttribute("retorno", "erro"); // Erro durante a alteração
        }

        // Define atributos para feedback e redirecionamento
        req.setAttribute("metodo", "ALTERAR");
        req.setAttribute("entidade", nome);

        // Redireciona para a página de listagem de planos
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/plano/listagemPlanos.jsp");
        rd.include(req, resp);
    }
}
