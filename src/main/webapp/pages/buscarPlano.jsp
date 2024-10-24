<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.constroocrud.crud.DAOs.PlanoDAO" %>
<%@ page import="sun.awt.DebugSettings" %> <%-- Supondo que você tenha um DAO para Planos --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="ErrorPage.jsp" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Planos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemPlano.css"> <!-- Atualize o caminho se necessário -->
</head>
<body>
<header>
    <div class="logo">Constroo 🌍</div>
</header>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/pages/listagemPlanos.jsp" class="active">Planos</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemCategoriaProdutos.jsp">Categorias</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemAdministradores.jsp">Adms</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemTagServico.jsp">Tag Servico</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>
    </ul>
</nav>
<main>
    <section class="planos">
        <h1>Planos</h1>
        <div class="controls">
            <form action="${pageContext.request.contextPath}/cadastros/cadastrarPlano.html" method="get">
                <button type="submit" class="create-btn">Criar</button>
            </form>
            <form action="${pageContext.request.contextPath}/BuscarPlanoServlet" method="post">
                <input type="text" name="pesquisar" placeholder="Pesquisar planos">
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
            PlanoDAO planoDAO = new PlanoDAO();
            ResultSet resultSet = planoDAO.buscarPlanoPeloNome(String.valueOf(request.getAttribute("nome")));
            try {
                while (resultSet.next()) {
        %>
        <div class="plano">
            <div class="info">
                <div class="details">
                    <h2><%= resultSet.getString("nome_plano") %></h2>
                    <p>Preço Mensal: <%= resultSet.getDouble("valor") %></p>
                    <p>Tipo de Usuário: <%= resultSet.getString("tipo_plano") %></p>
                    <p>Tempo duração: <%= resultSet.getInt("tempo_duracao") %></p>
                    <p><%= resultSet.getString("descricao") %></p>
                </div>
            </div>
            <div class="actions">
                <form action="${pageContext.request.contextPath}/DeletarPlanoServlet" method="post">
                    <input type="hidden" name="plano_id" value="<%= resultSet.getInt("id") %>">
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome_plano")%>">
                    <button type="submit" class="delete-btn">Deletar</button>
                </form>
                <form action="${pageContext.request.contextPath}/DirecionarPlanoAlterarServlet" method="post">
                    <input type="hidden" name="plano_id" value="<%= resultSet.getInt("id") %>">
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome_plano") %>">
                    <input type="hidden" name="precoMensal" value="<%= resultSet.getString("valor") %>">
                    <input type="hidden" name="tipoUsuario" value="<%= resultSet.getString("tipo_plano") %>">
                    <input type="hidden" name="duracao" value="<%= resultSet.getString("tempo_duracao") %>">
                    <input type="hidden" name="descricao" value="<%= resultSet.getString("descricao") %>">
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