<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="../ErrorPage.jsp" %> <!-- Página de erro a ser redirecionada em caso de falha -->
<html>
<head>
    <title>Deletar Plano pelo ID</title> <!-- Título da página -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/id.css"> <!-- Link para a folha de estilo -->
</head>
<body>
<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1> <!-- Título principal da aplicação -->
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo"> <!-- Logo da aplicação -->
    </div>
</header>

<h1 id="titulo-Editar-Pelo-ID">Deletar Plano por ID</h1> <!-- Título da seção de deletar plano -->
<form action="${pageContext.request.contextPath}/DeletarPlanoServlet" method="post"> <!-- Formulário para deletar plano -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo do formulário -->
    <div class="infos-linha1"> <!-- Divisão para informações do formulário -->

        <div class="ID"> <!-- Campo para ID -->
            <label for="id">ID:</label> <!-- Rótulo para o campo de ID -->
            <input type="text" name="plano_id" id="id" placeholder="Insira o ID"> <!-- Campo de entrada para o ID do plano -->
        </div>

        <div class="classButton1"> <!-- Botão de envio -->
            <input type="submit" id="buttonCadastrar" value="Deletar"> <!-- Botão para submeter o formulário -->
        </div>

        <!-- Exibição de mensagens de erro ou status -->
        <% if (request.getAttribute("retorno") == "erro") { %>
        <div>
            <p><%= request.getAttribute("mensagem") %></p> <!-- Mensagem de erro recebida do servlet -->
        </div>
        <% } else if (request.getAttribute("retorno") == "notfound") { %>
        <div>
            <p>ITEM NÃO ENCONTRADO</p> <!-- Mensagem informando que o item não foi encontrado -->
        </div>
        <% } %>
    </div>
</form>

</body>
</html>
