<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="../ErrorPage.jsp" %> <!-- Página de erro -->
<html lang="pt-BR"> <!-- Define o idioma da página -->
<head>
    <meta charset="UTF-8"> <!-- Define a codificação de caracteres -->
    <title>Deletar Administrador pelo ID</title> <!-- Título da página -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/id.css"> <!-- Importa o CSS -->
</head>
<body>
<header>
    <div class="titulo-constroo"> <!-- Container para título e logo -->
        <h1>Constroo</h1> <!-- Nome da aplicação -->
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo"> <!-- Logo da aplicação -->
    </div>
</header>

<main>
    <h1 id="titulo-Editar-Pelo-ID">Deletar Administrador por ID</h1> <!-- Título principal da seção -->
    <form action="${pageContext.request.contextPath}/DeletarAdministradorServlet" method="post"> <!-- Formulário para deletar administrador -->

        <div class="infos-linha1"> <!-- Linha de informações -->
            <div class="ID">
                <label for="id">ID:</label> <!-- Rótulo para o campo de ID -->
                <input type="text" name="administrador_id" id="id" placeholder="Insira o ID" required> <!-- Campo de entrada para ID, obrigatório -->
            </div>

            <div class="classButton1"> <!-- Container para o botão -->
                <input type="submit" id="buttonCadastrar" value="Deletar"> <!-- Botão de envio -->
            </div>

            <!-- Exibição de mensagens de erro -->
            <% if (request.getAttribute("retorno") == "erro") { %>
            <div class="error-message">
                <p><%= request.getAttribute("mensagem") %></p> <!-- Mensagem de erro, se existir -->
            </div>
            <% } else if (request.getAttribute("retorno") == "notfound") { %>
            <div class="error-message">
                <p>ITEM NÃO ENCONTRADO</p> <!-- Mensagem caso o item não seja encontrado -->
            </div>
            <% } %>
        </div>
    </form>
</main>
</body>
</html>
