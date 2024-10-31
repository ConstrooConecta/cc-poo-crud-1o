<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.constroocrud.crud.models.TagServico" %>
<%@ page import="org.constroocrud.crud.DAOs.TagServicoDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../ErrorPage.jsp" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tag Serviços</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemTagServico.css">

</head>
<body>
<header>
    <div class="logo">Constroo 🌍</div>
</header>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/pages/plano/listagemPlanos.jsp" >Planos</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/categoriaProduto/listagemCategoriaProdutos.jsp">Categorias</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/administrador/listagemAdministradores.jsp">Adms</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/tagServico/listagemTagServico.jsp" class="active">Tag Servico</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/planoAtivacao/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>

    </ul>
</nav>
<main>
    <section class="categorias">
        <h1>Tag Serviços</h1>
        <div class="controls">
            <form action="${pageContext.request.contextPath}/cadastros/cadastrarTagServico.html" method="get">
                <button class="create-btn">Criar</button>
            </form>
            <form action="${pageContext.request.contextPath}/BuscarTagServicoServlet" method="post">
                <input type="text" name="nome" placeholder="Pesquisar categorias">
                <input type="submit" value="Pesquisar">
            </form>
        </div>

        <% if (request.getAttribute("retorno") == "erro"){
        %>
        <div>
            <P>ERRO AO <%= request.getAttribute("metodo") %> O ITEM: <%= request.getAttribute("entidade") %></P>

        </div>
        <%} else if (request.getAttribute("retorno") == "certo") {%>
        <div>
            <P>SUCESSO AO <%= request.getAttribute("metodo") %> O ITEM: <%= request.getAttribute("entidade") %></P>

        </div>
        <%} else if (request.getAttribute("retorno") == "notfound") {%>
        <div>
            <p>ITEM NÃO ENCONTRADO AO <%= request.getAttribute("metodo")%>: <%= request.getAttribute("entidade") %></p>
        </div>
        <%} else if (request.getAttribute("retorno") == "existente") {%>
        <div>
            <p>ITEM JÁ EXISTENTE AO <%= request.getAttribute("metodo") %>: <%= request.getAttribute("entidade") %></p>
        </div>
        <%}%>


        <%
            // Recupera os dados do banco de dados via DAO
            TagServicoDAO tagServicoDAO = new TagServicoDAO();
            ResultSet resultSet = tagServicoDAO.buscarTagServicoPeloNome(String.valueOf(request.getAttribute("nome")));

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
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>">
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
