<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.constroocrud.crud.DAOs.PlanoAtivacaoDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Planos de Ativação</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemPlanosAtivacao.css">
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
        <li><a href="${pageContext.request.contextPath}/pages/listagemTagServico.jsp">Tag Servico</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/listagemPlanosAtivacao.jsp" class="active">Planos Ativação</a></li>
    </ul>
</nav>

<main>
    <section class="planos">
        <h1>Planos de Ativação</h1>
        <div class="controls">
            <form id="form-plano-ativacao-criar" action="${pageContext.request.contextPath}/BuscarPlanoAtivacaoServlet" method="post">
                <input type="text" name="id" placeholder="Pesquise o plano pelo id">
                <input type="submit" value="Pesquisar">
            </form>
            <form action="${pageContext.request.contextPath}/pages/alterarPlanoAtivacaoPeloID.jsp" method="get">
                <button type="submit" class="create-btn">Alterar</button> <!-- Botão para deletar administrador -->
            </form>
            <form action="${pageContext.request.contextPath}/pages/deletarPlanoAtivacaoPeloID.jsp" method="get">
                <button type="submit" class="create-btn">Deletar</button> <!-- Botão para deletar administrador -->
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


        <%-- Inicio do loop dos resultados --%>
        <%
            PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();
            ResultSet resultSet = planoAtivacaoDAO.buscarPlanoAtivacao();

            try {
                while (resultSet.next()) {
        %>
        <div class="plano">
            <div class="info">
                <div class="details">
                    <h2>Plano ID: <%= resultSet.getInt("id") %></h2>
                    <div class="bottom-plano-ativacao-infos">
                        <p>Ativação: <%= resultSet.getString("ativacao") %></p>
                        <div class="infos-duracao">
                            <p class="infos-texto">Data de Assinatura: <%= resultSet.getDate("data_assinatura") %></p>
                            <p class="infos-texto">Data de Término: <%= resultSet.getDate("data_final") %></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="actions">
                <form action="${pageContext.request.contextPath}/DeletarPlanoAtivacaoServlet" method="post">
                    <input type="hidden" name="id_planoativacao" value="<%= resultSet.getInt("id") %>">
                    <button type="submit" class="delete-btn">Deletar</button>
                </form>

                <form action="${pageContext.request.contextPath}/AlterarPlanoAtivacaoServlet" method="post">
                    <input type="hidden" name="id_planoativacao" value="<%= resultSet.getInt("id") %>">
                    <button type="submit" class="edit-btn">Alterar</button>
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
