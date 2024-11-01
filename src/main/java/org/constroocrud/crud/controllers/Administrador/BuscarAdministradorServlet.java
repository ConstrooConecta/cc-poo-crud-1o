package org.constroocrud.crud.controllers.Administrador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.constroocrud.crud.DAOs.AdministradorDAO;
import org.constroocrud.crud.models.Administrador;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BuscarAdministradorServlet", value = "/BuscarAdministradorServlet")
public class BuscarAdministradorServlet extends HttpServlet {

    // Método para lidar com requisições POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Define o tipo de resposta como HTML e cria um PrintWriter para escrever a resposta
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Extrai o parâmetro "nome" da requisição, representando o nome do administrador a ser buscado
        String nome = req.getParameter("nome");

        // Atribui o nome extraído ao atributo "nome" do request para uso na página JSP de resultado
        req.setAttribute("nome", nome);

        // Redireciona para a página JSP de busca, passando o request e response, e incluindo o valor de "nome"
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/administrador/buscarAdministrador.jsp");
        rd.include(req, resp);
    }
}
