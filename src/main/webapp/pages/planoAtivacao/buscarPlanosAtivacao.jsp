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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemPlanosAtivacao.css"> <!-- Link para o CSS -->
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
                <!-- Formulário para alterar planos de ativação -->
                <form action="${pageContext.request.contextPath}/pages/planoAtivacao/alterarPlanoAtivacaoPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Alterar</button>
                </form>
                <!-- Formulário para deletar planos de ativação -->
                <form action="${pageContext.request.contextPath}/pages/planoAtivacao/deletarPlanoAtivacaoPeloID.jsp" method="get">
                    <button type="submit" class="create-btn">Deletar</button>
                </form>
            </div>
            <!-- Formulário para buscar planos de ativação pelo ID -->
            <form class="form-plano-ativacao-criar" id="pesquisar" id="form-plano-ativacao-criar" action="${pageContext.request.contextPath}/BuscarPlanoAtivacaoServlet" method="post">
                <input type="number" name="id" id="id" value="<%= request.getAttribute("id") %>" placeholder="Pesquise o plano pelo ID" required>
                <input type="submit" value="Pesquisar">
            </form>
        </div>

        <!-- Mensagens de feedback -->
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

        <%-- Inicio do loop dos resultados --%>
        <%
            PlanoAtivacaoDAO planoAtivacaoDAO = new PlanoAtivacaoDAO();
            ResultSet resultSet;

            // Verifica se o ID é zero e busca todos os planos ou um específico
            if (Integer.parseInt(String.valueOf(request.getAttribute("id"))) == 0) {
                resultSet = planoAtivacaoDAO.buscarPlanoAtivacao();
            } else {
                resultSet = planoAtivacaoDAO.buscarPlanoAtivacaoPeloID(Integer.parseInt(String.valueOf(request.getAttribute("id"))));
            }

            try {
                if (!resultSet.next()){%>
        <p>Nenhum item encontrado</p>
            <%
            }else{
                do {%>
        <div class="plano">
            <div class="info">
                <div class="details">
                    <h2>Plano ID: <%= resultSet.getInt("id") %></h2>
                    <div class="bottom-plano-ativacao-infos">
                        <p>Ativação: <% if (resultSet.getString("ativacao").equals("A")) { %> Ativo <% } else if (resultSet.getString("ativacao").equals("I")) { %> Inativo <% } %></p>
                        <div class="infos-duracao">
                            <p class="infos-texto">Data de Assinatura: <%= resultSet.getDate("data_assinatura") %></p>
                            <p class="infos-texto">Data de Término: <%= resultSet.getDate("data_final") %></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="actions">
                <!-- Formulário para deletar um plano de ativação -->
                <form action="${pageContext.request.contextPath}/DeletarPlanoAtivacaoServlet" method="post">
                    <input type="hidden" name="id_planoativacao" value="<%= resultSet.getInt("id") %>">
                    <button type="submit" class="delete-btn">Deletar</button>
                </form>
                <!-- Formulário para alterar um plano de ativação -->
                <form action="${pageContext.request.contextPath}/AlterarPlanoAtivacaoServlet" method="post">
                    <input type="hidden" name="id_planoativacao" value="<%= resultSet.getInt("id") %>">
                    <button type="submit" class="edit-btn">Alterar</button>
                </form>
            </div>
        </div>
        <%
                } while (resultSet.next());
            }
        %>


        <%
            } catch (SQLException e) {
                e.printStackTrace(); // Imprime o stack trace em caso de erro
            }
        %>
    </section>
</main>
</body>
</html>
