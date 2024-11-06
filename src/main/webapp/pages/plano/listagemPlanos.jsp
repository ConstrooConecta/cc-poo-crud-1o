<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.constroocrud.crud.DAOs.PlanoDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../ErrorPage.jsp" %> <!-- Página de erro em caso de exceções -->
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8"> <!-- Charset para a página -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Responsividade -->
    <title>Planos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemPlano.css"> <!-- Estilos da página -->
</head>
<body>
<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1>
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo">  <!-- Logo da aplicação -->
    </div>

    <div class="cruds"> <!-- Navegação principal com links para diferentes seções -->
        <ul>
            <li><a href="${pageContext.request.contextPath}/pages/plano/listagemPlanos.jsp" class="active">Planos</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/categoriaProduto/listagemCategoriaProdutos.jsp">Categorias</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/administrador/listagemAdministradores.jsp">Adms</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/tagServico/listagemTagServico.jsp">Tag Serviço</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/planoAtivacao/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>
        </ul>
    </div>
</header>
<main>
    <section class="planos">
        <h1>Planos</h1> <!-- Título da seção -->
        <div class="controls">
            <div class="actionsCreateAlterDelete"> <!-- Controles para ações de criação, alteração e exclusão -->
                <form action="${pageContext.request.contextPath}/pages/plano/cadastrarPlano.html" method="get">
                    <button type="submit" class="create-btn">Adicionar</button> <!-- Botão para adicionar plano -->
                </form>
                <form action="${pageContext.request.contextPath}/pages/plano/alterarPlanoPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Alterar</button> <!-- Botão para alterar plano -->
                </form>
                <form action="${pageContext.request.contextPath}/pages/plano/deletarPlanoPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Deletar</button> <!-- Botão para deletar plano -->
                </form>
            </div>
            <form class="pesquisar" action="${pageContext.request.contextPath}/BuscarPlanoServlet" method="post"> <!-- Formulário de pesquisa -->
                <input type="text" name="nome" id="nome" placeholder="Pesquisar planos" required> <!-- Campo de pesquisa -->
                <input type="submit" value="Pesquisar"> <!-- Botão de pesquisa -->
            </form>
        </div>

        <!-- Exibição de mensagens de retorno -->
        <% if (request.getAttribute("retorno") != null) { %>
        <div>
            <% if (request.getAttribute("retorno").equals("erro")) { %>
            <p>ERRO AO <%= request.getAttribute("metodo") %> O ITEM: <%= request.getAttribute("entidade") %></p>
            <% } else if (request.getAttribute("retorno").equals("certo")) { %>
            <p>SUCESSO AO <%= request.getAttribute("metodo") %> O ITEM: <%= request.getAttribute("entidade") %></p>
            <% } else if (request.getAttribute("retorno").equals("notfound")) { %>
            <p>ITEM NÃO ENCONTRADO AO <%= request.getAttribute("metodo") %>: <%= request.getAttribute("entidade") %></p>
            <% } else if (request.getAttribute("retorno").equals("existente")) { %>
            <p>ITEM JÁ EXISTENTE AO <%= request.getAttribute("metodo") %>: <%= request.getAttribute("entidade") %></p>
            <% } %>
        </div>
        <% } %>

        <%
            // Instancia o DAO e busca os planos
            PlanoDAO planoDAO = new PlanoDAO();
            ResultSet resultSet = planoDAO.buscarPlanos(); // Método que busca todos os planos
            try {
                while (resultSet.next()) { // Itera sobre os resultados
        %>
        <div class="plano"> <!-- Divisão para cada plano -->
            <div class="info">
                <div class="details">
                    <h2>ID: <%= resultSet.getString("id") %> | <%= resultSet.getString("nome_plano") %></h2>
                    <p>Preço Mensal: <%= resultSet.getDouble("valor") %></p>
                    <p>Tipo de Plano:
                        <% if (resultSet.getString("tipo_plano").equals("P")) { %>Profissional<%
                        } else if (resultSet.getString("tipo_plano").equals("V")) { %>Vendedor<% } %>
                    </p>
                    <p>Tempo de Duração: <%= resultSet.getInt("tempo_duracao") %></p>
                    <p><%= resultSet.getString("descricao") %></p>
                </div>
            </div>

            <!-- Formulários para deletar ou editar cada plano -->
            <div class="actions">
                <form action="${pageContext.request.contextPath}/DeletarPlanoServlet" method="post">
                    <input type="hidden" name="plano_id" value="<%= resultSet.getInt("id") %>"> <!-- ID do plano -->
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome_plano") %>"> <!-- Nome do plano -->
                    <button type="submit" class="delete-btn">Deletar</button> <!-- Botão para deletar plano -->
                </form>
                <form action="${pageContext.request.contextPath}/DirecionarPlanoAlterarServlet" method="post">
                    <input type="hidden" name="plano_id" value="<%= resultSet.getInt("id") %>"> <!-- ID do plano -->
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome_plano") %>"> <!-- Nome do plano -->
                    <input type="hidden" name="precoMensal" value="<%= resultSet.getString("valor") %>"> <!-- Preço mensal do plano -->
                    <input type="hidden" name="tipoUsuario" value="<%= resultSet.getString("tipo_plano") %>"> <!-- Tipo de usuário -->
                    <input type="hidden" name="duracao" value="<%= resultSet.getString("tempo_duracao") %>"> <!-- Duração do plano -->
                    <input type="hidden" name="descricao" value="<%= resultSet.getString("descricao") %>"> <!-- Descrição do plano -->
                    <button type="submit" class="edit-btn">Editar</button> <!-- Botão para editar plano -->
                </form>
            </div>
        </div>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Captura e imprime a exceção em caso de erro
            }
        %>
    </section>
</main>
</body>
</html>
