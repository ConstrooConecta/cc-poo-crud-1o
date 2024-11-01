<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="../ErrorPage.jsp" %>
<html>
<head>
    <title>Deletar Tag Serviço pelo ID</title> <!-- Título da página -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/id.css"> <!-- Link para o CSS da página -->
</head>
<body>
<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1>
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo"> <!-- Logo da aplicação -->
    </div>
</header>
<h1 id="titulo-Editar-Pelo-ID">Deletar Tag Serviço por ID</h1> <!-- Título principal -->
<form action="${pageContext.request.contextPath}/DeletarTagServicoServlet" method="post"> <!-- Formulário para deletar Tag Serviço -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo -->
    <div class="infos-linha1"> <!-- Linha de informações -->

        <div class="ID">
            <label for="id">ID:</label> <!-- Rótulo para ID -->
            <input type="text" name="tag_id" id="id" placeholder="Insira o ID"> <!-- Campo de entrada para o ID da Tag Serviço -->
        </div>

        <div class="classButton1">
            <input type="submit" id="buttonCadastrar" value="Deletar"> <!-- Botão de envio para deletar -->
        </div>

        <%-- Exibição de mensagens de retorno --%>
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
</form>
</body>
</html>
