<%@ page import="java.sql.ResultSet" %> <!-- Importa a classe ResultSet -->
<%@ page import="java.sql.SQLException" %> <!-- Importa a classe SQLException -->
<%@ page import="org.constroocrud.crud.DAOs.AdministradorDAO" %> <!-- Importa a classe AdministradorDAO -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <!-- Define tipo de conteúdo e codificação -->
<%@ page errorPage="../ErrorPage.jsp" %>
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
<nav> <!-- Navegação principal -->
    <ul>
        <li><a href="${pageContext.request.contextPath}/pages/plano/listagemPlanos.jsp">Planos</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/categoriaProduto/listagemCategoriaProdutos.jsp">Categorias</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/administrador/listagemAdministradores.jsp" class="active">Adms</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/tagServico/listagemTagServico.jsp">Tag Serviço</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/planoAtivacao/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>
    </ul>
</nav>
<main>
    <section class="administradores"> <!-- Seção para administradores -->
        <h1>Administradores</h1> <!-- Título da seção -->
        <div class="controls"> <!-- Controles de ação -->
            <div class="actionsCreateAlterDelete">
                <form action="${pageContext.request.contextPath}/pages/administrador/cadastrarAdministrador.jsp" method="get">
                    <button type="submit" class="create-btn">Adicionar</button> <!-- Botão para adicionar administrador -->
                </form>
                <form action="${pageContext.request.contextPath}/pages/administrador/alterarAdministradorPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Alterar</button> <!-- Botão para adicionar administrador -->
                </form>
                <form action="${pageContext.request.contextPath}/pages/administrador/deletarAdministradorPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Deletar</button> <!-- Botão para deletar administrador -->
                </form>
            </div>
            <form action="${pageContext.request.contextPath}/BuscarAdministradorServlet" method="post">
                <input type="text" name="nome" id="nome" value="<%=request.getAttribute("nome")%>" placeholder="Pesquisar administradores pelo nome"> <!-- Campo de pesquisa -->
                <input type="submit" value="Pesquisar"> <!-- Botão de pesquisa -->
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
            AdministradorDAO administradorDAO = new AdministradorDAO(); // Instância do DAO
            ResultSet resultSet = administradorDAO.buscarAdministradorPeloNome(String.valueOf(request.getAttribute("nome"))); // Busca os administradores

            try {
                if (!resultSet.next()){%>
        <p>Nenhum item encontrado</p>
        <%
        }else{
            do {%>
        <div class="administrador"> <!-- Container para cada administrador -->
            <div class="info"> <!-- Informações do administrador -->
                <h2><%= resultSet.getString("nome") %></h2> <!-- Nome do administrador -->
                <p>Email: <%= resultSet.getString("email") %></p> <!-- Email do administrador -->
            </div>
            <div class="actions"> <!-- Ações disponíveis -->
                <form action="${pageContext.request.contextPath}/DeletarAdministradorServlet" method="post"> <!-- Formulário para deletar -->
                    <input type="hidden" name="administrador_id" value="<%= resultSet.getInt("id") %>"> <!-- ID do administrador -->
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>"> <!-- Nome do administrador -->
                    <button type="submit" class="delete-btn">Deletar</button> <!-- Botão de deletar -->
                </form>
                <form action="${pageContext.request.contextPath}/DirecionarAdministradorAlterarServlet" method="post"> <!-- Formulário para editar -->
                    <input type="hidden" name="administrador_id" value="<%= resultSet.getInt("id") %>"> <!-- ID do administrador -->
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>"> <!-- Nome do administrador -->
                    <input type="hidden" name="email" value="<%= resultSet.getString("email") %>"> <!-- Email do administrador -->
                    <input type="hidden" name="senha" value="<%= resultSet.getString("senha") %>"> <!-- Senha do administrador -->
                    <button type="submit" class="edit-btn">Editar</button> <!-- Botão de editar -->
                </form>
            </div>
        </div>
        <%
                } while (resultSet.next());
            }
        %>

        <%
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>
    </section>
</main>
</body>
</html>
