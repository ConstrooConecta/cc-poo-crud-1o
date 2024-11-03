<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="../ErrorPage.jsp" %>
<html>
<head>
    <title>Alterar Categoria Produto pelo ID</title> <!-- Título da página -->
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/id.css"> <!-- Link para a folha de estilo -->

<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1> <!-- Nome da aplicação -->
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo"> <!-- Logo da aplicação -->
    </div>
</header>

<h1 id="titulo-Editar-Pelo-ID">Editar Categoria Produto por ID</h1> <!-- Título principal da seção -->

<form action="${pageContext.request.contextPath}/IncluirCamposCategoriaProdutoServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo (atualmente vazio) -->
    <div class="infos-linha1"> <!-- Linha que agrupa as informações do formulário -->

        <div class="ID"> <!-- Div para o campo ID -->
            <label for="id">ID:</label> <!-- Rótulo para o campo ID -->
            <input type="text" name="categoria_id" id="id" placeholder="Insira o ID" required> <!-- Campo de entrada para ID da categoria -->
        </div>

        <div class="classButton1"> <!-- Div para o botão de envio -->
            <input type="submit" id="buttonCadastrar" value="Começar a editar"> <!-- Botão de envio do formulário -->
        </div>

        <div id="infos-erro"> <!-- Div para exibir mensagens de erro -->
            <% if (request.getAttribute("retorno") == "erro") { %>
            <p><%= request.getAttribute("mensagem") %></p> <!-- Mensagem de erro se o retorno for "erro" -->
            <% } else if (request.getAttribute("retorno") == "notfound") { %>
            <p>ITEM NÃO ENCONTRADO</p> <!-- Mensagem se o item não for encontrado -->
            <% } %>
        </div>

    </div>
</form>

</body>
</html>
