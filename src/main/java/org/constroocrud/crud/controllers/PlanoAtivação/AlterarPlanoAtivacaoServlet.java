package org.constroocrud.crud.controllers.PlanoAtivação;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.PlanoAtivacaoDAO;

import java.io.IOException;
import java.io.PrintWriter;

// Este servlet é responsável por alterar o status de ativação de um plano específico com base no ID fornecido.

@WebServlet(name = "AlterarPlanoAtivacaoServlet", value = "/AlterarPlanoAtivacaoServlet")
public class AlterarPlanoAtivacaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Define o tipo de resposta e inicializa o PrintWriter para saída de depuração
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Recebe o ID do Plano de Ativação a ser alterado e converte para inteiro
        String str_id_tipo = req.getParameter("id_planoativacao");
        int id_tipo = 0;

        req.setAttribute("metodo", "ALTERAR");

        try{
            id_tipo = Integer.parseInt(str_id_tipo);
        }catch (NumberFormatException e){
            req.setAttribute("retorno", "notfound");
            req.setAttribute("mensagem", "Plano Ativação não encontrado!");

            // Redireciona para a página de alteração do administrador
            req.getRequestDispatcher("../pages/planoAtivacao/alterarPlanoAtivacaoPeloID.jsp").forward(req, resp);
        }finally {
            //Estabelece a conexao
            PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();

            req.setAttribute("entidade", id_tipo);

            //caso esteja na tabela Plano Ativação, a coluna ativação será alterada
            int num = planoAtivacaoDAO.alterarAtivacao(id_tipo);
            if (num == 1){
                req.setAttribute("retorno", "certo");
                req.getRequestDispatcher("/pages/planoAtivacao/listagemPlanosAtivacao.jsp").forward(req, resp);
            }else if (num == 0){
                req.setAttribute("retorno", "notfound");
                req.setAttribute("mensagem", "Plano Ativação não encontrado!");
                req.getRequestDispatcher("/pages/planoAtivacao/alterarPlanoAtivacaoPeloID.jsp").forward(req, resp);
            }else {
                req.setAttribute("retorno", "erro");
                req.setAttribute("mensagem", "Erro");
                req.getRequestDispatcher("/pages/planoAtivacao/listagemPlanosAtivacao.jsp").forward(req, resp);

            }

        }

        int id_tipo = Integer.parseInt(str_id_tipo);
        out.println(id_tipo);  // Imprime o ID para fins de depuração

        // Cria uma instância do DAO para interação com o banco de dados
        PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();

        // Realiza a alteração da ativação no banco de dados
        int num = planoAtivacaoDAO.alterarAtivacao(id_tipo);

        // Define o resultado da operação e armazena como atributo para resposta
        if (num == 1) {
            req.setAttribute("retorno", "certo");  // Alteração bem-sucedida
        } else if (num == 0) {
            req.setAttribute("retorno", "notfound");  // ID não encontrado
        } else {
            req.setAttribute("retorno", "erro");  // Erro na operação
        }

        req.setAttribute("metodo", "ALTERAR");
        req.setAttribute("entidade", id_tipo);

        // Redireciona para a página de listagem de Planos de Ativação com a mensagem de retorno apropriada
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/planoAtivacao/listagemPlanosAtivacao.jsp");
        rd.forward(req, resp);
    }
}
