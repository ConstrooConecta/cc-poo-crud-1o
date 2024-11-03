<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.constroocrud.crud.DAOs.PlanoAtivacaoDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../ErrorPage.jsp" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Planos de Ativação</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemPlanosAtivacao.css"> <!-- CSS para estilização -->
</head>
<body>
<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1>
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo">  <!-- Logo da aplicação -->
    </div>

    <div class="cruds"> <!-- Navegação principal com links para diferentes seções -->
        <ul>
            <li><a href="${pageContext.request.contextPath}/pages/plano/listagemPlanos.jsp">Planos</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/categoriaProduto/listagemCategoriaProdutos.jsp">Categorias</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/administrador/listagemAdministradores.jsp">Adms</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/tagServico/listagemTagServico.jsp">Tag Serviço</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/planoAtivacao/listagemPlanosAtivacao.jsp" class="active">Planos Ativação</a></li>
        </ul>
    </div>
</header>

<main>
    <section class="planos">
        <h1>Planos de Ativação</h1>
        <div class="controls">
            <div class="actionsCreateAlterDelete">
                <form action="${pageContext.request.contextPath}/pages/planoAtivacao/alterarPlanoAtivacaoPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Alterar</button> <!-- Botão para alterar plano -->
                </form>
                <form action="${pageContext.request.contextPath}/pages/planoAtivacao/deletarPlanoAtivacaoPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Deletar</button> <!-- Botão para deletar plano -->
                </form>
            </div>
            <form id=form-plano-ativacao-criar" class="pesquisar" action="${pageContext.request.contextPath}/BuscarPlanoAtivacaoServlet" method="post">
                <input type="text" name="id" id="id" placeholder="Pesquise o plano pelo ID" required> <!-- Campo de pesquisa -->
                <input type="submit" value="Pesquisar"> <!-- Botão de pesquisa -->
            </form>
        </div>

        <!-- Mensagens de feedback para o usuário -->
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

        <%-- Início do loop dos resultados --%>
        <%
            PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();
            ResultSet resultSet = planoAtivacaoDAO.buscarPlanoAtivacao(); // Busca todos os planos de ativação

            try {
                while (resultSet.next()) {
        %>
        <div class="plano">
            <div class="info">
                <div class="details">
                    <h2>Plano ID: <%= resultSet.getInt("id") %></h2> <!-- Exibe o ID do plano -->
                    <div class="bottom-plano-ativacao-infos">
                        <p>Ativação: <% if (resultSet.getString("ativacao").equals("A")) { %> Ativo <% } else if (resultSet.getString("ativacao").equals("I")) { %> Inativo <% } %></p> <!-- Status da ativação -->
                        <div class="infos-duracao">
                            <p class="infos-texto">Data de Assinatura: <%= resultSet.getDate("data_assinatura") %></p> <!-- Data de assinatura -->
                            <p class="infos-texto">Data de Término: <%= resultSet.getDate("data_final") %></p> <!-- Data de término -->
                        </div>
                    </div>
                </div>
            </div>

            <div class="actions">
                <form action="${pageContext.request.contextPath}/DeletarPlanoAtivacaoServlet" method="post">
                    <input type="hidden" name="id_planoativacao" value="<%= resultSet.getInt("id") %>"> <!-- ID do plano a ser deletado -->
                    <button type="submit" class="delete-btn">Deletar</button> <!-- Botão de deletar -->
                </form>

                <form action="${pageContext.request.contextPath}/AlterarPlanoAtivacaoServlet" method="post">
                    <input type="hidden" name="id_planoativacao" value="<%= resultSet.getInt("id") %>"> <!-- ID do plano a ser alterado -->
                    <button type="submit" class="edit-btn">Alterar</button> <!-- Botão de alterar -->
                </form>
            </div>
        </div>

        <%
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Imprime a pilha de erro no console
            }
        %>
    </section>
</main>
</body>
</html>
