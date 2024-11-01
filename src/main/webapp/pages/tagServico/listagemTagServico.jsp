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
    <div class="logo">Constroo 🌍</div> <!-- Logo do aplicativo -->
</header>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/pages/plano/listagemPlanos.jsp">Planos</a></li>
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
            <div class="actionsCreateAlterDelete">
                <form action="${pageContext.request.contextPath}/pages/tagServico/cadastrarTagServico.html" method="get">
                    <button type="submit" class="create-btn">Adicionar</button> <!-- Botão para adicionar Tag Serviço -->
                </form>
                <form action="${pageContext.request.contextPath}/pages/tagServico/alterarTagServicoPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Alterar</button> <!-- Botão para alterar Tag Serviço -->
                </form>
                <form action="${pageContext.request.contextPath}/pages/tagServico/deletarTagServicoPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Deletar</button> <!-- Botão para deletar Tag Serviço -->
                </form>
            </div>
            <form action="${pageContext.request.contextPath}/BuscarTagServicoServlet" method="post">
                <input type="text" name="nome" id="nome" placeholder="Pesquisar categorias">
                <input type="submit" value="Pesquisar"> <!-- Botão de pesquisa -->
            </form>
        </div>

        <%-- Exibição de mensagens de retorno do servidor --%>
        <% if (request.getAttribute("retorno") == "erro") { %>
        <div>
            <p>ERRO AO <%= request.getAttribute("metodo") %> O ITEM: <%= request.getAttribute("entidade") %></p>
        </div>
        <% } else if (request.getAttribute("retorno") == "certo") { %>
        <div>
            <p>SUCESSO AO <%= request.getAttribute("metodo") %> O ITEM: <%= request.getAttribute("entidade") %></p>
        </div>
        <% } else if (request.getAttribute("retorno") == "notfound") { %>
        <div>
            <p>ITEM NÃO ENCONTRADO AO <%= request.getAttribute("metodo") %>: <%= request.getAttribute("entidade") %></p>
        </div>
        <% } else if (request.getAttribute("retorno") == "existente") { %>
        <div>
            <p>ITEM JÁ EXISTENTE AO <%= request.getAttribute("metodo") %>: <%= request.getAttribute("entidade") %></p>
        </div>
        <% } %>

        <%-- Recupera os dados do banco de dados via DAO --%>
        <%
            TagServicoDAO tagServicoDAO = new TagServicoDAO();
            ResultSet resultSet = tagServicoDAO.buscarTagServicos();

            try {
                while (resultSet.next()) {
        %>
        <div class="categoria">
            <div class="info">
                <h2><%= resultSet.getString("nome") %></h2>
                <p>ID: <%= resultSet.getInt("id") %> | Descrição: <%= resultSet.getString("descricao") %></p> <!-- Exibe ID e Descrição -->
            </div>
            <div class="actions">
                <form action="${pageContext.request.contextPath}/DeletarTagServicoServlet" method="post">
                    <input type="hidden" name="tag_id" value="<%= resultSet.getInt("id") %>">
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>">
                    <button type="submit" class="delete-btn">Deletar</button> <!-- Botão para deletar Tag Serviço -->
                </form>
                <form action="${pageContext.request.contextPath}/DirecionarTagServicoServlet" method="post">
                    <input type="hidden" name="tag_id" value="<%= resultSet.getInt("id") %>">
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>">
                    <input type="hidden" name="descricao" value="<%= resultSet.getString("descricao") %>">
                    <button type="submit" class="edit-btn">Editar</button> <!-- Botão para editar Tag Serviço -->
                </form>
            </div>
        </div>
        <%
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace(); // Imprime a stack trace em caso de erro SQL
            }
        %>
    </section>
</main>
</body>
</html>
