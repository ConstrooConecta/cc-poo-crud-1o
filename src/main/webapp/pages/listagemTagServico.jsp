<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.constroocrud.crud.models.TagServico" %>
<%@ page import="org.constroocrud.crud.DAOs.TagServicoDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tag Serviços</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemTagServicoR.css">

</head>
<body>
<header>
    <div class="logo">Constroo 🌍</div>
</header>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/pages/listagemPlanos.jsp" >Planos</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemCategoriaProdutos.jsp">Categorias</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemAdministradores.jsp">Adms</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemTagServico.jsp" class="active">Tag Servico</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>

    </ul>
</nav>
<main>
    <section class="categorias">
        <h1>Tag Serviços</h1>
        <div class="controls">
            <form action="${pageContext.request.contextPath}/hyperText-markup-language/cadastrarTagServico.html" method="get">
                <button class="create-btn">Criar</button>
            </form>
            <form action="${pageContext.request.contextPath}/BuscarTagServicoServlet" method="get">
                <input type="text" name="pesquisa" placeholder="Pesquisar categorias">
                <input type="submit" value="Pesquisar">
            </form>
        </div>
        <%
            // Recupera os dados do banco de dados via DAO
            TagServicoDAO tagServicoDAO = new TagServicoDAO();
            ResultSet resultSet = tagServicoDAO.buscarTagServicos();

            try {
                while (resultSet.next()) {
        %>
        <div class="categoria">
            <div class="info">
                <h2><%= resultSet.getString("nome") %></h2>
                <p><%= resultSet.getString("descricao") %></p>
            </div>
            <div class="actions">
                <form action="${pageContext.request.contextPath}/DeletarTagServicoServlet" method="post">
                    <input type="hidden" name="tag_id" value="<%= resultSet.getInt("id") %>">
                    <button type="submit" class="delete-btn">Deletar</button>
                </form>
                <form action="${pageContext.request.contextPath}/DirecionarTagServicoServlet" method="post">
                    <input type="hidden" name="tag_id" value="<%= resultSet.getInt("id") %>">
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>">
                    <input type="hidden" name="descricao" value="<%= resultSet.getString("descricao") %>">
                    <button type="submit" class="edit-btn">Editar</button>
                </form>
            </div>
        </div>
        <%
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        %>
    </section>
</main>
</body>
</html>
