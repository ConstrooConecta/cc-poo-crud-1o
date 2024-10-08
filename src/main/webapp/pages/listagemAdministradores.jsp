<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.constroocrud.crud.DAOs.AdministradorDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administradores</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemAdministradoresR.css">
</head>
<body>
<header>
    <div class="logo">Constroo 🌍</div>
</header>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/pages/listagemPlanos.jsp" >Planos</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemCategoriaProdutos.jsp">Categorias</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemAdministradores.jsp"  class="active">Adms</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemTagServico.jsp">Tag Serviço</a></li>
    </ul>
</nav>
<main>
    <section class="administradores">
        <h1>Administradores</h1>
        <div class="controls">
            <form action="${pageContext.request.contextPath}/cadastrarAdministrador.html" method="get">
                <button type="submit" class="create-btn">Adicionar</button>
            </form>
            <form action="${pageContext.request.contextPath}/BuscarAdministradorServlet" method="get">
                <input type="text" name="pesquisar" placeholder="Pesquisar administradores">
                <input type="submit" value="Pesquisar">
            </form>
        </div>

        <%
            AdministradorDAO administradorDAO = new AdministradorDAO();
            ResultSet resultSet = administradorDAO.buscarAdministrador();

            try {
                while (resultSet.next()) {
        %>
        <div class="administrador">
            <div class="info">
                <h2><%= resultSet.getString("nome") %></h2>
                <p>Email: <%= resultSet.getString("email") %></p>
            </div>
            <div class="actions">
                <form action="${pageContext.request.contextPath}/DeletarAdministradorServlet" method="post">
                    <input type="hidden" name="administrador_id" value="<%= resultSet.getInt("id") %>">
                    <button type="submit" class="delete-btn">Deletar</button>
                </form>
                <form action="${pageContext.request.contextPath}/DirecionarAdministradorAlterarServlet" method="post">
                    <input type="hidden" name="administrador_id" value="<%= resultSet.getInt("id") %>">
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>">
                    <input type="hidden" name="email" value="<%= resultSet.getString("email") %>">
                    <input type="hidden" name="senha" value="<%= resultSet.getString("senha") %>">
                    <button type="submit" class="edit-btn">Editar</button>
                </form>
            </div>
        </div>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>
    </section>
</main>
</body>
</html>
