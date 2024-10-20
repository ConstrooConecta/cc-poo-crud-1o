package org.constroocrud.crud.controllers.Plano;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoDAO;
import org.constroocrud.crud.DAOs.TagServicoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "IncluirCamposPlanoServlet", value = "/IncluirCamposPlanoServlet")
public class IncluirCamposPlanoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe os dados do administrador a ser alterado
        String str_id = req.getParameter("plano_id");
        int id = Integer.parseInt(str_id);
        PlanoDAO planoDAO = new PlanoDAO();

        ResultSet resultSet = planoDAO.buscarPlanoPeloID(id);

        try {
            if (!resultSet.next()){
                req.setAttribute("retorno", "notfound");
                req.setAttribute("mensagem", "Admin não encontrado!");
                req.getRequestDispatcher("pages/alterarPlanoPeloID.jsp").forward(req, resp);
            }else{

                String nome = resultSet.getString("nome_plano");
                String descricao = resultSet.getString("descricao");
                String strDuracao = resultSet.getString("tempo_duracao");
                String strValor = resultSet.getString("valor");
                String tipo = resultSet.getString("tipo_plano");
                double valor = Double.parseDouble(strValor);
                int duracao = Integer.parseInt(strDuracao);

                req.setAttribute("id", id);
                req.setAttribute("nome", nome);
                req.setAttribute("descricao", descricao);
                req.setAttribute("duracao", duracao);
                req.setAttribute("tipo", tipo);
                req.setAttribute("valor", valor);

                req.getRequestDispatcher("pages/alterarCamposPlano.jsp").forward(req, resp);
            }



        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");
        }

        // Define atributos para serem usados na página de alteração


        // Redireciona para a página de alteração do administrador

    }
}
