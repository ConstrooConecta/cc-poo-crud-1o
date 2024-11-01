<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="../ErrorPage.jsp" %>
<html>
<head>
    <title>Deletar Plano Ativação pelo ID</title> <!-- Título da página -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/id.css"> <!-- Link para o CSS -->
</head>
<body>
<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1>
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo"> <!-- Logo do app -->
    </div>
</header>

<h1 id="titulo-Editar-Pelo-ID">Deletar Plano Ativação por ID</h1> <!-- Título principal -->
<form action="${pageContext.request.contextPath}/DeletarPlanoAtivacaoServlet" method="post"> <!-- Formulário para deleção -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo -->
    <div class="infos-linha1"> <!-- Linha de informações -->

        <div class="ID">
            <label for="id">ID:</label> <!-- Rótulo para ID do plano -->
            <input type="text" name="id_planoativacao" id="id" placeholder="Insira o ID"> <!-- Campo de entrada para o ID -->
        </div>

        <div class="classButton1">
            <input type="submit" id="buttonCadastrar" value="Deletar"> <!-- Botão de envio -->
        </div>

        <!-- Mensagens de feedback baseadas no resultado da operação -->
        <div id="infos-erro">
            <% if (request.getAttribute("retorno") == "erro") { %>
            <div>
                <p><%= request.getAttribute("mensagem") %></p> <!-- Mensagem de erro -->
            </div>
            <% } else if (request.getAttribute("retorno") == "notfound") { %>
            <div>
                <p>ITEM NÃO ENCONTRADO</p> <!-- Mensagem se o item não for encontrado -->
            </div>
            <% } %>
        </div>
    </div>
</form>

</body>
</html>
