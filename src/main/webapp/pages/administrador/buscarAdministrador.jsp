<%@ page import="java.sql.ResultSet" %> <!-- Importa a classe ResultSet para manipulação de resultados SQL -->
<%@ page import="java.sql.SQLException" %> <!-- Importa a classe SQLException para tratamento de exceções de SQL -->
<%@ page import="org.constroocrud.crud.DAOs.AdministradorDAO" %> <!-- Importa o DAO de administrador -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <!-- Define tipo de conteúdo e codificação UTF-8 -->
<%@ page errorPage="../ErrorPage.jsp" %> <!-- Página de erro padrão -->
<!DOCTYPE html>
<html lang="pt-BR"> <!-- Define o idioma da página como português -->
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administradores</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemAdministradores.css"> <!-- Importa o CSS para estilização da página -->
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
            <li><a href="${pageContext.request.contextPath}/pages/administrador/listagemAdministradores.jsp" class="active">Adms</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/tagServico/listagemTagServico.jsp">Tag Serviço</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/planoAtivacao/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>
        </ul>
    </div>
</header>
<main>
    <section class="administradores">
        <h1>Administradores</h1>
        <div class="controls">
            <div class="actionsCreateAlterDelete"> <!-- Botões de ação para adicionar, alterar e deletar administradores -->
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
            <form class="pesquisar" action="${pageContext.request.contextPath}/BuscarAdministradorServlet" method="post"> <!-- Formulário para pesquisa de administradores -->
                <input type="text" name="nome" id="nome" value="<%= request.getAttribute("nome") %>" placeholder="Pesquisar administradores pelo nome" required> <!-- Campo de pesquisa pelo nome -->
                <input type="submit" value="Pesquisar">
            </form>
        </div>

        <%
            // Exibição de mensagens de retorno para informar o status da operação (sucesso, erro, item existente, etc.)
            String retorno = (String) request.getAttribute("retorno");
            String metodo = (String) request.getAttribute("metodo");
            String entidade = (String) request.getAttribute("entidade");
            if ("erro".equals(retorno)) { // Caso ocorra um erro ao realizar a operação
        %>
        <div>
            <p>ERRO AO <%= metodo %> O ITEM: <%= entidade %></p>
        </div>
        <% } else if ("certo".equals(retorno)) { %> <!-- Caso a operação seja realizada com sucesso -->
        <div>
            <p>SUCESSO AO <%= metodo %> O ITEM: <%= entidade %></p>
        </div>
        <% } else if ("notfound".equals(retorno)) { %> <!-- Caso o item não seja encontrado -->
        <div>
            <p>ITEM NÃO ENCONTRADO AO <%= metodo %>: <%= entidade %></p>
        </div>
        <% } else if ("existente".equals(retorno)) { %> <!-- Caso o item já exista -->
        <div>
            <p>ITEM JÁ EXISTENTE AO <%= metodo %>: <%= entidade %></p>
        </div>
        <% } %>

        <%
            // Conexão com o banco de dados para busca dos administradores
            AdministradorDAO administradorDAO = new AdministradorDAO();
            ResultSet resultSet = administradorDAO.buscarAdministradorPeloNome((String) request.getAttribute("nome")); // Busca administradores pelo nome

            try {
                if (!resultSet.next()) { // Verifica se há resultados na busca
        %>
        <p>Nenhum item encontrado</p> <!-- Mensagem caso nenhum administrador seja encontrado -->
        <%
        } else {
            do { // Caso existam resultados, exibe cada administrador encontrado
        %>
        <div class="administrador"> <!-- Container para cada administrador -->
            <div class="info"> <!-- Exibe informações básicas do administrador -->
                <h2><%= resultSet.getString("nome") %></h2> <!-- Nome do administrador -->
                <p>ID: <%= resultSet.getInt("id") %> | Email: <%= resultSet.getString("email") %></p> <!-- Exibe ID e Email do administrador -->
            </div>
            <div class="actions"> <!-- Ações específicas para o administrador: deletar ou editar -->
                <form action="${pageContext.request.contextPath}/DeletarAdministradorServlet" method="post">
                    <input type="hidden" name="administrador_id" value="<%= resultSet.getInt("id") %>"> <!-- ID do administrador para deletar -->
                    <button type="submit" class="delete-btn">Deletar</button>
                </form>
                <form action="${pageContext.request.contextPath}/DirecionarAdministradorAlterarServlet" method="post">
                    <input type="hidden" name="administrador_id" value="<%= resultSet.getInt("id") %>">
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>">
                    <input type="hidden" name="email" value="<%= resultSet.getString("email") %>">
                    <input type="hidden" name="senha" value="<%= resultSet.getString("senha") %>"> <!-- Dados do administrador para edição -->
                    <button type="submit" class="edit-btn">Editar</button>
                </form>
            </div>
        </div>
        <%
                    } while (resultSet.next()); // Continua exibindo enquanto houverem registros
                }
            } catch (SQLException e) { // Tratamento de exceções de SQL
                e.printStackTrace(); // Log da exceção
            }
        %>
    </section>
</main>
</body>
</html>
