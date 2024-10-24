<%@ page import="java.sql.ResultSet" %> <!-- Importa a classe ResultSet -->
<%@ page import="java.sql.SQLException" %> <!-- Importa a classe SQLException -->
<%@ page import="org.constroocrud.crud.models.CategoriaProduto" %> <!-- Importa a classe CategoriaProduto -->
<%@ page import="org.constroocrud.crud.DAOs.CategoriaProdutoDAO" %> <!-- Importa a classe CategoriaProdutoDAO -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <!-- Define tipo de conteúdo e codificação -->
<%@ page errorPage="ErrorPage.jsp" %>
<!DOCTYPE html>
<html lang="pt-BR"> <!-- Define o idioma da página -->
<head>
    <meta charset="UTF-8"> <!-- Define a codificação de caracteres -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Configura responsividade -->
    <title>Categorias</title> <!-- Título da página -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemCategoriaProduto.css"> <!-- Importa o CSS -->
</head>
<body>
<header>
    <div class="logo">Constroo 🌍</div> <!-- Logo da aplicação -->
</header>
<nav> <!-- Navegação principal -->
    <ul>
        <li><a href="${pageContext.request.contextPath}/pages/listagemPlanos.jsp">Planos</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemCategoriaProdutos.jsp" class="active">Categorias</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemAdministradores.jsp">Adms</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemTagServico.jsp">Tag Serviço</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>
    </ul>
</nav>
<main>
    <section class="categorias"> <!-- Seção para categorias -->
        <h1>Categorias Produtos</h1> <!-- Título da seção -->
        <div class="controls"> <!-- Controles de ação -->
            <div class="actionsCreateAlterDelete">
                <form action="${pageContext.request.contextPath}/cadastros/cadastrarCategoriaProduto.html" method="get">
                    <button type="submit" class="create-btn">Adicionar</button> <!-- Botão para adicionar administrador -->
                </form>
                <form action="${pageContext.request.contextPath}/pages/alterarCategoriaProdutoPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Alterar</button> <!-- Botão para adicionar administrador -->
                </form>
                <form action="${pageContext.request.contextPath}/pages/deletarCategoriaProdutoPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Deletar</button> <!-- Botão para deletar administrador -->
                </form>
            </div>
            <form action="${pageContext.request.contextPath}/BuscarCategoriaProdutoServlet" method="post">
                <input type="text" name="nome" placeholder="Pesquisar categorias"> <!-- Campo de pesquisa -->
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
            CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO(); // Instância do DAO
            ResultSet resultSet = categoriaProdutoDAO.buscarCategoriaProduto(); // Busca as categorias
            try {
                while (resultSet.next()) { // Itera sobre os resultados
        %>
        <div class="categoria"> <!-- Container para cada categoria -->
            <div class="info"> <!-- Informações da categoria -->
                <h2><%= resultSet.getString("nome") %></h2> <!-- Nome da categoria -->
                <p><%= resultSet.getString("descricao") %></p> <!-- Descrição da categoria -->
            </div>
            <div class="actions"> <!-- Ações disponíveis -->
                <form action="${pageContext.request.contextPath}/DeletarCategoriaProdutoServlet" method="post"> <!-- Formulário para deletar -->
                    <input type="hidden" name="categoria_id" value="<%= resultSet.getInt("id") %>">
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>"><!-- ID da categoria -->
                    <button type="submit" class="delete-btn">Deletar</button> <!-- Botão de deletar -->
                </form>
                <form action="${pageContext.request.contextPath}/DirecionarCategoriaProdutoAlterarServlet" method="post"> <!-- Formulário para editar -->
                    <input type="hidden" name="categoria_id" value="<%= resultSet.getInt("id") %>"> <!-- ID da categoria -->
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>"> <!-- Nome da categoria -->
                    <input type="hidden" name="descricao" value="<%= resultSet.getString("descricao") %>"> <!-- Descrição da categoria -->
                    <button type="submit" class="edit-btn">Editar</button> <!-- Botão de editar -->
                </form>
            </div>
        </div>
        <%
                } // Fim do while
            } catch (SQLException sqlException) { // Tratamento de exceções
                sqlException.printStackTrace(); // Imprime o stack trace em caso de erro
            }
        %>
    </section>
</main>
</body>
</html>
