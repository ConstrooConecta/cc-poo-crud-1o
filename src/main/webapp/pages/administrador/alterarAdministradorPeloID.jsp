<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="../ErrorPage.jsp" %>
<html>
<head>
    <title>Alterar Administrador pelo ID</title> <!-- Título da página -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/id.css"> <!-- Importação do CSS -->
</head>
<body>

<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1> <!-- Nome do aplicativo -->
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo"> <!-- Logo do aplicativo -->
    </div>
</header>

<h1 id="titulo-Editar-Pelo-ID">Editar Administrador por ID</h1> <!-- Título principal da seção de edição -->

<!-- Formulário que será enviado ao servlet para inclusão dos campos do administrador -->
<form action="${pageContext.request.contextPath}/IncluirCamposAdministradorServlet" method="post"> <!-- Ação do formulário -->
    <div class="infos-linha1"> <!-- Contêiner para as informações do administrador -->

        <!-- Seção para inserir o ID do administrador -->
        <div class="ID">
            <label for="id">ID:</label> <!-- Rótulo para o campo de ID -->
            <input type="text" name="administrador_id" id="id" placeholder="Insira o ID" required> <!-- Campo para inserir o ID -->
        </div>

        <div class="classButton1">
            <input type="submit" id="buttonCadastrar" value="Começar a editar"> <!-- Botão de envio do formulário -->
        </div>

        <!-- Seção para exibir mensagens de erro -->
        <div id="infos-erro">
            <% if ("erro".equals(request.getAttribute("retorno"))) { %>
            <p><%= request.getAttribute("mensagem") %></p> <!-- Mensagem de erro a ser exibida -->
            <% } else if ("notfound".equals(request.getAttribute("retorno"))) { %>
            <p>ITEM NÃO ENCONTRADO</p> <!-- Mensagem caso o item não seja encontrado -->
            <% } %>
        </div>
    </div>
</form>

</body>
</html>
