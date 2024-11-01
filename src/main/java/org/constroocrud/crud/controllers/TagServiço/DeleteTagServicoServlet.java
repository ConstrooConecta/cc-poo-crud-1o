package org.constroocrud.crud.controllers.TagServiço;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.TagServicoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "DeleteTagServicoServlet", value = "/DeletarTagServicoServlet")
public class DeleteTagServicoServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("tag_id");

        TagServicoDAO tagServicoDAO = new TagServicoDAO();
        try {
            ResultSet rs = tagServicoDAO.buscarTagServicoPeloID(Integer.parseInt(id));
            if (!rs.next()){
                req.setAttribute("retorno", "notfound");
                req.setAttribute("metodo", "DELETAR");
                req.setAttribute("entidade", id);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/pages/tagServico/listagemTagServico.jsp");
                rd.include(req, resp);
            }else{
                String nome = rs.getString("nome");

                int num = tagServicoDAO.removerTagServicoPeloID(Integer.parseInt(id));

                if (num == 1){
                    req.setAttribute("retorno", "certo");
                }else if (num == 0){
                    req.setAttribute("retorno", "notfound");
                }else {
                    req.setAttribute("retorno", "erro");
                }

                req.setAttribute("metodo", "DELETAR");
                req.setAttribute("entidade", nome);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/pages/tagServico/listagemTagServico.jsp");
                rd.include(req, resp);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            req.setAttribute("retorno", "erro");
            req.setAttribute("mensagem", "Erro SQL");

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/tagServico/listagemTagServico.jsp");
            rd.include(req, resp);

        }
    }
}
