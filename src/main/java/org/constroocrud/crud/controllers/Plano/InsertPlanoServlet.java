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
import java.sql.ResultSet;
import java.sql.SQLException;

// SERVLET QUE INSERE UM NOVO PLANO
// Este servlet é responsável por coletar os dados de um novo plano e inseri-lo no banco de dados.

@WebServlet(name = "InsertPlanoServlet", value = "/InserirPlanoServlet")
public class InsertPlanoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Define o tipo de conteúdo da resposta
        resp.setContentType("text/html");

        // Cria um PrintWriter para escrever a resposta
        PrintWriter out = resp.getWriter();

        // Coleta os parâmetros enviados na requisição
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String strDuracao = req.getParameter("duracao");
        String tipo = req.getParameter("tipo");
        String strValor = req.getParameter("valor");

        // Converte os valores para os tipos adequados
        double valor = Double.parseDouble(strValor);
        int duracao = Integer.parseInt(strDuracao);

        // Cria uma instância do modelo Plano
        Plano plano = new Plano(tipo, valor, descricao, nome, duracao);
        PlanoDAO planoDAO = new PlanoDAO();

        try {
            // Verifica se já existe um plano com o mesmo ID
            ResultSet rs = planoDAO.buscarPlanoPeloID(plano.getId());
            if (!rs.next()) { // Se não encontrar, insere o novo plano
                int num = planoDAO.inserirPlano(plano);
                // Define o retorno baseado no resultado da inserção
                if (num == 1) {
                    req.setAttribute("retorno", "certo");
                } else if (num == 0) {
                    req.setAttribute("retorno", "notfound");
                } else {
                    req.setAttribute("retorno", "erro");
                }
            } else {
                // Se o plano já existir, pode-se definir um retorno específico, se necessário
                req.setAttribute("retorno", "existente");
            }
        } catch (SQLException e) {
            // Lida com exceções SQL
            throw new RuntimeException(e);
        }

        // Define os atributos que serão utilizados na página de listagem
        req.setAttribute("metodo", "INSERIR");
        req.setAttribute("entidade", nome);

        // Redireciona para a página de listagem de planos
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/plano/listagemPlanos.jsp");
        rd.include(req, resp);
    }
}
