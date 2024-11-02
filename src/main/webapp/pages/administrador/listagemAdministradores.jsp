<%@ page import="java.sql.ResultSet" %> <!-- Importa a classe ResultSet -->
<%@ page import="java.sql.SQLException" %> <!-- Importa a classe SQLException para tratamento de erros SQL -->
<%@ page import="org.constroocrud.crud.DAOs.AdministradorDAO" %> <!-- Importa a classe AdministradorDAO para manipulação de dados do administrador -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <!-- Define tipo de conteúdo e codificação para UTF-8 -->
<%@ page errorPage="../ErrorPage.jsp" %> <!-- Define a página de erro padrão -->
<!DOCTYPE html>
<html lang="pt-BR"> <!-- Define o idioma da página -->
<head>
    <meta charset="UTF-8"> <!-- Define a codificação de caracteres -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Configura responsividade -->
    <title>Administradores</title> <!-- Título da página -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemAdministradores.css"> <!-- Importa o CSS -->
</head>
<body>
<header>
    <div class="logo">Constroo 🌍</div> <!-- Logo da aplicação -->
</header>
<nav> <!-- Navegação principal com links para diferentes seções -->
    <ul>
        <li><a href="${pageContext.request.contextPath}/pages/plano/listagemPlanos.jsp">Planos</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/categoriaProduto/listagemCategoriaProdutos.jsp">Categorias</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/administrador/listagemAdministradores.jsp" class="active">Adms</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/tagServico/listagemTagServico.jsp">Tag Serviço</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/planoAtivacao/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>
    </ul>
</nav>
<main>
    <section class="administradores"> <!-- Seção para listagem dos administradores -->
        <h1>Administradores</h1> <!-- Título da seção -->
        <div class="controls"> <!-- Controles de ação para adicionar, alterar e deletar -->
            <div class="actionsCreateAlterDelete">
                <form action="${pageContext.request.contextPath}/pages/administrador/cadastrarAdministrador.jsp" method="get">
                    <button type="submit" class="create-btn">Adicionar</button> <!-- Botão para adicionar administrador -->
                </form>
                <form action="${pageContext.request.contextPath}/pages/administrador/alterarAdministradorPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Alterar</button> <!-- Botão para alterar administrador -->
                </form>
                <form action="${pageContext.request.contextPath}/pages/administrador/deletarAdministradorPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Deletar</button> <!-- Botão para deletar administrador -->
                </form>
            </div>

            <form action="${pageContext.request.contextPath}/BuscarAdministradorServlet" method="post"> <!-- Formulário de pesquisa de administradores -->
                <input type="text" name="nome" id="nome" placeholder="Pesquisar administradores" required> <!-- Campo de pesquisa pelo nome -->
                <input type="submit" value="Pesquisar"> <!-- Botão de pesquisa -->
            </form>
        </div>

        <%
            // Exibe mensagens de retorno baseadas no atributo "retorno" para feedback ao usuário
            if (request.getAttribute("retorno") == "erro") {
        %>
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

        <%
            // Busca os administradores usando AdministradorDAO
            AdministradorDAO administradorDAO = new AdministradorDAO(); // Instância do DAO
            ResultSet resultSet = administradorDAO.buscarAdministrador(); // Executa busca de administradores

            try {
                while (resultSet.next()) { // Itera sobre os resultados e exibe cada administrador encontrado
        %>
        <div class="administrador"> <!-- Container para cada administrador -->
            <div class="info"> <!-- Informações do administrador -->
                <h2><%= resultSet.getString("nome") %></h2> <!-- Exibe o nome do administrador -->
                <p>ID: <%= resultSet.getInt("id") %> | Email: <%= resultSet.getString("email") %></p> <!-- Exibe ID e Email do administrador -->
            </div>
            <div class="actions"> <!-- Ações disponíveis para cada administrador: deletar e editar -->
                <form action="${pageContext.request.contextPath}/DeletarAdministradorServlet" method="post"> <!-- Formulário para deletar administrador -->
                    <input type="hidden" name="administrador_id" value="<%= resultSet.getInt("id") %>"> <!-- ID do administrador para deletar -->
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>"> <!-- Nome do administrador -->
                    <button type="submit" class="delete-btn">Deletar</button> <!-- Botão de deletar administrador -->
                </form>
                <form action="${pageContext.request.contextPath}/DirecionarAdministradorAlterarServlet" method="post"> <!-- Formulário para editar administrador -->
                    <input type="hidden" name="administrador_id" value="<%= resultSet.getInt("id") %>"> <!-- ID do administrador -->
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>"> <!-- Nome do administrador -->
                    <input type="hidden" name="email" value="<%= resultSet.getString("email") %>"> <!-- Email do administrador -->
                    <input type="hidden" name="senha" value="<%= resultSet.getString("senha") %>"> <!-- Senha do administrador -->
                    <button type="submit" class="edit-btn">Editar</button> <!-- Botão de editar administrador -->
                </form>
            </div>
        </div>
        <%
                } // Fim do while para exibir todos os registros encontrados
            } catch (SQLException e) { // Tratamento de exceções de SQL
                e.printStackTrace(); // Imprime o stack trace
            }
        %>
    </section>
</main>
</body>
</html>
