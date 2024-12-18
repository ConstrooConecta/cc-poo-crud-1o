<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="../ErrorPage.jsp" %> <!-- Página de erro em caso de exceções -->
<html>
<head>
    <title>Ativar Plano Ativação pelo ID</title> <!-- Título da página -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/id.css"> <!-- Estilos da página -->
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

<h1 id="titulo-Editar-Pelo-ID">Ativar/Desativar Plano Ativação por ID</h1> <!-- Título principal da seção -->
<form action="${pageContext.request.contextPath}/AlterarPlanoAtivacaoServlet" method="post"> <!-- Formulário para ativação/desativação -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo opcional -->
    <div class="infos-linha1"> <!-- Linha de informações -->

        <div class="ID"> <!-- Divisão para o campo de ID -->
            <label for="id">ID:</label> <!-- Rótulo para o campo ID -->
            <input type="number" name="id_planoativacao" id="id" min="1" value="<%= (request.getAttribute("id") == null) ? "" : request.getAttribute("id") %>" placeholder="Insira o ID" required> <!-- Campo de entrada para ID do plano -->
        </div>

        <div class="classButton1">
            <input type="submit" id="buttonCadastrar" value="Ativar/Desativar"> <!-- Botão de envio -->
        </div>

        <div id="infos-erro"> <!-- Seção para exibição de mensagens de erro -->
            <% if (request.getAttribute("retorno") == "erro") { %>
            <p><%= request.getAttribute("mensagem") %></p> <!-- Mensagem de erro -->
            <% } else if (request.getAttribute("retorno") == "notfound") { %>
            <p>ITEM NÃO ENCONTRADO</p> <!-- Mensagem se o item não for encontrado -->
            <% } %>
        </div>

    </div>
</form>
</body>
</html>
