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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "InsertTagServicoServlet", value = "/InserirTagServicoServlet")
public class InsertTagServicoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        String str_id = req.getParameter("id");
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");

        TagServico tagServico = new TagServico(nome,descricao);
        TagServicoDAO tagServicoDAO = new TagServicoDAO();

        try {
            ResultSet rs = tagServicoDAO.buscarTagServicoPeloID(Integer.parseInt(str_id));
            if (!rs.next()){
                int num = tagServicoDAO.inserirTagServico(tagServico);
                if (num == 1){
                    req.setAttribute("retorno", "certo");
                }else if (num == 0){
                    req.setAttribute("retorno", "notfound");
                }else {
                    req.setAttribute("retorno", "erro");
                }
            }else {
                req.setAttribute("retorno", "existente");  // Categoria já existe
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("metodo", "INSERIR");
        req.setAttribute("entidade", str_id);

        //Voce é direcionado para a listagem de usuarios!
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/pages/listagemTagServico.jsp");
        rd.include(req, resp);




    }
}
