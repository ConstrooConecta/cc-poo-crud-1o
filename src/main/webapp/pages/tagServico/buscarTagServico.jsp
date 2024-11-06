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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemTagServico.css"> <!-- Link para o CSS da página -->
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
            <li><a href="${pageContext.request.contextPath}/pages/administrador/listagemAdministradores.jsp" >Adms</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/tagServico/listagemTagServico.jsp" class="active">Tag Serviço</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/planoAtivacao/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>
        </ul>
    </div>
</header>
<main>
    <section class="tags">
        <h1>Tag Serviços</h1> <!-- Título da seção -->
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
            <form class="pesquisar" action="${pageContext.request.contextPath}/BuscarTagServicoServlet" method="post">
                <input type="text" name="nome" id="nome" placeholder="Pesquisar categorias" required>
                <input type="submit" value="Pesquisar"> <!-- Botão de pesquisa -->
            </form>
        </div>

        <%-- Exibição de mensagens de retorno --%>
        <%
            if (request.getAttribute("retorno") == "erro") {
        %>
        <div>
            <p>ERRO AO <%= request.getAttribute("metodo") %> O ITEM: <%= request.getAttribute("entidade") %></p>
        </div>
        <%} else if (request.getAttribute("retorno") == "certo") {%>
        <div>
            <p>SUCESSO AO <%= request.getAttribute("metodo") %> O ITEM: <%= request.getAttribute("entidade") %></p>
        </div>
        <%} else if (request.getAttribute("retorno") == "notfound") {%>
        <div>
            <p>ITEM NÃO ENCONTRADO AO <%= request.getAttribute("metodo") %>: <%= request.getAttribute("entidade") %></p>
        </div>
        <%} else if (request.getAttribute("retorno") == "existente") {%>
        <div>
            <p>ITEM JÁ EXISTENTE AO <%= request.getAttribute("metodo") %>: <%= request.getAttribute("entidade") %></p>
        </div>
        <%}%>

        <%-- Recupera os dados do banco de dados via DAO --%>
        <%
            TagServicoDAO tagServicoDAO = new TagServicoDAO();
            ResultSet resultSet = tagServicoDAO.buscarTagServicoPeloNome(String.valueOf(request.getAttribute("nome")));

            try {
                if (!resultSet.next()){%>
        <p>Nenhum item encontrado</p>
        <%
        }else{
            do {%>
        <div class="tag">
            <div class="info">
                <h2><%= resultSet.getString("nome") %></h2> <!-- Nome da Tag Serviço -->
                <p>ID: <%= resultSet.getInt("id") %> | Descrição: <%= resultSet.getString("descricao") %></p> <!-- Exibe ID e Descrição -->
            </div>
            <div class="actions">
                <form action="${pageContext.request.contextPath}/DeletarTagServicoServlet" method="post"> <!-- Formulário para deletar Tag Serviço -->
                    <input type="hidden" name="tag_id" value="<%= resultSet.getInt("id") %>"> <!-- ID da Tag Serviço -->
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>"> <!-- Nome da Tag Serviço -->
                    <button type="submit" class="delete-btn">Deletar</button> <!-- Botão para deletar Tag Serviço -->
                </form>
                <form action="${pageContext.request.contextPath}/DirecionarTagServicoServlet" method="post"> <!-- Formulário para editar Tag Serviço -->
                    <input type="hidden" name="tag_id" value="<%= resultSet.getInt("id") %>"> <!-- ID da Tag Serviço -->
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome") %>"> <!-- Nome da Tag Serviço -->
                    <input type="hidden" name="descricao" value="<%= resultSet.getString("descricao") %>"> <!-- Descrição da Tag Serviço -->
                    <button type="submit" class="edit-btn">Editar</button> <!-- Botão para editar Tag Serviço -->
                </form>
            </div>
        </div>
        <%
                } while (resultSet.next());
            }
        %>

        <%
            } catch (SQLException sqlException) {
                sqlException.printStackTrace(); // Log do erro
            }
        %>
    </section>
</main>
</body>
</html>
