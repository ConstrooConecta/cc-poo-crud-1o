<%@ page import="java.sql.ResultSet" %> <!-- Importação de classes necessárias -->
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.constroocrud.crud.DAOs.PlanoDAO" %> <!-- Importando o DAO para Planos -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <!-- Definição do tipo de conteúdo -->
<%@ page errorPage="../ErrorPage.jsp" %> <!-- Página de erro a ser redirecionada em caso de falha -->
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8"> <!-- Charset da página -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Responsividade -->
    <title>Planos</title> <!-- Título da página -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemPlano.css"> <!-- Link para a folha de estilo -->
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
        <h1>Planos</h1> <!-- Título da seção de planos -->
        <div class="controls">
            <div class="actionsCreateAlterDelete">
                <!-- Formulários para adicionar, alterar e deletar planos -->
                <form action="${pageContext.request.contextPath}/pages/plano/cadastrarPlano.html" method="get">
                    <button type="submit" class="create-btn">Adicionar</button>
                </form>
                <form action="${pageContext.request.contextPath}/pages/plano/alterarPlanoPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Alterar</button>
                </form>
                <form action="${pageContext.request.contextPath}/pages/plano/deletarPlanoPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Deletar</button>
                </form>
            </div>
            <!-- Formulário para pesquisar planos -->
            <form id="pesquisar" action="${pageContext.request.contextPath}/BuscarPlanoServlet" method="post">
                <input type="text" name="nome" id="nome" value="<%= request.getAttribute("nome") %>" placeholder="Pesquisar planos" required>
                <input type="submit" value="Pesquisar" class="">
            </form>
        </div>

        <!-- Exibição de mensagens de erro ou sucesso -->
        <%
            // Verifica se o atributo "retorno" não é nulo, indicando que há uma mensagem para exibir
            if (request.getAttribute("retorno") != null) {
        %>
        <div>
            <%
                // Exibe a mensagem correspondente com base no valor de "retorno"
                if (request.getAttribute("retorno").equals("erro")) {
            %>
            <p>ERRO AO <%= request.getAttribute("metodo") %> O ITEM: <%= request.getAttribute("entidade") %></p> <!-- Mensagem de erro -->
            <%
            } else if (request.getAttribute("retorno").equals("certo")) {
            %>
            <p>SUCESSO AO <%= request.getAttribute("metodo") %> O ITEM: <%= request.getAttribute("entidade") %></p> <!-- Mensagem de sucesso -->
            <%
            } else if (request.getAttribute("retorno").equals("notfound")) {
            %>
            <p>ITEM NÃO ENCONTRADO AO <%= request.getAttribute("metodo") %>: <%= request.getAttribute("entidade") %></p> <!-- Mensagem de item não encontrado -->
            <%
            } else if (request.getAttribute("retorno").equals("existente")) {
            %>
            <p>ITEM JÁ EXISTENTE AO <%= request.getAttribute("metodo") %>: <%= request.getAttribute("entidade") %></p> <!-- Mensagem de item já existente -->
            <%
                }
            %>
        </div>
        <%
            }
        %>


        <!-- Recuperação de planos do banco de dados -->
        <%
            PlanoDAO planoDAO = new PlanoDAO();
            ResultSet resultSet = planoDAO.buscarPlanoPeloNome(String.valueOf(request.getAttribute("nome")));
            try {
                if (!resultSet.next()){%>
                    <p>Nenhum item encontrado</p>
                <%
                }else{
                    do {%>
        <div class="plano">
            <div class="info">
                <div class="details">
                    <h2>ID: <%= resultSet.getString("id") %> | <%= resultSet.getString("nome_plano") %></h2>
                    <p>Preço Mensal: <%= resultSet.getDouble("valor") %></p> <!-- Preço do plano -->
                    <p>Tipo de Usuário:
                        <% if (resultSet.getString("tipo_plano").equals("P")) { %>Profissional
                        <% } else if (resultSet.getString("tipo_plano").equals("V")) { %>Vendedor
                        <% } %>
                    </p>
                    <p>Tempo de Duração: <%= resultSet.getInt("tempo_duracao") %></p> <!-- Duração do plano -->
                    <p><%= resultSet.getString("descricao") %></p> <!-- Descrição do plano -->
                </div>
            </div>
            <div class="actions">
                <!-- Formulários para deletar e editar planos -->
                <form action="${pageContext.request.contextPath}/DeletarPlanoServlet" method="post">
                    <input type="hidden" name="plano_id" value="<%= resultSet.getInt("id") %>"> <!-- ID do plano -->
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome_plano") %>"> <!-- Nome do plano -->
                    <button type="submit" class="delete-btn">Deletar</button> <!-- Botão de deletar -->
                </form>
                <form action="${pageContext.request.contextPath}/DirecionarPlanoAlterarServlet" method="post">
                    <input type="hidden" name="plano_id" value="<%= resultSet.getInt("id") %>"> <!-- ID do plano -->
                    <input type="hidden" name="nome" value="<%= resultSet.getString("nome_plano") %>"> <!-- Nome do plano -->
                    <input type="hidden" name="precoMensal" value="<%= resultSet.getString("valor") %>"> <!-- Preço do plano -->
                    <input type="hidden" name="tipoUsuario" value="<%= resultSet.getString("tipo_plano") %>"> <!-- Tipo de usuário -->
                    <input type="hidden" name="duracao" value="<%= resultSet.getString("tempo_duracao") %>"> <!-- Duração do plano -->
                    <input type="hidden" name="descricao" value="<%= resultSet.getString("descricao") %>"> <!-- Descrição do plano -->
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
                e.printStackTrace(); // Impressão de erro se houver uma exceção
            }
        %>

    </section>
</main>
</body>
</html>
